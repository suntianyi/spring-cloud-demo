package com.sun.filter;

import com.alibaba.fastjson.JSON;
import com.sun.constants.MessageConstants;
import com.sun.result.Result;
import com.sun.utils.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Value("${ignore_url_reg}")
    private String ignoreUrlReg;

    private boolean urlIgnore(String url) {
        return Pattern.compile(ignoreUrlReg).matcher(url).find();
    }
    
    private String getToken(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst("token");
        }
        return token;
    }
    

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (urlIgnore(request.getURI().getPath())) {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] data = JSON.toJSONString(Result.fail(MessageConstants.TOKEN_INVALID)).getBytes();
            DataBuffer buffer = response.bufferFactory().wrap(data);
            return response.writeWith(Mono.just(buffer));
        }
        String result = JWTUtil.validateToken(token);
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header("user", result).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}