FROM java:8-jdk-alpine

COPY ./target/et-forecast-1.0.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch et-forecast-1.0.0.jar'

ENTRYPOINT ["java","-jar","et-forecast-1.0.0.jar"]