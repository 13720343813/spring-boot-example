package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@RestController
public class AuthenticateController {
    @RequestMapping("authenticate/runScript")
    public String runScript(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter("type");
        if (null == type) {
            System.out.println("type is null");
            return "ok";
        }
        String argList = request.getParameter("args");
        type = type.replace("/", ".");
        String className = "script." + type;
        String methodName = "runScript";
        try {
            Class<?> customClass = Class.forName(className);
            Method method = customClass.getMethod(methodName, String.class);
            Object result = method.invoke(customClass.newInstance(), argList);
            if (null != result) {
                if ((Boolean) result) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
