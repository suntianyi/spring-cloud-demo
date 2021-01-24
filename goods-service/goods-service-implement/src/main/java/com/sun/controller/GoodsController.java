package com.sun.controller;

import com.alibaba.fastjson.JSON;
import com.sun.po.GoodsSpu;
import com.sun.po.User;
import com.sun.result.Result;
import com.sun.service.GoodsService;
import com.sun.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Reference
    UserService userService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }


    @GetMapping(value = "{id}")
    public Result<GoodsSpu> get(@PathVariable String id) {
        User user = userService.get(id, null, null);
        log.info(JSON.toJSONString(user));
        return Result.success(goodsService.get(id));
    }
}
