package com.sun.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -1;

    private String id;
    private String name;
    private String menuType;
    private String parentId;
    private String url;
    private String img;
    private String sort;
}
