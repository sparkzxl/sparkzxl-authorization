package com.github.sparkzxl.authorization.interfaces.controller.oauth;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.IOauthClientDetailsService;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperController;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 应用客户端管理
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:31:18
 */
@RestController
@Api(tags = "应用客户端管理")
@ResponseResult
@RequestMapping("/client")
public class OauthClientDetailsController extends SuperController<IOauthClientDetailsService, String,
        OauthClientDetails, OauthClientDetailsPageDTO, OauthClientDetailsSaveDTO, OauthClientDetailsUpdateDTO> {


    @ApiOperation("保存客户端信息")
    @PostMapping("saveClient")
    public boolean saveOauthClientDetails(@RequestBody OauthClientDetailsSaveDTO oauthClientDetailsSaveDTO) {
        return baseService.saveOauthClientDetails(oauthClientDetailsSaveDTO);
    }

    @ApiOperation("获取客户端分页")
    @PostMapping("/listPage")
    public PageInfo<OauthClientDetails> listPage(@RequestBody OauthClientDetailsPageDTO oauthClientDetailsPageDTO) {
        return baseService.listPage(oauthClientDetailsPageDTO);
    }
}
