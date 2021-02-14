package com.github.sparkzxl.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.SpTenant;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;

/**
 * description: 租户信息 服务类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:20:51
 */
public interface ISpTenantService extends IService<SpTenant> {

    /**
     * 分页查询租户列表
     *
     * @param tenantPageDTO 租户分页查询参数
     * @return PageInfo<SpTenant>
     */
    PageInfo<SpTenant> getTenantPageList(TenantPageDTO tenantPageDTO);

    /**
     * 保存租户信息
     *
     * @param tenantSaveDTO 租户保存对象
     * @return boolean
     */
    boolean saveTenant(TenantSaveDTO tenantSaveDTO);

    /**
     * 更新租户信息
     *
     * @param tenantUpdateDTO 租户更新对象
     * @return boolean
     */
    boolean updateTenant(TenantUpdateDTO tenantUpdateDTO);
}
