package com.samsungsds.caasportal.repository;

import com.samsungsds.caasportal.repository.entity.WorkloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadRepository extends JpaRepository<WorkloadEntity, Long>, WorkloadRepositoryCustom {

    //TODO : Return Type 변경하기    List<Long> -> Long
//    @Query("SELECT SUM(i.investingAmount) FROM InvestEntity i WHERE i.product.id = :productId group by :productId")
//    List<Long> getInvestingAmountTotal(Long productId);
//
//    @Query("select i from InvestEntity i join fetch i.product where i.userId = :userId")
//    List<InvestEntity> getInvestByUserId(Long userId);
}
