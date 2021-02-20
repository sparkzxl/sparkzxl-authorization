package com.github.sparkzxl.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsUpdateDTO;
import com.github.sparkzxl.database.base.service.SuperService;
import com.github.sparkzxl.database.dto.DeleteDTO;

/**
 * description: 应用客户端 服务类
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:36:11
 */
public interface IOauthClientDetailsService extends SuperService<OauthClientDetails> {

    /**
     * 保存应用客户端信息
     *
     * @param oauthClientDetailsSaveDTO 应用客户端保存DTO
     * @return boolean
     */
    boolean saveOauthClientDetails(OauthClientDetailsSaveDTO oauthClientDetailsSaveDTO);

    /**
     * 获取客户端分页
     *
     * @param oauthClientDetailsPageDTO 应用客户端分页DTO
     * @return PageInfo<OauthClientDetails>
     */
    PageInfo<OauthClientDetails> listPage(OauthClientDetailsPageDTO oauthClientDetailsPageDTO);

    /**
     * 删除客户端
     *
     * @param deleteDTO 删除对象
     * @return boolean
     */
    boolean deleteClient(DeleteDTO deleteDTO);

    /**
     * 更新客户端信息
     *
     * @param oauthClientDetailsUpdateDTO 应用客户端更新DTO
     * @return boolean
     */
    boolean updateOauthClientDetails(OauthClientDetailsUpdateDTO oauthClientDetailsUpdateDTO);

}
