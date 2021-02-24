package com.github.sparkzxl.authorization.interfaces.controller.oauth;

import com.github.sparkzxl.core.entity.CaptchaInfo;
import com.github.sparkzxl.core.utils.RequestContextHolderUtils;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.open.entity.AuthorizationRequest;
import com.github.sparkzxl.open.service.OauthService;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;


/**
 * description：授权登录管理
 *
 * @author zhouxinlei
 * @date 2020/6/6 9:08 上午
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "授权管理")
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/oauth/token")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3Bhcmt6eGw6MTIzNDU2")
    )
    public OAuth2AccessToken getAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                            Principal principal,
                                            @RequestParam AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        log.info("Authorization = {}", authorization);
        return oauthService.getAccessToken(principal, authorizationRequest);
    }


    @PostMapping("/oauth/token")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Basic Auth", paramType = "header", defaultValue = "Basic c3Bhcmt6eGw6MTIzNDU2")
    )
    public OAuth2AccessToken postAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                             @ApiIgnore Principal principal,
                                             @RequestBody AuthorizationRequest authorizationRequest)
            throws HttpRequestMethodNotSupportedException {
        log.info("Authorization = {}", authorization);
        return oauthService.postAccessToken(principal, authorizationRequest);
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/oauth/captcha")
    public CaptchaInfo captcha(@RequestParam(value = "type") String type) {
        return oauthService.createCaptcha(type);
    }

    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @GetMapping(value = "/oauth/check")
    public boolean checkCaptcha(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        return oauthService.checkCaptcha(key, code);
    }

    @GetMapping("/oauth/getAuthorizeUrl")
    public String getAuthorizeUrl() {
        return oauthService.getAuthorizeUrl();
    }

    @GetMapping("/oauth/callBack")
    public OAuth2AccessToken callBack(String code) {
        return oauthService.callBack(code);
    }

}
