package com.sun.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Slf4j
public class HTTPUtils {
    /**
     * 获取IP地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        return ip;
    }

    /**
     * 发送GET请求
     *
     * @param url 服务地址
     * @param obj 参数对象
     */
    public static String getForObject(String url, Object obj) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String param = objToParam(obj);
            log.info("请求地址：{}, 请求参数：{}", url, param);
            return restTemplate.getForObject(url + param, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 发送POST请求，表单方式
     *
     * @param url 服务地址
     * @param obj 参数对象
     */
    public static String postForObject(String url, Object obj) {
        try {
            log.info("请求地址：{}, 请求参数：{}", url, JSON.toJSONString(obj));
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(objToMap(obj), headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 发送POST请求，BODY方式
     *
     * @param url 服务地址
     * @param obj 参数对象
     */
    public static String postBodyForObject(String url, Object obj) {
        try {
            log.info("请求地址：{}, 请求参数：{}", url, JSON.toJSONString(obj));
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, obj, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 发送PUT请求
     *
     * @param url 服务地址
     * @param obj 参数对象
     */
    public static void putForObject(String url, Object obj) {
        try {
            log.info("请求地址：{}, 请求参数：{}", url, JSON.toJSONString(obj));
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(url, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MultiValueMap<String, Object> objToMap(Object object) throws Exception {
        if (object instanceof MultiValueMap) {
            return (MultiValueMap<String, Object>) object;
        }
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        if (object instanceof Map) {
            ((Map<String, Object>) object).forEach((k, v) -> multiValueMap.add(k, v));
            return multiValueMap;
        }
        if (object != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(object);
                    if (value == null) continue;
                    multiValueMap.add(name, value);
                }
            }
        }
        return multiValueMap;
    }

    private static String objToParam(Object object) throws Exception {
        if (object == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (object instanceof Map) {
            for (Object key : ((Map) object).keySet()) {
                Object value = ((Map) object).get(key);
                if (value == null) continue;
                sb.append("&").append(key).append("=");
                if (value instanceof List) {
                    sb.append(StringUtils.join((List) value, "&" + key + "="));
                } else {
                    sb.append(value.toString());
                }
            }
            if (sb.length() > 0) {
                return sb.replace(0, 1, "?").toString();
            }
        } else {
            Field[] fields = object.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(object);
                    if (value == null) continue;
                    sb.append("&").append(name).append("=");
                    if (value instanceof List) {
                        sb.append(StringUtils.join((List) value, "&" + name + "="));
                    } else {
                        sb.append(value.toString());
                    }
                }
                if (sb.length() > 0) {
                    return sb.replace(0, 1, "?").toString();
                }
            }
        }
        return "";
    }
}
