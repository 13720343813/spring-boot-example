package com;

import com.study.common.Constants;
import com.study.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Test
    public void contextLoads() {
        String url = Constants.requestUrl + Constants.GET_PRODUCTS;
        final Pattern QUERY_PARAM_PATTERN = Pattern
                .compile("([^&=]+)(=?)([^&]+)?");
        Matcher mc = QUERY_PARAM_PATTERN.matcher(url);
        StringBuilder sb = new StringBuilder(url);
        if (mc.find()) {
            sb.append("&");
            System.out.println("found value : " + mc.group(0));
            System.out.println("found value : " + mc.group(1));
            System.out.println("found value : " + mc.group(2));
            System.out.println("found value : " + mc.group(3));
        } else {
            sb.append("?");
        }


        System.out.println(sb.toString());
    }

    @Test
    public void process() {
        try {
            String cmd = "curl \"www.baidu.com\"";
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            System.out.println(sb.toString());
            process.waitFor();
            System.out.println(process.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processBuilder(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String,String> env = processBuilder.environment();
        for (String key : env.keySet()){
            System.out.println(key + " : " + env.get(key));
        }
    }

}
