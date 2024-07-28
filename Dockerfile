# 使用轻量级的OpenJDK镜像运行应用程序
FROM openjdk:11-jre

# 设置维护者信息
LABEL maintainer="iooemx"

# 设置工作目录
WORKDIR /

# 复制本地生成的JAR文件到运行镜像中
COPY target/olecode-springboot-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用程序端口
EXPOSE 8080

# 运行应用程序，不使用jenkins le
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
