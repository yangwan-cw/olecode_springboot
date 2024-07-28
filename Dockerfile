FROM openjdk:11-jre
MAINTAINER iooemx
RUN mvn package -DskipTests
WORKDIR /
ADD target/olecode-springboot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
