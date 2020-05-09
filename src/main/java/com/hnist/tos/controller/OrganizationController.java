package com.hnist.tos.controller;

import com.hnist.tos.entity.Organization;
import com.hnist.tos.exception.TOSException;
import com.hnist.tos.service.OrganizationService;
import com.hnist.tos.utils.CommonResult;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Pany
 * @date 2020-05-06 21:39
 * @content
 */
@RestController
@RequestMapping(value = "/api/organization")
@Validated
public class OrganizationController extends BaseController {
    @Autowired
    OrganizationService organizationService;

    /**
     * 通过ID查询机构信息
     * @param id
     * @return
     * @throws TOSException
     */
    @RequiresAuthentication
    @PostMapping("/getOrganizationInfo/{id}")
    public CommonResult getOrganizationInfo(
            @NotNull(message = "机构ID不能为空")
            @PathVariable(value = "id") Long id
    ) throws TOSException {
        return CommonResult.success(organizationService.findById(id));
    }

    @RequiresAuthentication
    @PostMapping("/getOrganizations")
    public CommonResult getUserInfo(
            int page,
            int size,
            @RequestParam() Map map
    ) throws TOSException {
        return CommonResult.success(organizationService.findAll(page,size,map));
    }

}
