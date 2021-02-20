package com.github.sparkzxl.authorization.infrastructure.repository;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.domain.repository.IAuthMenuRepository;
import com.github.sparkzxl.authorization.domain.repository.IIdSegmentRepository;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authorization.infrastructure.entity.RoleAuthority;
import com.github.sparkzxl.authorization.infrastructure.entity.UserRole;
import com.github.sparkzxl.authorization.infrastructure.mapper.AuthMenuMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.RoleAuthorityMapper;
import com.github.sparkzxl.authorization.infrastructure.mapper.UserRoleMapper;
import com.github.sparkzxl.core.tree.TreeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 菜单 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
@Repository
public class AuthMenuRepository implements IAuthMenuRepository {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthMenuMapper authMenuMapper;
    @Autowired
    private IIdSegmentRepository segmentRepository;
    @Autowired
    private AuthResourceRepository authResourceRepository;


    @Override
    public List<MenuBasicInfo> getAuthMenuList(Long userId) {
        List<Long> roleIds =
                userRoleMapper.selectList(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, userId)).stream().map(UserRole::getRoleId)
                        .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<RoleAuthority> roleAuthorities =
                    roleAuthorityMapper.selectList(new LambdaQueryWrapper<RoleAuthority>().in(RoleAuthority::getRoleId, roleIds)
                            .groupBy(RoleAuthority::getAuthorityId, RoleAuthority::getAuthorityType, RoleAuthority::getRoleId));
            List<Long> menuIds = roleAuthorities.stream().filter(x -> "MENU".equals(x.getAuthorityType()))
                    .map(RoleAuthority::getAuthorityId).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(menuIds)) {
                List<AuthMenu> menuList = authMenuMapper.selectBatchIds(menuIds);
                menuList = menuList.stream().sorted(Comparator.comparing(AuthMenu::getSortValue)).collect(Collectors.toList());
                List<MenuBasicInfo> menuBasicInfos = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(menuList)) {
                    menuList.forEach(menu -> {
                        MenuBasicInfo menuBasicInfo = new MenuBasicInfo();
                        menuBasicInfo.setId(menu.getId());
                        menuBasicInfo.setLabel(menu.getLabel());
                        menuBasicInfo.setIcon(menu.getIcon());
                        menuBasicInfo.setPath(menu.getPath());
                        menuBasicInfo.setRedirect(menu.getRedirect());
                        menuBasicInfo.setComponent(menu.getComponent());
                        menuBasicInfo.setComponentName(menu.getComponentName());
                        menuBasicInfo.setHidden(menu.isHidden());
                        menuBasicInfo.setNoKeepAlive(menu.isNoKeepAlive());
                        menuBasicInfo.setParentId(menu.getParentId());
                        menuBasicInfo.setSortValue(menu.getSortValue());
                        menuBasicInfos.add(menuBasicInfo);
                    });
                    return TreeUtils.buildTree(menuBasicInfos);
                }
            }
        }
        return Lists.newArrayList();
    }


    @Override
    public void saveAuthMenus(List<AuthMenu> authMenus) {
        authMenus.forEach(authMenu -> {
            if (authMenu.getParentId().equals(0L)) {
                long id = segmentRepository.getIdSegment("auth_menu").longValue();
                authMenu.setId(id);
                authMenuMapper.insert(authMenu);
                saveNodeMenu(id, authMenu.getChildren());
            }
        });
    }

    private void saveNodeMenu(Long parentId, List<AuthMenu> authMenus) {
        if (CollectionUtils.isNotEmpty(authMenus)) {
            for (AuthMenu authMenu : authMenus) {
                authMenu.setParentId(parentId);
                long id = segmentRepository.getIdSegment("auth_menu").longValue();
                authMenu.setId(id);
                authMenuMapper.insert(authMenu);
                List<AuthResource> resourceList = authMenu.getResourceList();
                resourceList.forEach(resource-> resource.setMenuId(id));
                if (CollectionUtils.isNotEmpty(resourceList)) {
                    authResourceRepository.saveResourceList(resourceList);
                }
                Long nodeParentId = authMenu.getId();
                saveNodeMenu(nodeParentId, authMenu.getChildren());
            }
        }
    }

    @Override
    public List<AuthMenu> findAuthMenuList() {
        return authMenuMapper.selectList(null);
    }
}
