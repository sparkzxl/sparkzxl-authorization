package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.infrastructure.entity.TenantClient;
import com.github.sparkzxl.authorization.infrastructure.mapper.TenantClientMapper;
import com.github.sparkzxl.authorization.application.service.ITenantClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 租户客户端服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:44:43
*/
@Service
public class TenantClientServiceImpl extends ServiceImpl<TenantClientMapper, TenantClient> implements ITenantClientService {

}
