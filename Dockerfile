# Docker 镜像构建
# @author ioomex
# @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
FROM maven:3.8.1-jdk-8-slim as builder

# 设置工作目录
WORKDIR /app
COPY pom.xml .
COPY src ./src

# 构建镜像
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/olecode-springboot-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]