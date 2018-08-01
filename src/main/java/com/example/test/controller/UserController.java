package com.example.test.controller;

import com.example.test.entity.dto.*;
import com.example.test.entity.vo.UserVo;
import com.example.test.service.UserService;
import com.example.test.utils.Constant;
import com.example.test.utils.GovernCenterException;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;


    @RequestMapping(value = "/pageUser.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<PageInfo<UserVo>> pageUser(UserPageDTO pageDTO) {
        return this.userService.pageUser(pageDTO);
    }


    @RequestMapping(value = "/selectUserById.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> selectUserById(@RequestParam("userId") long userId) {
        return this.userService.selectUserById(userId);
    }


    @ApiOperation("用户登录")
    @RequestMapping(value = "/getLogin.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> getLogin(@RequestBody UserLoginDTO userLoginDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ResultJson jsonResult = this.userService.getLogin(userLoginDTO);
        HttpSession session = request.getSession();
        session.setAttribute("account",userLoginDTO.getAccount());
        return jsonResult;
    }

    @ApiOperation("重置密码")
    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> resetPassword(@RequestBody UserResetPasswordDTO resetPasswordDTO) {
        return this.userService.resetPassword(resetPasswordDTO);
    }


    @ApiOperation("根据用户账号查询用户信息")
    @RequestMapping(value = "/selectUserByAccount.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> selectUserByAccount(@RequestParam("account") String account) {
        return this.userService.selectUserByAccount(account);
    }


    @ApiOperation("创建用户")
    @RequestMapping(value = "/createUser.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            return this.userService.insertUser(userCreateDTO);
        } catch (GovernCenterException e) {
            log.error("添加用户失败", e);
            return new ResultJson<>(210, e.getMessage());
        }
    }


    @ApiOperation("(启用)禁用用户")
    @RequestMapping(value = "/modifyUserStatusById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<Boolean> modifyUserStatusById(@RequestBody UserModifyStatusDTO userModifyStatusDTO) {
        return this.userService.updateUserStatusById(userModifyStatusDTO);
    }


//    @ApiOperation("根据用户id修改用户信息")
//    @RequestMapping(value = "/modifyUserById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
//    public ResultJson<UserVo> modifyUserById(@RequestBody UserModifyDTO userModifyDTO) {
//        return this.userService.updateUserById(userModifyDTO);
//    }


    @ApiOperation("根据用户id修改用户密码")
    @RequestMapping(value = "/modifyUserByPassword.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<UserVo> modifyUserByPassword(@RequestBody UserModifyPasswordDTO userModifyPasswordDTO) {
        return this.userService.updateUserByPassword(userModifyPasswordDTO);
    }
}
