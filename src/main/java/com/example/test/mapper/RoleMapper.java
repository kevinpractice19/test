package com.example.test.mapper;

import com.example.test.entity.po.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleMapper")
public interface RoleMapper {

        /**
         * 查询所有角色
         *
         * @return
         */

        List<Role> selectRole();

        /**
         * 根据角色id查询角色信息
         *
         * @param id
         * @return
         */
        Role selectRoleById(@Param("id") long id);

        /**
         * 创建角色
         *
         * @param role
         * @return
         */
        boolean insertRole(@Param("role") Role role);

        /**
         * 更新角色信息
         *
         * @param role
         * @return
         */
        boolean updateRoleById(@Param("role") Role role);

}
