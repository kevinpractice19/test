package com.example.test.mapper;

import com.example.test.entity.po.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuMapper")
public interface MenuMapper {
        /**
         * 查询菜单根据Id集合
         * @param list
         * @return
         */
        List<Menu> selectMenuByIdList(@Param("list") List<Long> list);

        /**
         * 查询所有菜单
         * @param
         * @return
         */
        List<Menu> selectMenuList() ;

        /**
         * 查询父菜单命根据parentId
         * @param
         * @return
         */
        String selectParentName(long parentId) ;

        /**
         * 根据父菜单id查询子菜单集合
         * @param parentId
         * @return
         */
        List<Menu> selectMenuByParentId(long parentId) ;

        /**
         * 查询父菜单
         * @param
         * @return
         */
        List<Menu> selectParentMenu() ;

        /**
         * 根据菜单名称查询菜单id
         * @param menuName
         * @return
         */
        Menu selectMenuByMenuName(@Param("menuName") String menuName);

        /**
         * 添加菜单
         * @param menu
         * @return
         */
        boolean insertMenu(@Param("menu") Menu menu);

        /**
         * 修改菜单
         * @param menu
         * @return
         */
        boolean updateMenuById(@Param("menu") Menu menu);

        /**
         * 删除菜单根据id
         * @param id
         * @return
         */
        boolean deleteMenuById(long id);

        /**
         * 根据菜单名称查询菜单id
         * @param menuNameList
         * @return
         */
        List<Long> selectMenuByNameList(List<String> menuNameList);

        /**
         * 根据菜单id查询菜单详情
         * @param menuId
         * @return
         */
        Menu selectMenuById(@Param("menuId") long menuId);


        /**
         * 根据父菜单id查询子菜单集合
         * @param parentId
         * @return
         */
        List<Menu> selectMenuByParentIdList(@Param("parentId") long parentId) ;


}
