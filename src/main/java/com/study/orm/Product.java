package com.study.orm;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private String code;

    public Product() {
    }

    public Product(Long id, String name, Double price, String code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
