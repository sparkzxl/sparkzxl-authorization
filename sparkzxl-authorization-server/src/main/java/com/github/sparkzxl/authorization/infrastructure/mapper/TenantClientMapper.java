package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantClient;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 租户客户端Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:43:16
 */
@Repository
public interface TenantClientMapper extends BaseMapper<TenantClient> {

    /**
     * 根据租户code删除租户客户端
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from tenant_client where tenant_code = #{tenantCode}")
    void deleteTenantClient(String tenantCode);
}
