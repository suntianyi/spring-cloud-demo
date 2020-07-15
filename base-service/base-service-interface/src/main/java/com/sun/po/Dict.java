package com.sun.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dict implements Serializable {
    private static final long serialVersionUID = -1;

    private String dictType;
    private String dictName;
    private String dictValue;
    private String remark;
}
