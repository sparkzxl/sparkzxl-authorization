package com.github.sparkzxl.authorization.infrastructure.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.authorization.application.service.ITenantInfoService;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.core.utils.StringHandlerUtils;
import com.github.sparkzxl.open.properties.SecurityProperties;
import com.github.sparkzxl.open.service.OauthService;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: 多租户模式登录前置过滤器
 *
 * @author: zhouxinlei
 * @date: 2021-02-25 10:53:03
 */
@Component
public class TenantCaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private OauthService oauthService;
    @Autowired
    private ITenantInfoService tenantService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String authenticationFormUrl = "/authentication/form";
        if (StringUtils.equals(requestURI, authenticationFormUrl)) {
            String tenantCode = request.getParameter("tenantCode");
            BaseContextHandler.setTenant(tenantCode);
            checkTenantCode(tenantCode);
            String captchaKey = request.getParameter("captchaKey");
            String captchaCode = request.getParameter("captchaCode");
            oauthService.checkCaptcha(captchaKey, captchaCode);
        }
        chain.doFilter(request, response);
    }

    private void checkTenantCode(String tenantCode) {
        boolean success = true;
        if (StringUtils.isNotEmpty(tenantCode)) {
            int count = tenantService.count(new LambdaQueryWrapper<TenantInfo>().eq(TenantInfo::getCode, tenantCode));
            success = count > 0;
        }
        if (!success) {
            SparkZxlExceptionAssert.businessFail("该租户不存在");
        }
    }

}
