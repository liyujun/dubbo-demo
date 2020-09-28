package com.springcloud.dubbo.sample.api.response;

import com.springcloud.dubbo.sample.api.domain.Goods;

import java.util.List;

public class UserResponse extends SampleResponse {

    private static final long serialVersionUID = 1161177489152857240L;

    private String name;

    private List<Goods> goods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
