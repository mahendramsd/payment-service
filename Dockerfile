FROM openjdk:8u212-jre-alpine3.9
MAINTAINER msd.com
COPY target/service-0.0.1-SNAPSHOT.jar /opt/payment-service.jar
RUN chmod +x /opt/payment-service.jar
EXPOSE 8081
WORKDIR /opt/
USER root
ENTRYPOINT ["java", "-jar", "/opt/payment-service.jar"]