package com.newnoa.govern.service;

import com.baozun.framework.entity.PageInfo;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.entity.dto.MenuCreateDTO;
import com.newnoa.govern.entity.dto.MenuModifyDTO;
import com.newnoa.govern.entity.vo.MenuVo;

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
