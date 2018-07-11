package com.example.test.mapper;

import com.example.test.entity.po.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleMenuMapper")
public interface RoleMenuMapper {

        /**
         * 根据角色id查询菜单id
         *
         * @param list
         * @return
         */
        List<Integer> selectRoleMenuIdById(@Param("list") List<Integer> list);

        /**
         * 创建菜单角色关联
         *
         * @param roleMenu
         * @return
         */
        boolean insertRoleMenu(@Param("roleMenu") RoleMenu roleMenu);

        /**
         * 根据菜单id删除该菜单角色关联
         *
         * @param menuId
         * @return
         */
        boolean deleteRoleMenuByMenuId(@Param("menuId") long menuId);

        /**
         * 根据角色id查询菜单id
         *
         * @param roleId
         * @return
         */
        List<Integer> selectRoleMenuByRoleId(@Param("roleId") long roleId);

        /**
         * 删除角色菜单关联
         *
         * @param roleId
         * @param menuId
         * @return
         */
        boolean deleteRoleMenuByMenuIdRoleId(@Param("roleId") long roleId, @Param("menuId") long menuId);

        List<Integer> selectRoleMenuByOperationBtnId(@Param("list") List<Integer> list);


}