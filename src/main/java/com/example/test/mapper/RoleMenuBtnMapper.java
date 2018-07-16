package com.example.test.mapper;

import com.example.test.entity.po.RoleMenuBtn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleMenuBtnMapper")
public interface RoleMenuBtnMapper {

    boolean insertRoleMenuBtn(@Param("roleMenuBtn") RoleMenuBtn roleMenuBtn);

    List<Long> selectRoleMenuBtnByRoleId(List<Long> roleIdList);

    Boolean updateRoleMenuBtnStatusById(@Param("roleMenuBtnId") long roleMenuBtnId, @Param("status") int status);
}
