package com.example.test.controller;

import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.service.RoleMenuService;
import com.example.test.utils.Constant;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/roleMenu")
@Slf4j
public class RoleMenuController {

        @Resource(name = "roleMenuService")
        private RoleMenuService roleMenuService;


        @ApiOperation("新建角色菜单关系")
        @RequestMapping(value = "/createRoleMenu.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
        public ResultJson<RoleMenuVo> createRoleMenu(@RequestBody  RoleMenuCreateDTO createDTO) {
                return this.roleMenuService.insertOrUpdateRoleMenu(createDTO);
        }

}
