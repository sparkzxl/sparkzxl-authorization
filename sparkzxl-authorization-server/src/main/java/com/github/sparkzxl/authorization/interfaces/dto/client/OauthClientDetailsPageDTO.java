package com.github.sparkzxl.authorization.interfaces.dto.client;

import com.github.sparkzxl.database.dto.PageDTO;
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
public class OauthClientDetailsPageDTO extends PageDTO {

    @ApiModelProperty("客户端id")
    private String clientId;

}
