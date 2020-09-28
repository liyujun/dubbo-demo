package com.springcloud.dubbo.sample.service;


import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DefaultDubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.dubbo.sample.api.response.SampleResponse;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;

/**
 * 应用启动类
 *
 * @author manco
 * @since 2019/03/07 10:22
 */

@SpringBootApplication
@ImportResource(locations = {"classpath:application-dubbo.xml"})
public class DubboProviderApplication {
    private static final Logger logger = LoggerFactory.getLogger(DubboProviderApplication.class);

    @PostConstruct
    void initFallback () {
        DubboAdapterGlobalConfig.setProviderFallback(new DefaultDubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
                logger.info("捕获到block异常，降级处理", ex);
                SampleResponse response = new SampleResponse();
                response.setErrorCode("500");
                response.setErrorMsg("service is busy");
                return AsyncRpcResult.newDefaultAsyncResult(response, invocation);
            }
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
        logger.info("服务启动成功");
    }
}
