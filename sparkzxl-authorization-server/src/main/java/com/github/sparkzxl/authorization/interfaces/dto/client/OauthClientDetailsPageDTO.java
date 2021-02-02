package com.github.sparkzxl.authorization.interfaces.dto.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: 应用客户端分页DTO
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 14:53:04
 */
@Data
@ApiModel("应用客户端分页DTO")
public class OauthClientDetailsPageDTO {

    @ApiModelProperty("客户端id")
    private String clientId;

    @ApiModelProperty("授权认证类型")
    private String authorizedGrantTypes;

}
