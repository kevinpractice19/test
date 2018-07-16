package com.example.test.controller;

import com.example.test.entity.dto.MenuBtnCreateDTO;
import com.example.test.entity.vo.MenuBtnVo;
import com.example.test.service.MenuBtnService;
import com.example.test.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roleMenuBtn")
public class RoleMenuBtnController {

    @Autowired
    private MenuBtnService menuBtnService;

    @RequestMapping(value = "/createMenuBtn.do", method = RequestMethod.POST)
    public ResultJson<MenuBtnVo> createMenuBtn(@RequestBody MenuBtnCreateDTO createDTO) {
        return this.menuBtnService.insertMenuBtn(createDTO);
    }

    @RequestMapping(value = "/getMenuBtnById.do", method = RequestMethod.GET)
    public ResultJson<MenuBtnVo> getMenuBtnById(@RequestParam("id") long id) {
        return this.menuBtnService.selectMenuBtn(id);
    }

}
