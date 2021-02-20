package com.github.sparkzxl.authorization.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * description: 应用客户端管理
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:34:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
@ApiModel(value = "OauthClientDetails对象", description = "")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 6504265519325579381L;

    @ApiModelProperty("客户端id")
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;

    @ApiModelProperty("资源保护id列表")
    @TableField("resource_ids")
    private String resourceIds;

    @ApiModelProperty("客户端密钥")
    @TableField("client_secret")
    private String clientSecret;

    @ApiModelProperty("授权范围")
    @TableField("scope")
    private String scope;

    @ApiModelProperty("授权认证类型")
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    @ApiModelProperty("授权回调地址")
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    @ApiModelProperty("权限列表")
    @TableField("authorities")
    private String authorities;

    @ApiModelProperty("token有效期")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty("刷新token有效期")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty("自定义信息")
    @TableField("additional_information")
    private String additionalInformation;

    @ApiModelProperty("自动授权")
    @TableField("autoapprove")
    private String autoApprove;

    @ApiModelProperty(value = "主键")
    @TableField(exist = false)
    private Long id;

    @ApiModelProperty(value = "租户code")
    @TableField(exist = false)
    private String tenantCode;

    @ApiModelProperty(value = "租户名称")
    @TableField(exist = false)
    private String tenantName;

    @ApiModelProperty("客户端原始密钥")
    @TableField(exist = false)
    private String originalClientSecret;

}
