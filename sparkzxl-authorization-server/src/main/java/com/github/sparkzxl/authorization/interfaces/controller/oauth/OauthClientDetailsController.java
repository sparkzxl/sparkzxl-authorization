package com.github.sparkzxl.authorization.interfaces.controller.oauth;


import com.github.sparkzxl.authorization.application.service.IAuthUserService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 应用客户端管理
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:31:18
 */
@RestController
@Api(tags = "应用客户端管理")
@RequestMapping("/oauth/client")
public class OauthClientDetailsController extends SuperCacheController<IAuthUserService, Long,
        AuthUser, AuthUserPageDTO, AuthUserSaveDTO, AuthUserUpdateDTO> {

}
