package com.newnoa.govern.service;

import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.entity.vo.UserRoleVo;

import java.util.List;

public interface UserRoleService {

        List<Integer> getRoleIdListById(long userId);

        ResultJson<UserRoleVo> insertOrUpdateUserRole(long userId, List<String> roleNameList);
}
