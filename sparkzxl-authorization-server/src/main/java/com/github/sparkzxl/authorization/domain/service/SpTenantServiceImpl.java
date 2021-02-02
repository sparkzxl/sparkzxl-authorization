package com.github.sparkzxl.authorization.domain.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.sparkzxl.authorization.application.service.ISpTenantService;
import com.github.sparkzxl.authorization.infrastructure.entity.SpTenant;
import com.github.sparkzxl.authorization.infrastructure.mapper.SpTenantMapper;
import org.springframework.stereotype.Service;

/**
 * description: 租户信息 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:21:08
 */
@Service
public class SpTenantServiceImpl extends ServiceImpl<SpTenantMapper, SpTenant> implements ISpTenantService {

}
