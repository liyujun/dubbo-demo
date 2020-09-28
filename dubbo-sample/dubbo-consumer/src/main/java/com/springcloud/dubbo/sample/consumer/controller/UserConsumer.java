package com.springcloud.dubbo.sample.consumer.controller;


import com.springcloud.dubbo.sample.api.GoodsService;
import com.springcloud.dubbo.sample.api.response.GoodsResponse;
import com.springcloud.dubbo.sample.api.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/userGoods")
    public UserResponse getUserGoods() {
        UserResponse userResponse = new UserResponse();
        try {
            GoodsResponse goodsResponse = goodsService.getAllGoods();
            userResponse.setErrorCode(goodsResponse.getErrorCode());
            userResponse.setErrorMsg(goodsResponse.getErrorMsg());
            if(goodsResponse.getErrorCode().equals("0")) {
                userResponse.setName("manco");
                if (goodsResponse.getGoodsList() != null) {
                    userResponse.setGoods(goodsResponse.getGoodsList());
                }
            }
        } catch (Exception e){
            logger.error("调用发生异常：", e);
            userResponse.setErrorCode("-1");
            userResponse.setErrorMsg("unknown error");
        }
        return userResponse;
    }
}
