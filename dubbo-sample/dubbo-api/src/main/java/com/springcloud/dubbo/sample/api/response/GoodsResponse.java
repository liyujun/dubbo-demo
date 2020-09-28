package com.springcloud.dubbo.sample.api.response;

import com.springcloud.dubbo.sample.api.domain.Goods;

import java.util.List;

/**
 * 测试服务接口响应报文
 * @author manco
 * @since 2020-09-27 10:06:52
 */
public class GoodsResponse extends SampleResponse {

    private static final long serialVersionUID = 3763979071067580116L;

    private List<Goods> goodsList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
