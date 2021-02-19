package com.github.sparkzxl.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.repository.ISpTenantRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.IdSegment;
import com.github.sparkzxl.authorization.infrastructure.entity.SpTenant;
import com.github.sparkzxl.authorization.infrastructure.mapper.IdSegmentMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.SpTenantMapper;
import com.github.sparkzxl.core.utils.DateUtils;
import com.github.sparkzxl.database.entity.SuperEntity;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * description: 租户仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2021-02-14 10:13:48
 */
@Repository
public class SpTenantRepository implements ISpTenantRepository {

    @Autowired
    private SpTenantMapper tenantMapper;
    @Autowired
    private IdSegmentMapper idSegmentMapper;

    @Override
    public PageInfo<SpTenant> getTenantPageList(int pageNum, int pageSize, String code, String name) {
        LambdaQueryWrapper<SpTenant> tenantLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            tenantLambdaQueryWrapper.eq(SpTenant::getCode, code);
        }
        if (StringUtils.isNotEmpty(name)) {
            tenantLambdaQueryWrapper.likeLeft(SpTenant::getName, name);
        }
        tenantLambdaQueryWrapper.orderByAsc(SpTenant::getCode);
        PageHelper.startPage(pageNum, pageSize);
        List<SpTenant> tenantList = tenantMapper.selectList(tenantLambdaQueryWrapper);
        return PageInfoUtils.pageInfo(tenantList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTenant(SpTenant tenant) {
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

    @Override
    public boolean updateTenant(SpTenant tenant) {
        return tenantMapper.updateById(tenant) != 0;
    }

    @Override
    public boolean deleteTenant(List<Long> ids) {
        return tenantMapper.deleteBatchIds(ids) != 0;
    }
}
