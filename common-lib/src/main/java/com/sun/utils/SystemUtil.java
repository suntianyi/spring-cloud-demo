package com.sun.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SystemUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> objToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (object != null) {
                Field[] fields = object.getClass().getDeclaredFields();
                if (fields.length > 0) {
                    for (Field field : fields) {
                        String name = field.getName();
                        if ("serialVersionUID".equals(name)) {
                            continue;
                        }
                        field.setAccessible(true);
                        Object value = field.get(object);
                        if (value == null) continue;
                        map.put(name, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> T copy(Object object, Class<T> clazz) {
        if (object == null) return null;

        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Object t = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    String field = method.getName().substring(3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    for (Field f : fields) {
                        if (field.equals(f.getName())) {
                            f.setAccessible(true);
                            Object value = f.get(object);
                            if (value == null) continue;
                            method.invoke(t, value);
                        }
                    }
                }
            }
            return (T) t;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
