FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/transactions-data-service-1.0.0-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]