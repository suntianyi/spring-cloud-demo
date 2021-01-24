package com.sun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsSpu implements Serializable {
    private static final long serialVersionUID = -1;

    private String id;
    private String orgId;
    private String categoryId;
    private String name;
    private String img;
    private int price;
    private String status;
    private String detail;
}
