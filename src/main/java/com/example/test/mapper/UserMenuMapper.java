package com.example.test.mapper;

import com.example.test.entity.po.UserMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userMenuMapper")
public interface UserMenuMapper {

    UserMenu selectUserMenuByUserIdAndMenuId(@Param("userId")long userId, @Param("menuId")long menuId);

    Boolean insertUserMenu(@Param("userMenu") UserMenu userMenu);

    Boolean updateUserMenuById(@Param("userMenu") UserMenu userMenu);
}
