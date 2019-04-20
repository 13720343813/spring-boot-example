package com.study.utils;

public class ServiceResponse<T> {
    private Integer code;
    private T result;
    private String description;

    public ServiceResponse() {
        this.code = 200;
        this.description = "success";
    }

    public ServiceResponse(Integer code,String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
