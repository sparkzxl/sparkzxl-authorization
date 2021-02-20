package com.github.sparkzxl.authorization.domain.repository;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;

/**
 * description: 应用客户端 仓储类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:51:03
 */
public interface IOauthClientDetailsRepository {

    /**
     * 保存应用客户端
     *
     * @param oauthClientDetails 应用客户端
     * @return boolean
     */
    boolean saveOauthClientDetails(OauthClientDetails oauthClientDetails);

    /**
     * 获取客户端分页
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @param clientId 客户端id
     * @return PageInfo<OauthClientDetails>
     */
    PageInfo<OauthClientDetails> listPage(int pageNum, int pageSize, String clientId);
}
