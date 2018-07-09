package com.example.test.service;


import com.example.test.entity.dto.*;
import com.example.test.entity.vo.UserVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

public interface UserService {

        ResultJson<PageInfo<UserVo>> pageUser(UserPageDTO pageDTO);

        ResultJson<UserVo> selectUserById(long userId);

        ResultJson<UserVo> insertUser(UserCreateDTO userCreateDTO);

        ResultJson<Boolean> updateUserStatusById(UserModifyStatusDTO userModifyStatusDTO);

        ResultJson<UserVo> updateUserById(UserModifyDTO userModifyDTO);

        ResultJson<UserVo> getLogin(UserLoginDTO userLoginDTO);

        ResultJson<UserVo> selectUserByAccount(String account);

        ResultJson<UserVo> updateUserByPassword(UserModifyPasswordDTO userModifyPasswordDTO);

        ResultJson<UserVo> resetPassword(UserResetPasswordDTO resetPasswordDTO);

}
