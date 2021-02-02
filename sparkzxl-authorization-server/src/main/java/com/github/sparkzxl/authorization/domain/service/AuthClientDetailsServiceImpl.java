package com.github.sparkzxl.authorization.domain.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.sparkzxl.authorization.application.service.IAuthClientDetailsService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthClientDetails;
import com.github.sparkzxl.authorization.infrastructure.mapper.AuthClientDetailsMapper;
import org.springframework.stereotype.Service;

/**
 * description: 应用客户端 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:35:59
 */
@Service
public class AuthClientDetailsServiceImpl extends ServiceImpl<AuthClientDetailsMapper, AuthClientDetails> implements IAuthClientDetailsService {

}
