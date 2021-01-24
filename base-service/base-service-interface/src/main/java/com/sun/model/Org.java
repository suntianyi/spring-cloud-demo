package com.sun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Org implements Serializable {
    private static final long serialVersionUID = -1;

    private String id;
    private String name;
    private String orgType;
    private String location;
    private String logo;
    private String img;
    private String info;
}
