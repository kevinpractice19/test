package com.example.test.service;

import com.example.test.entity.dto.MenuBtnCreateDTO;
import com.example.test.entity.dto.MenuBtnModifyDTO;
import com.example.test.entity.dto.PageDTO;
import com.example.test.entity.vo.MenuBtnVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

public interface MenuBtnService {

    ResultJson<MenuBtnVo> selectMenuBtnById(long id);

    ResultJson<MenuBtnVo> insertMenuBtn(MenuBtnCreateDTO createDTO);

    ResultJson<MenuBtnVo> updateMenuBtnById(MenuBtnModifyDTO modifyDTO);

    ResultJson<Boolean> updateMenuBtnStatusById(long menuBtnId, int status);

    ResultJson<PageInfo<MenuBtnVo>> selectMenuBtn(PageDTO pageDTO);
}
