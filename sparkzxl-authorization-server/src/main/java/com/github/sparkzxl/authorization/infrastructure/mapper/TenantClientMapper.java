package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantClient;
import org.springframework.stereotype.Repository;

/**
 * description: 租户客户端Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:43:16
 */
@Repository
public interface TenantClientMapper extends BaseMapper<TenantClient> {

}
