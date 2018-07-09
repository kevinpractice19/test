package com.example.test.service;

import com.example.test.entity.dto.MenuCreateDTO;
import com.example.test.entity.dto.MenuModifyDTO;
import com.example.test.entity.vo.MenuVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface MenuService {

        ResultJson<PageInfo<MenuVo>> selectMenuList(int pageNum, int pageSize);

        ResultJson<MenuVo> insertMenu(MenuCreateDTO createDTO);

        ResultJson<MenuVo> deleteMenuById(long menuId);

        ResultJson<MenuVo> updateMenuById(MenuModifyDTO modifyDTO);

        ResultJson<List<MenuVo>> selectMenuByParentId(long parentId);

        ResultJson<List<MenuVo>> listParentMenuByUserId(long userId);

        ResultJson<List<MenuVo>> selectParentMenu();

        ResultJson<MenuVo> selectMenuById(long userId);

}
