package com.sun.service;

import com.sun.model.Dict;

import java.util.List;

public interface DictService {
    List<Dict> get(String dictType);
}
