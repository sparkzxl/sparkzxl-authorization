package com.github.sparkzxl.authorization.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.IOauthClientDetailsRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.infrastructure.mapper.OauthClientDetailsMapper;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: 客户端 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:54:44
 */
@Repository
public class OauthClientDetailsRepository implements IOauthClientDetailsRepository {

    @Autowired
    private OauthClientDetailsMapper clientDetailsMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOauthClientDetails(OauthClientDetails oauthClientDetails) {
        if (StringUtils.isEmpty(oauthClientDetails.getClientId())) {
            SparkZxlExceptionAssert.businessFail(400, "客户端id不能为空");
        }
        if (StringUtils.isEmpty(oauthClientDetails.getClientSecret())) {
            SparkZxlExceptionAssert.businessFail(400, "客户端id不能为空");
        }
        if (StringUtils.isEmpty(oauthClientDetails.getAuthorizedGrantTypes())) {
            SparkZxlExceptionAssert.businessFail(400, "授权类型不能为空");
        }
        if (ObjectUtils.isEmpty(oauthClientDetails.getAccessTokenValidity())) {
            SparkZxlExceptionAssert.businessFail(400, "令牌时效不能为空");
        }
        if (ObjectUtils.isEmpty(oauthClientDetails.getRefreshTokenValidity())) {
            SparkZxlExceptionAssert.businessFail(400, "令牌刷新时效不能为空");
        }
        String clientSecret = oauthClientDetails.getClientSecret();
        String encryptClientSecret = passwordEncoder.encode(clientSecret);
        oauthClientDetails.setClientSecret(encryptClientSecret);
        clientDetailsMapper.insert(oauthClientDetails);
    }

    @Override
    public void deleteClient(List<String> ids) {
        clientDetailsMapper.deleteBatchIds(ids);
    }


    @Override
    public List<OauthClientDetails> findListByIdList(List<String> ids) {
        return clientDetailsMapper.selectBatchIds(ids);
    }

    @Override
    public void updateOauthClientDetails(OauthClientDetails oauthClientDetails) {
        if (StringUtils.isEmpty(oauthClientDetails.getClientId())) {
            SparkZxlExceptionAssert.businessFail(400, "客户端id不能为空");
        }
        String clientSecret = oauthClientDetails.getClientSecret();
        String encryptClientSecret = passwordEncoder.encode(clientSecret);
        oauthClientDetails.setClientSecret(encryptClientSecret);
        clientDetailsMapper.updateById(oauthClientDetails);
    }
}
