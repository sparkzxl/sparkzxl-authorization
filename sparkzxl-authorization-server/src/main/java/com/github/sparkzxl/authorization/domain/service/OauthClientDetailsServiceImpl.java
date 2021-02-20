package com.github.sparkzxl.authorization.domain.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.IOauthClientDetailsService;
import com.github.sparkzxl.authorization.domain.repository.IOauthClientDetailsRepository;
import com.github.sparkzxl.authorization.infrastructure.convert.OauthClientDetailsConvert;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.infrastructure.mapper.OauthClientDetailsMapper;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsSaveDTO;
import com.github.sparkzxl.database.base.service.impl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 应用客户端 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:35:59
 */
@Service
public class OauthClientDetailsServiceImpl extends SuperServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    @Autowired
    private IOauthClientDetailsRepository clientDetailsRepository;

    @Override
    public boolean saveOauthClientDetails(OauthClientDetailsSaveDTO oauthClientDetailsSaveDTO) {
        OauthClientDetails oauthClientDetails = OauthClientDetailsConvert.INSTANCE.convertBaseClientDetails(oauthClientDetailsSaveDTO);
        return clientDetailsRepository.saveOauthClientDetails(oauthClientDetails);
    }

    @Override
    public PageInfo<OauthClientDetails> listPage(OauthClientDetailsPageDTO oauthClientDetailsPageDTO) {
        return clientDetailsRepository.listPage(oauthClientDetailsPageDTO.getPageNum(),
                oauthClientDetailsPageDTO.getPageSize(),
                oauthClientDetailsPageDTO.getClientId());
    }
}
