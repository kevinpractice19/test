package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRole")
@Slf4j
public class UserRoleController {

//        @Resource(name = "userRoleService")
//        private UserRoleService userRoleService;
//
//
//
//        @ApiOperation("给用户新增角色")
//        @ApiImplicitParams({
//                        @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
//                        @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", required = true, dataType = "String")
//
//        })
//        @RequestMapping(value = "/createUserRole.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//        public ResultJson<UserRoleVo> createUserRole(HttpServletRequest request) {
//                long userId = ObjectUtils.objectToLong(request.getParameter("userId"));
//                String[] roleName = request.getParameter("roleName").split("&");
//                List<String> roleNameList = new ArrayList<>();
//                Collections.addAll(roleNameList, roleName);
//                return userRoleService.insertOrUpdateUserRole(userId, roleNameList);
//        }
}
