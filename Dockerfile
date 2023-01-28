FROM openjdk:17-jdk-alpine

RUN apk add maven

RUN java -version

RUN mvn -version

COPY . .

RUN mvn clean package -Dmaven.test.skip

EXPOSE 8080

#RUN ls
#
#RUN ls /target
#COPY /target/CryptocurrencyTracker-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["mvn","spring-boot:run"]
