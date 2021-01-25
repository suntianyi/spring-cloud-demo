package com.sun.service.impl;

import com.sun.model.Dict;
import com.sun.service.DictService;
import com.sun.constants.RedisKeyPrefixConstants;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = DictService.class)
public class DictServiceImpl implements DictService {

    @Override
    @Cacheable(cacheNames = RedisKeyPrefixConstants.DICT, key = "#dictType")
    public List<Dict> get(String dictType) {
        System.out.println("执行查询方法");
        return new ArrayList<>();
    }

    @CacheEvict(value = RedisKeyPrefixConstants.DICT, key = "#dict.dictType")
    public void update(Dict dict) {
    }
}
