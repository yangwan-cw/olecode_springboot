# 第一阶段：使用Maven镜像进行构建
FROM maven:3.8.1-jdk-8-slim as builder

# 设置工作目录
WORKDIR /app

# 复制pom.xml和源代码
COPY pom.xml .
COPY src ./src

# 构建项目并跳过测试
RUN mvn clean package -DskipTests

# 第二阶段：使用轻量级的OpenJDK镜像运行应用程序
FROM openjdk:11-jre

# 设置维护者信息
MAINTAINER iooemx

# 设置第二阶段工作目录
WORKDIR /

# 从构建阶段复制生成的JAR文件到运行镜像中
COPY --from=builder /app/target/olecode-springboot-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用程序端口
EXPOSE 8080

# 运行应用程序
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
