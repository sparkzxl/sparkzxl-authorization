package com.github.sparkzxl.authorization.infrastructure.config;

import com.github.sparkzxl.core.resource.SwaggerStaticResource;
import com.github.sparkzxl.core.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;

/**
 * description: 资源服务器
 *
 * @author: zhouxinlei
 * @date: 2021-02-01 11:30:00
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        List<String> excludeStaticPatterns = SwaggerStaticResource.EXCLUDE_STATIC_PATTERNS;
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and().authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(ListUtils.listToArray(excludeStaticPatterns)).permitAll()
                .anyRequest().authenticated()
                .and()
                //统一自定义异常
                .exceptionHandling()
                .and()
                .csrf().disable();
    }
}
