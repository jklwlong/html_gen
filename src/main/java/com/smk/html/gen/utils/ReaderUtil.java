package com.smk.html.gen.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liuwl
 * @date 2019/4/2 17:09
 */
public class ReaderUtil {

    public static Map<String, String> readFile(String path) {
        Map<String, String> map = new HashMap<>();
        try {
            Properties properties = new Properties();
            System.out.println(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            properties.load(bufferedReader);

            map.put("driverClassName", properties.getProperty("driverClassName"));
            map.put("url", properties.getProperty("url"));
            map.put("username", properties.getProperty("username"));
            map.put("password", properties.getProperty("password"));
            map.put("tableName", properties.getProperty("tableName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
