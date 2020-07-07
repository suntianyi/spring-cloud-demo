package com.sun.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResult {

    /**
     * 结果
     */
    private ResultEnum result;

    /**
     * 新token
     */
    private String token;

    /**
     * 信息
     */
    private Map<String, Object> info;

    /**
     * 结果枚举
     */
    public enum ResultEnum {
        /**
         * 新鲜
         */
        FRESH,

        /**
         * 到期
         */
        EXPIRE
    }
}
