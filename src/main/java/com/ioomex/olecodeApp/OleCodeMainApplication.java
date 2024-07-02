package com.ioomex.olecodeApp;

import com.ioomex.olecodeApp.starter.ApplicationRunStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
// todo 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.ioomex.olecodeApp.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class OleCodeMainApplication {

    public static void main(String[] args) {

        // 创建 SpringApplicationBuilder 对象并进行相关设置
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OleCodeMainApplication.class)
          .main(SpringVersion.class)
          .bannerMode(Banner.Mode.CONSOLE);

        // 运行 Spring 应用
        ConfigurableApplicationContext run = builder.run(args);

        // 获取环境对象
        Environment env = run.getEnvironment();
        ApplicationRunStarter.logApplicationStartup(env);
    }

}
