# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm set strict-ssl false --global
RUN npm install
COPY . .
RUN npm run build

# production stage
FROM node:lts-alpine as production-stage
WORKDIR /app
RUN npm set strict-ssl false --global
RUN npm install -g http-server
COPY --from=build-stage /app/dist /app/dist
EXPOSE 8080
CMD ["http-server", "dist"]


# dockerfile 빌드 시 npm관련 에러 메세지가 나오는데, 잘 몰라서 일단 위처럼 변경했습니다.
#FROM node:lts-alpine
#
## for intranet proxy
#RUN npm set strict-ssl false --global
#
## install simple http server for serving static content
#RUN npm install -g http-server
#
## make the 'app' folder the current working directory
#WORKDIR /app
#
## copy both 'package.json' and 'package-lock.json' (if available)
#COPY package*.json ./
#
## install project dependencies leaving out dev dependencies
#RUN npm install --production
#
## copy project files and folders to the current working directory (i.e. 'app' folder)
#COPY . .
#
## build app for production with minification
#RUN npm run build
#
#EXPOSE 8001
#CMD [ "http-server", "dist" ]