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
         * 根据角色id删除该角色
         *
         * @param roleId
         * @return
         */
        boolean deleteRoleById(@Param("roleId") long roleId);

        /**
         * 更新角色信息
         *
         * @param role
         * @return
         */
        boolean updateRoleById(@Param("role") Role role);

        /**
         * 查询角色id
         *
         * @param list
         * @return
         */
        List<Role> selectRoleIdList(@Param("list") List<Integer> list);

        /**
         * 根据角色名查询角色信息
         *
         * @param roleName
         * @return
         */
        Role selectRoleByRoleName(@Param("roleName") String roleName);

        /**
         * 根据角色名查询角色id
         *
         * @param list
         * @return
         */
        List<Integer> selectIdByRoleName(@Param("list") List<String> list);
//

}
