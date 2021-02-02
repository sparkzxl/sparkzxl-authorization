package com.github.sparkzxl.authorization.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("auth_client_details")
@ApiModel(value = "OauthClientDetails对象", description = "")
public class AuthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("app_key")
    private String appKey;

    @TableField("app_secret")
    private String appSecret;

    @TableField("resource_ids")
    private String resourceIds;

    @TableField("scope")
    private String scope;

    @TableField("grant_types")
    private String grantTypes;

    @TableField("redirect_url")
    private String redirectUrl;

    @TableField("authorities")
    private String authorities;

    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @TableField("additional_information")
    private String additionalInformation;

    @TableField("auto_approve")
    private String autoApprove;

    @TableField("enabled")
    private boolean enabled;
}
