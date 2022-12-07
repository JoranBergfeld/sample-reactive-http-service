FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -Dmaven.test.skip=true
FROM openjdk:17-jdk
COPY --from=0 /build/target/http-reactive-http-service-0.0.1-SNAPSHOT.jar /app/target/app.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/app/target/app.jar", "--server.port=8081" ]
