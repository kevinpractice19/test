package com.newnoa.govern.controller;

import com.baozun.framework.entity.PageInfo;
import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.vo.RoleVo;
import com.newnoa.govern.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @ApiOperation(value = "分页查询角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "分页数", required = false, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/pageRole.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<PageInfo<RoleVo>> pageRole(HttpServletRequest request) {
        int pageNum = ObjectUtils.objectToInt(request.getParameter("pageNum"));
        int pageSize = ObjectUtils.objectToInt(request.getParameter("pageSize"));
        return this.roleService.selectRole(pageNum, pageSize);
    }


    @ApiOperation(value = "根据角色id查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "long"),
    })
    @RequestMapping(value = "/selectRoleById.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<RoleVo> selectRoleById(HttpServletRequest request) {
        long roleId = ObjectUtils.objectToLong(request.getParameter("roleId"));
        return this.roleService.selectRoleById(roleId);
    }


    @ApiOperation(value = "创建角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "角色备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/createRole.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<RoleVo> createRole(HttpServletRequest request) {
        String menuName = request.getParameter("menuName");
        RoleVo roleVo = new RoleVo(request);
        return this.roleService.insertRole(roleVo, menuName);
    }


    @ApiOperation(value = "根据角色id删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "path", dataType = "long"),
    })
    @RequestMapping(value = "/removeRoleById.do/{roleId}", method = RequestMethod.DELETE, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<Boolean> removeRoleById(@PathVariable("roleId") long roleId) {
        return this.roleService.deleteRoleById(roleId);
    }


    @ApiOperation(value = "根据角色id更新角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "roleName", value = "角色名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "角色备注", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/modifyRoleById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<RoleVo> modifyRoleById(HttpServletRequest request) {
        RoleVo roleVo = new RoleVo(request);
        return this.roleService.updateRoleById(roleVo);
    }
}
