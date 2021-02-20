package com.github.sparkzxl.authorization.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: 租户客户端关系信息
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:42:43
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tenant_client")
@ApiModel(value="租户客户端关系对象", description="")
public class TenantClient implements Serializable {

    private static final long serialVersionUID = -8899094056701826936L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "租户code")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "客户端id")
    @TableField("client_id")
    private String clientId;

    @ApiModelProperty("客户端原始密钥")
    @TableField("original_client_secret")
    private String originalClientSecret;

}
