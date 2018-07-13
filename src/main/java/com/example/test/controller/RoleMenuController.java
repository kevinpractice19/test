package com.example.test.controller;

import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.service.RoleMenuService;
import com.example.test.utils.Constant;
import com.example.test.utils.GovernCenterException;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/roleMenu")
@Slf4j
public class RoleMenuController {

    @Resource(name = "roleMenuService")
    private RoleMenuService roleMenuService;

    @ApiOperation("给角色添加菜单权限")
    @RequestMapping(value = "/createRoleMenu.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<RoleMenuVo> createRoleMenu(@RequestBody RoleMenuCreateDTO createDTO) {
        try {
            return this.roleMenuService.insertRoleMenu(createDTO);
        } catch (GovernCenterException e) {
            return new ResultJson<>(210, e.getMessage());
        }
    }

    @ApiOperation("删除角色菜单权限关系")
    @RequestMapping(value = "/removeRoleMenuByMenuIdRoleId.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<Boolean> removeRoleMenuByMenuIdRoleId(@RequestParam("menuId") long menuId, @RequestParam("roleId") long roleId) {
        return this.roleMenuService.deleteRoleMenuByMenuIdRoleId(menuId, roleId);
    }

}
