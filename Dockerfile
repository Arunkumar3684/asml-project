FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/asml-app-1.0.0.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar","--server.port=8082"]
