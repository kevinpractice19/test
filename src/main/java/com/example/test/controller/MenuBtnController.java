package com.example.test.controller;

import com.example.test.entity.dto.MenuBtnCreateDTO;
import com.example.test.entity.dto.MenuBtnModifyDTO;
import com.example.test.entity.dto.PageDTO;
import com.example.test.entity.vo.MenuBtnVo;
import com.example.test.service.MenuBtnService;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/MenuBtn")
public class MenuBtnController {

    @Autowired
    private MenuBtnService menuBtnService;

    @ApiOperation("创建菜单按钮")
    @RequestMapping(value = "/createMenuBtn.do", method = RequestMethod.POST)
    public ResultJson<MenuBtnVo> createMenuBtn(@RequestBody MenuBtnCreateDTO createDTO) {
        return this.menuBtnService.insertMenuBtn(createDTO);
    }

    @ApiOperation("根据id查询菜单按钮")
    @RequestMapping(value = "/getMenuBtnById.do", method = RequestMethod.GET)
    public ResultJson<MenuBtnVo> getMenuBtnById(@RequestParam("id") long id) {
        return this.menuBtnService.selectMenuBtnById(id);
    }


    @ApiOperation("根据菜单按钮id修改菜单按钮")
    @RequestMapping(value = "/updateMenuBtnById.do", method = RequestMethod.POST)
    public ResultJson<MenuBtnVo> updateMenuBtnById(@RequestBody MenuBtnModifyDTO modifyDTO) {
        return this.menuBtnService.updateMenuBtnById(modifyDTO);
    }


    @ApiOperation("启用（禁用）菜单按钮")
    @RequestMapping(value = "/updateMenuBtnStatusById.do", method = RequestMethod.POST)
    public ResultJson<Boolean> updateMenuBtnStatusById(@RequestParam("menuBtnId") long menuBtnId, @RequestParam("status") int status) {
        return this.menuBtnService.updateMenuBtnStatusById(menuBtnId, status);
    }

    @ApiOperation("分页查询菜单按钮")
    @RequestMapping(value = "/pageMenuBtn.do", method = RequestMethod.POST)
    public ResultJson<PageInfo<MenuBtnVo>> pageMenuBtn(PageDTO pageDTO) {
        return this.menuBtnService.selectMenuBtn(pageDTO);
    }

}
