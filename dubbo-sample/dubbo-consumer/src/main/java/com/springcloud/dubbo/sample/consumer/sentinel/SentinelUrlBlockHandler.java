package com.springcloud.dubbo.sample.consumer.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.springcloud.dubbo.sample.api.response.SampleResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 *
 * @author manco
 * @since 2020/10/09 17:22
 */
@Component
public class SentinelUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        SampleResponse errorResponse = new SampleResponse();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            errorResponse.setErrorMsg("接口限流了");
            errorResponse.setErrorCode("100");
        } else if (e instanceof DegradeException) {
            errorResponse.setErrorMsg("服务降级了");
            errorResponse.setErrorCode("101");
        } else if (e instanceof ParamFlowException) {
            errorResponse.setErrorMsg("热点参数限流了");
            errorResponse.setErrorCode("102");
        } else if (e instanceof SystemBlockException) {
            errorResponse.setErrorMsg("触发系统保护规则");
            errorResponse.setErrorCode("103");
        } else if (e instanceof AuthorityException) {
            errorResponse.setErrorMsg("授权规则不通过");
            errorResponse.setErrorCode("104");
        }
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(errorResponse));
        out.flush();
        out.close();
    }
}
