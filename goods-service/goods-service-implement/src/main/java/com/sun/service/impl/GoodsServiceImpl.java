package com.sun.service.impl;

import com.sun.po.GoodsSpu;
import com.sun.service.GoodsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {
    @Override
    public GoodsSpu get(String id) {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setId(id);
        goodsSpu.setName("机械键盘");
        goodsSpu.setImg("http://b.hiphotos.baidu.com/zhidao/pic/item/6159252dd42a283455cdefb85db5c9ea15cebf1e.jpg");
        goodsSpu.setPrice(19900);
        return goodsSpu;
    }
}
