package com.github.sparkzxl.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.IAuthRoleRepository;
import com.github.sparkzxl.authorization.domain.repository.IAuthUserRepository;
import com.github.sparkzxl.authorization.domain.repository.ITenantInfoRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthRole;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.infrastructure.entity.IdSegment;
import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.infrastructure.mapper.IdSegmentMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.TenantInfoMapper;
import com.github.sparkzxl.core.context.BaseContextHandler;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * description: 租户仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-14 10:13:48
 */
@Repository
public class TenantInfoRepository implements ITenantInfoRepository {

    @Autowired
    private TenantInfoMapper tenantMapper;
    @Autowired
    private IdSegmentMapper idSegmentMapper;
    @Autowired
    private IAuthUserRepository authUserRepository;
    @Autowired
    private IAuthRoleRepository authRoleRepository;

    @Override
    public PageInfo<TenantInfo> getTenantPageList(int pageNum, int pageSize, String code, String name) {
        LambdaQueryWrapper<TenantInfo> tenantLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            tenantLambdaQueryWrapper.eq(TenantInfo::getCode, code);
        }
        if (StringUtils.isNotEmpty(name)) {
            tenantLambdaQueryWrapper.likeLeft(TenantInfo::getName, name);
        }
        tenantLambdaQueryWrapper.orderByAsc(TenantInfo::getCode);
        PageHelper.startPage(pageNum, pageSize);
        List<TenantInfo> tenantList = tenantMapper.selectList(tenantLambdaQueryWrapper);
        return PageInfoUtils.pageInfo(tenantList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTenant(TenantInfo tenant) {
        IdSegment idSegment = idSegmentMapper.selectOne(new LambdaQueryWrapper<IdSegment>().eq(IdSegment::getBusinessTag, "tenant_code"));
        BigDecimal maxIdDecimal = new BigDecimal(idSegment.getMaxId().toString());
        BigDecimal stepDecimal = new BigDecimal(idSegment.getStep().toString());
        maxIdDecimal.add(stepDecimal);
        BigDecimal sumDecimal = maxIdDecimal.add(stepDecimal);
        String tenantCode = sumDecimal.toString();
        tenant.setCode(tenantCode);
        Long maxId = sumDecimal.longValue();
        idSegment.setMaxId(maxId);
        idSegment.setUpdateTime(DateUtils.toLocalDateTime(new Date()));
        idSegmentMapper.updateById(idSegment);
        return tenantMapper.insert(tenant) != 0;
    }

    private void initTenantData(String tenantCode) {
        BaseContextHandler.setTenant(tenantCode);
        AuthUser authUser = new AuthUser();
        authUser.setAccount("admin");
        authUser.setPassword("admin");
        authUser.setName("管理员");
        authUser.setTenantCode(tenantCode);
        authUserRepository.saveAuthUserInfo(authUser);
        AuthRole authRole = new AuthRole();
        authRole.setCode("ADMIN");
        authRole.setName("管理员");
        authRole.setDescribe("内置管理员");
        authRole.setReadonly(true);
        authRole.setDsType("ALL");
        authRoleRepository.saveRole(authRole);
    }

    @Override
    public boolean updateTenant(TenantInfo tenant) {
        return tenantMapper.updateById(tenant) != 0;
    }

    @Override
    public boolean deleteTenant(List<Long> ids) {
        return tenantMapper.deleteBatchIds(ids) != 0;
    }
}
