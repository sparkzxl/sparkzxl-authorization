package com.github.sparkzxl.authorization.interfaces.controller.tenant;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.ISpTenantService;
import com.github.sparkzxl.authorization.infrastructure.entity.SpTenant;
import com.github.sparkzxl.authorization.interfaces.dto.station.StationPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * description: 租户管理
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 16:21:52
*/
@RestController
@Api(tags = "租户管理")
@RequestMapping("/tenant")
public class SpTenantController {

    private final ISpTenantService tenantService;

    public SpTenantController(ISpTenantService tenantService) {
        this.tenantService = tenantService;
    }

    @ApiOperation("查询租户列表")
    @PostMapping("/tenants")
    public PageInfo<SpTenant> getTenantPageList(@RequestBody TenantPageDTO tenantPageDTO) {
        return tenantService.getTenantPageList(tenantPageDTO);
    }

    @ApiOperation("新增租户列表")
    @PostMapping("/tenant")
    public boolean saveTenant(@RequestBody TenantSaveDTO tenantSaveDTO) {
        return tenantService.saveTenant(tenantSaveDTO);
    }

    @ApiOperation("修改租户列表")
    @PutMapping("/tenant")
    public boolean updateTenant(@RequestBody TenantUpdateDTO tenantUpdateDTO) {
        return tenantService.updateTenant(tenantUpdateDTO);
    }

}
