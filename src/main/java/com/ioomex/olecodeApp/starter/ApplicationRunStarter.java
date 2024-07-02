package com.ioomex.olecodeApp.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author ioomex
 * @description run application
 */
public class ApplicationRunStarter {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunStarter.class);


    /**
     * 日志输出启动信息
     * @param env 环境变量
     */
    public static void logApplicationStartup(Environment env) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String path = env.getProperty("server.servlet.context-path", "");

            String localUrl = "http://localhost:" + port + path;
            String kn4jDocUrl = "http://" + ip + ":" + port + path + "/doc.html";

            log.info("\n----------------------------------------------------------\n\t"
                + "Application is running! Access URLs:\n\t"
                + "Local: \t\t{}\n\t"
                + "Kn4j-Doc: \t\t{}\n"
                + "----------------------------------------------------------",
              localUrl, kn4jDocUrl);

            log.info("启动成功 V0.0.1 {}", System.currentTimeMillis());

        } catch (UnknownHostException e) {
            log.error("The host address could not be determined.", e);
        }
    }
}