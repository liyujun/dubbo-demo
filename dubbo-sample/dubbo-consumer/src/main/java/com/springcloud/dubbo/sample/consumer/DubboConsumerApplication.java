package com.springcloud.dubbo.sample.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 应用启动类
 *
 * @author manco
 * @since 2019/03/07 10:22
 */

@SpringBootApplication(scanBasePackages = {"com.springcloud.dubbo.sample.consumer.controller", "com.springcloud.dubbo.sample.consumer.sentinel"})
@ImportResource(locations = {"classpath:application-dubbo.xml"})
@Configuration
public class DubboConsumerApplication {
    private static volatile boolean running = true;

    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DubboConsumerApplication.class, args);
        logger.info("消费者服务启动成功");
        synchronized (DubboConsumerApplication.class) {
            while (running) {
                try {
                    DubboConsumerApplication.class.wait();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                } finally {
                    SpringApplication.exit(context);
                }
            }
        }
    }
}
