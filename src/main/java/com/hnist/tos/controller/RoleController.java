package com.hnist.tos.controller;

import com.hnist.tos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pany
 * @date 2020-05-06 19:35
 * @content
 */
@RestController
public class RoleController extends BaseController{
    @Autowired
    RoleService roleService;
}
