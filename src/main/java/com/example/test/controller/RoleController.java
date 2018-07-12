package com.example.test.controller;

import com.example.test.entity.dto.RoleCreateDTO;
import com.example.test.entity.dto.RolePageDTO;
import com.example.test.entity.vo.RoleVo;
import com.example.test.service.RoleService;
import com.example.test.utils.Constant;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @ApiOperation(value = "分页查询角色信息")
    @RequestMapping(value = "/pageRole.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<PageInfo<RoleVo>> pageRole(RolePageDTO pageDTO) {
        return this.roleService.selectRole(pageDTO);
    }



    @RequestMapping(value = "/selectRoleById.do", method = RequestMethod.GET)
    public ResultJson<RoleVo> selectRoleById(@RequestParam("roleId") long roleId) {
        return this.roleService.selectRoleById(roleId);
    }


    @ApiOperation(value = "创建角色")
    @RequestMapping(value = "/createRole.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<RoleVo> createRole(@RequestBody RoleCreateDTO createDTO) {
        return this.roleService.insertRole(createDTO);
    }


    @ApiOperation(value = "根据角色id删除角色")
    @RequestMapping(value = "/removeRoleById.do/{roleId}", method = RequestMethod.DELETE, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<Boolean> removeRoleById(@PathVariable("roleId") long roleId) {
        return this.roleService.deleteRoleById(roleId);
    }


//    @ApiOperation(value = "根据角色id更新角色")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "long"),
//            @ApiImplicitParam(name = "roleName", value = "角色名", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "remark", value = "角色备注", required = true, paramType = "query", dataType = "String"),
//    })
//    @RequestMapping(value = "/modifyRoleById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<RoleVo> modifyRoleById(HttpServletRequest request) {
//        RoleVo roleVo = new RoleVo(request);
//        return this.roleService.updateRoleById(roleVo);
//    }
}
