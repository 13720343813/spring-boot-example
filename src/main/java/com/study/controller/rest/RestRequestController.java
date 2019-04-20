package com.study.controller.rest;

import com.study.common.Constants;
import com.study.utils.JsonUtils;
import com.study.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("rest_request")
@RestController
public class RestRequestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("get_products")
    public void getProducts() {
        RestUtils<String> restUtils = new RestUtils<String>(restTemplate);
        String url = Constants.requestUrl + Constants.GET_PRODUCTS;
        Map<String, Object> params = new HashMap<>();
        params.put("name", "test");
        System.out.println(JsonUtils.toJson(restUtils.request(url, params, HttpMethod.GET)));
    }

    @RequestMapping("get_product_details")
    public void getProductDetails() {
        RestUtils<String> restUtils = new RestUtils<String>(restTemplate);
        String url = Constants.requestUrl + Constants.GET_PRODUCT_DETAILS;
        url = url.replaceAll("\\{code\\}", "123456");
        System.out.println(JsonUtils.toJson(restUtils.request(url, null, HttpMethod.GET)));
    }

    @RequestMapping("update_product")
    public void updateProduct() {
        RestUtils<String> restUtils = new RestUtils<String>(restTemplate);
        String url = Constants.requestUrl + Constants.UPDATE_PRODUCTS;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id", 3);
        data.put("code", "CC33");
        params.put("data", data);
        System.out.println(JsonUtils.toJson(restUtils.request(url, params, HttpMethod.POST)));
    }
}
