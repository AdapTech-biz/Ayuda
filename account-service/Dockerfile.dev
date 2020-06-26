FROM maven:3.6-amazoncorretto-11
ARG APP_FILE=target/*.jar
COPY ${APP_FILE} app.jar
EXPOSE 8201
ENTRYPOINT ["java", "-jar", "/app.jar"]