package com.springcloud.dubbo.sample.service.impl;

import com.springcloud.dubbo.sample.api.domain.Goods;
import com.springcloud.dubbo.sample.api.GoodsService;
import com.springcloud.dubbo.sample.api.response.GoodsResponse;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Override
    public GoodsResponse getAllGoods() {
        GoodsResponse goodsResponse = new GoodsResponse();
        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods("computer", 10));
        goods.add(new Goods("telephone", 20));
        goods.add(new Goods("books", 30));
        logger.info("{} 正在调用服务...", RpcContext.getContext().getRemoteHost());
        goodsResponse.setGoodsList(goods);
        return goodsResponse;
    }
}
