package com.example.test.mapper;

import com.example.test.entity.po.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRoleMapper")
public interface UserRoleMapper {

        /**
         * 查询RoleId
         *
         * @return
         */
        List<Long> selectRoleIdById(@Param("userId") long userId);

        /**
         * 插入用户角色关系
         * <p>
         * * @param userRole
         *
         * @return
         */
        boolean insertUserRole(@Param("userRole") UserRole userRole);

        /**
         * 根据userId,roleId删除用户角色关联
         *
         * @return
         */
        boolean deleteUserRoleById(@Param("userRole") UserRole userRole);


}
