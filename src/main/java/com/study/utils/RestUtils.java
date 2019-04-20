package com.study.utils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

public class RestUtils<T> {
    private RestTemplate restTemplate;

    public RestUtils() {
        super();
    }

    public RestUtils(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    public ServiceResponse<T> request(String url, Map<String, Object> params, HttpMethod httpMethod) {
        ServiceResponse<T> serviceResponse = new ServiceResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
            MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype());
            headers.setContentType(mediaType);
            HttpEntity<Object> entity = null;
            if (params == null) {
                entity = new HttpEntity<>(headers);
            } else {
                if (params.containsKey("data")) {
                    entity = new HttpEntity<>(params.get("data"), headers);
                    params.remove("data");
                } else {
                    entity = new HttpEntity<>(params, headers);
                }
            }
            ResponseEntity<T> responseEntity = null;
            if (params == null || params.isEmpty()) {
                responseEntity = restTemplate.exchange(url, httpMethod, entity, new ParameterizedTypeReference<T>() {
                });
            } else {
                responseEntity = restTemplate.exchange(expandURL(url, params.keySet()), httpMethod, entity, new ParameterizedTypeReference<T>() {
                }, params);
            }
            serviceResponse.setResult(responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse.setCode(500);
            serviceResponse.setDescription(e.getMessage());
        }
        return serviceResponse;
    }

    private String expandURL(String url, Set<?> keys) {
        StringBuffer sb = new StringBuffer(url);
        if (url.indexOf("?") > 0) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        for (Object key : keys) {
            sb.append(key).append("=").append("{").append(key).append("}")
                    .append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
