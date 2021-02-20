package com.github.sparkzxl.authorization.domain.repository;


import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;

import java.util.List;

/**
 * description: 资源 仓储类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:31:28
 */
public interface IAuthResourceRepository {

    /**
     * 加载所有资源
     *
     * @return List<AuthResource>
     */
    List<AuthResource> authResourceList();


    /**
     * 获取用户资源
     *
     * @param userId
     * @param menuId
     * @return List<AuthResource>
     */
    List<AuthResource> findVisibleResource(Long userId, Long menuId);

    /**
     * 删除资源
     *
     * @param resourceId 资源id
     * @return boolean
     */
    boolean deleteResource(Long resourceId);

    /**
     * 保存资源信息列表
     * @param resourceList 资源信息列表
     * @return boolean
     */
    boolean saveResourceList(List<AuthResource> resourceList);
}
