FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /root
COPY pom-parent /root/pom-parent
COPY microservice-gateway /root/microservice-gateway
COPY mvnw /root/
COPY .mvn /root/.mvn
RUN chmod +x mvnw
RUN ./mvnw -f microservice-gateway/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /root/microservice-gateway/target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]