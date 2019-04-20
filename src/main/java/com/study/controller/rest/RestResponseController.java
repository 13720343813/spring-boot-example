package com.study.controller.rest;

import com.study.orm.Product;
import com.study.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest")
public class RestResponseController {

    @RequestMapping(value = "get_products", method = RequestMethod.GET)
    public List<Product> getProducts(@RequestParam("name") String name) {
        System.out.println("name : " + name);
        List<Product> products = Arrays.asList(
                new Product(1L, "AA", 10.2D, "AA11"),
                new Product(2L, "BB", 115D, "BB22")
        );
        return products;
    }

    @RequestMapping(value = "get_product_details/{code}", method = RequestMethod.GET)
    public Product getProductDetails(@PathVariable("code") String code) {
        System.out.println("code : " + code);
        return new Product(1L, "AA", 10.2D, "AA11");
    }

    @RequestMapping(value = "update_product", method = RequestMethod.POST)
    public String updateProduct(@RequestBody Map<String, Object> requestMap) {
        System.out.println(JsonUtils.toJson(requestMap));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "update success");
        return JsonUtils.toJson(response);
    }
}
