package com.github.sparkzxl.authorization.domain.repository;


import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;

import java.util.List;

/**
 * description: 菜单 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:12
 */
public interface IAuthMenuRepository {

    /**
     * 获取菜单列表
     *
     * @param userId 用户id
     * @return List<MenuBasicInfo>
     */
    List<MenuBasicInfo> getAuthMenuList(Long userId);

    /**
     * 保存菜单信息
     *
     * @param authMenus 菜单集合
     * @return boolean
     */
    boolean saveAuthMenus(List<AuthMenu> authMenus);
}
