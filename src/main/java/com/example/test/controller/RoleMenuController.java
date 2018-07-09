package com.newnoa.govern.controller;

import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.vo.RoleMenuVo;
import com.newnoa.govern.service.RoleMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        @ApiImplicitParams({
                        @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "long"),
                        @ApiImplicitParam(name = "menuName", value = "菜单名称", required = true, paramType = "query", dataType = "String")

        })
        @RequestMapping(value = "/createRoleMenu.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
        public ResultJson<RoleMenuVo> createRoleMenu(HttpServletRequest request) {
                long roleId = ObjectUtils.objectToLong(request.getParameter("roleId"));
                String[] menuName = ObjectUtils.objectToString(request.getParameter("menuName")).split("&");
                List<String> menuNameList = new ArrayList<>();
                Collections.addAll(menuNameList, menuName);
                return this.roleMenuService.insertOrUpdateRoleMenu(roleId, menuNameList);
        }

}
