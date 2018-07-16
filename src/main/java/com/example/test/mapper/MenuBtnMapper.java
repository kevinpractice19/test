package com.example.test.mapper;

import com.example.test.entity.po.MenuBtn;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuBtnMapper {

    boolean insertMenuBtn(@Param("menuBtn") MenuBtn menuBtn);

    MenuBtn selectMenuBtnById(@Param("id") long id);

    List<MenuBtn> selectMenuBtn();

    List<MenuBtn> selectMenuBtnByMenuId(@Param("menuId") long menuId);

    List<MenuBtn> selectMenuBtnByIdList(@Param("list") List<Long> list);

    Boolean updateMenuBtnById(@Param("menuBtn") MenuBtn menuBtn);

    Boolean updateMenuBtnStatusById(@Param("menuBtnId") long menuBtnId, @Param("status") int status);
}
