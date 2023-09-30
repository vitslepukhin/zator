FROM maven:3.9.4-eclipse-temurin-20-alpine

WORKDIR /

COPY / .

RUN mvn clean install

CMD java -jar ./target/zator-0.0.1-SNAPSHOT.jar