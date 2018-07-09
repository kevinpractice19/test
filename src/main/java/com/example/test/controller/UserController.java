package com.example.test.controller;

import com.example.test.entity.vo.UserVo;
import com.example.test.service.UserService;
import com.example.test.utils.Constant;
import com.example.test.utils.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;


//    @RequestMapping(value = "/pageUser.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<PageInfo<UserVo>> pageUser(UserPageDTO pageDTO) {
//        return this.userService.pageUser(pageDTO);
//    }
//

    @RequestMapping(value = "/selectUserById.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> selectUserById(@RequestParam("userId") long userId) {
        return this.userService.selectUserById(userId);
    }

//
//    @ApiOperation("用户登录")
//    @BaozunApi(name = "用户登录", description = "用户登录", version = "v1.0")
//    @BaozunApiParamClass(entity = UserLoginDTO.class)
//    @RequestMapping(value = "/getLogin.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> getLogin(@RequestBody UserLoginDTO userLoginDTO) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        ResultJson jsonResult = this.userService.getLogin(userLoginDTO);
//        HttpSession session = request.getSession();
//        session.setAttribute("account",userLoginDTO.getAccount());
//        return jsonResult;
//    }
//
//    @ApiOperation("重置密码")
//    @BaozunApi(name = "重置密码", description = "重置密码", version = "v1.0")
//    @BaozunApiParamClass(entity = UserResetPasswordDTO.class)
//    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> resetPassword(@RequestBody UserResetPasswordDTO resetPasswordDTO) {
//        return this.userService.resetPassword(resetPasswordDTO);
//    }
//
//
//    @ApiOperation("根据用户账号查询用户信息")
//    @RequestMapping(value = "/selectUserByAccount.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> selectUserByAccount(@RequestParam("account") String account) {
//        return this.userService.selectUserByAccount(account);
//    }
//
//
//    @ApiOperation("创建用户")
//    @BaozunApi(name = "创建用户", description = "创建用户", version = "v1.0")
//    @BaozunApiParamClass(entity = UserCreateDTO.class)
//    @RequestMapping(value = "/createUser.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> createUser(@RequestBody UserCreateDTO userCreateDTO) {
//        try {
//            return this.userService.insertUser(userCreateDTO);
//        } catch (GovernCenterException e) {
//            log.error("添加用户失败", e);
//            return new ResultJson<>(210, e.getMessage());
//        }
//    }
//
//
//    @ApiOperation("(启用)禁用用户")
//    @BaozunApi(name = "(启用)禁用用户", description = "(启用)禁用用户", version = "v1.0")
//    @BaozunApiParamClass(entity = UserModifyStatusDTO.class)
//    @RequestMapping(value = "/modifyUserStatusById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<Boolean> modifyUserStatusById(@RequestBody UserModifyStatusDTO userModifyStatusDTO) {
//        return this.userService.updateUserStatusById(userModifyStatusDTO);
//    }
//
//
//    @ApiOperation("根据用户id修改用户信息")
//    @BaozunApi(name = "根据用户id修改用户信息", description = "根据用户id修改用户信息", version = "v1.0")
//    @BaozunApiParamClass(entity = UserModifyDTO.class)
//    @RequestMapping(value = "/modifyUserById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> modifyUserById(@RequestBody UserModifyDTO userModifyDTO) {
//        return this.userService.updateUserById(userModifyDTO);
//    }
//
//
//    @ApiOperation("根据用户id修改用户密码")
//    @BaozunApi(name = "根据用户id修改用户密码", description = "根据用户id修改用户密码", version = "v1.0")
//    @BaozunApiParamClass(entity = UserModifyPasswordDTO.class)
//    @RequestMapping(value = "/modifyUserByPassword.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> modifyUserByPassword(@RequestBody UserModifyPasswordDTO userModifyPasswordDTO) {
//        return this.userService.updateUserByPassword(userModifyPasswordDTO);
//    }
}
