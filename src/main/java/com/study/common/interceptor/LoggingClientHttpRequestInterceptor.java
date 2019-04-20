package com.study.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static Logger log = LoggerFactory
            .getLogger(LoggingClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(httpRequest,body);
        ClientHttpResponse response = execution.execute(httpRequest, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        try {
            log.debug(
                    "Request URI:{}, Request Body:{}",
                    request.getURI(), new String(body, "UTF-8"));
            // log.info("===========================request begin================================================");
            // log.info("request URI         : {}", request.getURI());
            // log.info("request Method      : {}", request.getMethod());
            // log.info("request Headers     : {}", request.getHeaders() );
            // log.info("request Request body: {}", new String(body, "UTF-8"));
            // log.info("==========================request end================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
        }
        log.debug("============================response begin==========================================");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("Response body: {}", inputStringBuilder.toString());//WARNING: comment out in production to improve performance
        log.debug("=======================response end=================================================");
    }
}
