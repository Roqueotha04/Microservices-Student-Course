FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /root
COPY pom-parent /root/pom-parent
COPY microservice-config /root/microservice-config
COPY pom.xml ./
COPY mvnw /root/
COPY .mvn /root/.mvn

RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw

RUN ls -la /root
RUN ./mvnw -f microservice-config/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /root/microservice-config/target/app.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]