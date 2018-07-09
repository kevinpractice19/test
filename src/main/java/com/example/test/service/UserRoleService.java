package com.example.test.service;


import com.example.test.entity.vo.UserRoleVo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface UserRoleService {

        List<Integer> getRoleIdListById(long userId);

        ResultJson<UserRoleVo> insertOrUpdateUserRole(long userId, List<String> roleNameList);
}
