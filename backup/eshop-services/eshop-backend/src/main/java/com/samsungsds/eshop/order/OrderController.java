package com.samsungsds.eshop.order;

import java.util.stream.Stream;

import com.google.common.collect.Iterables;
import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.cart.CartService;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.payment.PaymentRequest;
import com.samsungsds.eshop.payment.PaymentService;
import com.samsungsds.eshop.product.Product;
import com.samsungsds.eshop.product.ProductService;
import com.samsungsds.eshop.shipping.ShippingRequest;
import com.samsungsds.eshop.shipping.ShippingResult;
import com.samsungsds.eshop.shipping.ShippingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/checkouts")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final ShippingService shippingService;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;

    public OrderController(final OrderService orderService, 
    final ShippingService shippingService,
    final  PaymentService paymentService,
    final CartService cartService,
    final ProductService productService,
    final RabbitTemplate rabbitTemplate) {
        this.orderService = orderService;
        this.shippingService = shippingService;
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.productService = productService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderResult> placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("placeOrder : " + orderRequest);

        // cart ??????
        CartItem[] cartItems = Iterables.toArray(cartService.getCartItems(), CartItem.class);


        // cart ?????? ??????
        Product[] products = getProducts(cartItems);

        // ?????? ?????? ?????? ??????
        Money itemPrice = orderService.calculateItemPrice(cartItems, products);
        logger.info("total item price : " + itemPrice);

        // ?????? ????????? ??????
        Money shippingCost = shippingService.calculateShippingCostFromCartItems(cartItems);

        // ?????? ??????
        PaymentRequest request = new PaymentRequest(orderRequest.getCreditCardInfo(), itemPrice.plus(shippingCost));
        paymentService.requestPayment(request);

        // ?????? ??????
        ShippingResult shippingResult = shippingService
                .shipOrder(new ShippingRequest(cartItems, orderRequest.getAddress()));
        logger.info("shippingCost : " + shippingResult.getShippingCost());

        // ?????? ??????
        Money totalCost = itemPrice.plus(shippingResult.getShippingCost());

        // ??????ID ??????
        String orderId = orderService.createOrderId(orderRequest);

        // ?????? ?????????
        // cartService.emptyCart();
        rabbitTemplate.convertAndSend("eshop-exchange","order.placed", new OrderPlaced(orderId));
        return ResponseEntity.ok(new OrderResult(orderId, shippingResult.getShippingTrackingId(),
                shippingResult.getShippingCost(), totalCost));
    }

    private Product[] getProducts(CartItem[] cartItems) {
        String[] cartItemIds = Stream.of(cartItems).map(CartItem::getId).toArray(String[]::new);
        return productService.fetchProductsByIds(cartItemIds).getProducts();
    }
}
