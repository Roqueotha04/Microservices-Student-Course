FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /root
COPY pom-parent /root/pom-parent
COPY microservice-course /root/microservice-course
COPY mvnw /root/
COPY .mvn /root/.mvn
RUN chmod +x mvnw
RUN ./mvnw -f microservice-course/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /root/microservice-course/target/app.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]