package com.newnoa.govern.service;

import com.baozun.framework.entity.PageInfo;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.entity.dto.*;
import com.newnoa.govern.entity.vo.UserVo;

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
