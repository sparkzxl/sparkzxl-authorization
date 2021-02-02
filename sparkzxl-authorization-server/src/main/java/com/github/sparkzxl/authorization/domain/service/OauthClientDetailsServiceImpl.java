package com.github.sparkzxl.authorization.domain.service;

import com.github.sparkzxl.authorization.application.service.IOauthClientDetailsService;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.infrastructure.mapper.OauthClientDetailsMapper;
import com.github.sparkzxl.database.base.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 应用客户端 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:35:59
 */
@Service
public class OauthClientDetailsServiceImpl extends SuperServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
