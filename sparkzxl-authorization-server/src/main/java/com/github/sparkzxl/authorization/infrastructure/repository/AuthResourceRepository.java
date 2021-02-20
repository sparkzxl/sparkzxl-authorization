package com.github.sparkzxl.authorization.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.authorization.domain.repository.IAuthResourceRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authorization.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.authorization.infrastructure.mapper.AuthResourceMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.RoleAuthorityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 资源 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:28
 */
@AllArgsConstructor
@Repository
public class AuthResourceRepository implements IAuthResourceRepository {

    private final AuthResourceMapper authResourceMapper;
    private final RoleAuthorityMapper roleAuthorityMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    public static final String RESOURCE_ROLES_MAP = "auth:resource_roles_map";

    @Override
    public List<AuthResource> authResourceList() {
        return authResourceMapper.selectList(null);
    }

    @Override
    public List<AuthResource> findVisibleResource(Long userId, Long menuId) {
        return authResourceMapper.findVisibleResource(userId, menuId);
    }

    @Override
    public boolean deleteResource(Long resourceId) {
        roleAuthorityMapper.delete(new LambdaQueryWrapper<RoleAuthority>().eq(RoleAuthority::getAuthorityId, resourceId));
        AuthResource authResource = authResourceMapper.selectById(resourceId);
        redisTemplate.opsForHash().delete(RESOURCE_ROLES_MAP, authResource.getRequestUrl());
        return authResourceMapper.deleteById(resourceId) == 1;
    }
}
