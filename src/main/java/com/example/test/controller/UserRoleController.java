package com.newnoa.govern.controller;

import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.vo.UserRoleVo;
import com.newnoa.govern.service.UserRoleService;
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
@RequestMapping("/userRole")
@Slf4j
public class UserRoleController {

        @Resource(name = "userRoleService")
        private UserRoleService userRoleService;



        @ApiOperation("给用户新增角色")
        @ApiImplicitParams({
                        @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
                        @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", required = true, dataType = "String")

        })
        @RequestMapping(value = "/createUserRole.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
        public ResultJson<UserRoleVo> createUserRole(HttpServletRequest request) {
                long userId = ObjectUtils.objectToLong(request.getParameter("userId"));
                String[] roleName = request.getParameter("roleName").split("&");
                List<String> roleNameList = new ArrayList<>();
                Collections.addAll(roleNameList, roleName);
                return userRoleService.insertOrUpdateUserRole(userId, roleNameList);
        }
}
