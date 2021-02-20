package com.github.sparkzxl.authorization.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.IOauthClientDetailsRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantClient;
import com.github.sparkzxl.authorization.infrastructure.mapper.OauthClientDetailsMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.TenantClientMapper;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.support.SparkZxlExceptionAssert;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: 应用客户端 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:54:44
 */
@Repository
public class OauthClientDetailsRepository implements IOauthClientDetailsRepository {

    @Autowired
    private TenantClientMapper tenantClientMapper;
    @Autowired
    private OauthClientDetailsMapper clientDetailsMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOauthClientDetails(OauthClientDetails oauthClientDetails) {
        String tenantCode = BaseContextHandler.getTenant();
        if (StringUtils.isEmpty(tenantCode)) {
            SparkZxlExceptionAssert.businessFail("租户信息为空");
        }
        TenantClient tenantClient = new TenantClient();
        tenantClient.setClientId(oauthClientDetails.getClientId());
        tenantClient.setTenantCode(tenantCode);
        tenantClientMapper.insert(tenantClient);
        String clientSecret = passwordEncoder.encode(oauthClientDetails.getClientSecret());
        oauthClientDetails.setClientSecret(clientSecret);
        return clientDetailsMapper.insert(oauthClientDetails) == 1;
    }

    @Override
    public PageInfo<OauthClientDetails> listPage(int pageNum, int pageSize, String clientId) {
        String tenantCode = BaseContextHandler.getTenant();
        if (StringUtils.isEmpty(tenantCode)) {
            SparkZxlExceptionAssert.businessFail("租户信息为空");
        }
        if ("0000".equals(tenantCode)) {
            tenantCode = null;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<OauthClientDetails> oauthClientDetailsList = clientDetailsMapper.listPage(tenantCode, clientId);
        return PageInfoUtils.pageInfo(oauthClientDetailsList);
    }
}
