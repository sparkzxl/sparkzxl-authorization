package com.github.sparkzxl.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
        String clientSecret = oauthClientDetails.getClientSecret();
        TenantClient tenantClient = new TenantClient();
        tenantClient.setClientId(oauthClientDetails.getClientId());
        tenantClient.setTenantCode(tenantCode);
        tenantClient.setOriginalClientSecret(clientSecret);
        tenantClientMapper.insert(tenantClient);
        String encryptClientSecret = passwordEncoder.encode(clientSecret);
        oauthClientDetails.setClientSecret(encryptClientSecret);
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

    @Override
    public boolean deleteClient(List<Long> ids) {
        List<TenantClient> tenantClients = tenantClientMapper.selectList(new LambdaQueryWrapper<TenantClient>().in(TenantClient::getId, ids));
        List<Serializable> clientIds = tenantClients.stream().map(TenantClient::getClientId).collect(Collectors.toList());
        tenantClientMapper.deleteBatchIds(ids);
        return clientDetailsMapper.deleteBatchIds(clientIds) > 0;
    }

    @Override
    public boolean updateOauthClientDetails(Long id, OauthClientDetails oauthClientDetails) {
        String tenantCode = BaseContextHandler.getTenant();
        if (StringUtils.isEmpty(tenantCode)) {
            SparkZxlExceptionAssert.businessFail("租户信息为空");
        }
        String clientSecret = oauthClientDetails.getClientSecret();
        TenantClient tenantClient = new TenantClient();
        tenantClient.setId(id);
        tenantClient.setClientId(oauthClientDetails.getClientId());
        tenantClient.setTenantCode(tenantCode);
        tenantClient.setOriginalClientSecret(clientSecret);
        tenantClientMapper.updateById(tenantClient);
        String encryptClientSecret = passwordEncoder.encode(clientSecret);
        oauthClientDetails.setClientSecret(encryptClientSecret);
        return clientDetailsMapper.updateById(oauthClientDetails) == 1;
    }
}
