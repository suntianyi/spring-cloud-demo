package com.sun.controller;

import com.sun.model.Dict;
import com.sun.result.Result;
import com.sun.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/dict")
@Api(tags = "字典")
public class DictController {
    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping(value = "/{dictType}")
    @ApiOperation(value = "根据字典类型查询")
    public Result<List<Dict>> get(@PathVariable String dictType) {
        return Result.success(dictService.get(dictType));
    }
}
