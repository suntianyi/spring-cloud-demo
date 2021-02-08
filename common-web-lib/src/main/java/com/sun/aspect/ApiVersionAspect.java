package com.sun.aspect;

import com.sun.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ApiVersionAspect {

    @Before("@annotation(apiVersion)")
    public void before(ApiVersion apiVersion) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String version = request.getHeader("version");
        Assert.state(!StringUtils.isEmpty(version) && Integer.parseInt(version) >= apiVersion.version(),
                "版本过低，请及时升级版本");
    }
}
