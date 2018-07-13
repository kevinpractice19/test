package com.example.test.controller;


import com.example.test.entity.dto.MenuCreateDTO;
import com.example.test.entity.dto.MenuModifyDTO;
import com.example.test.entity.vo.MenuVo;
import com.example.test.service.MenuService;
import com.example.test.utils.Constant;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping(value = "/selectMenuByUserId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectMenuByUserId(@RequestParam("userId") long userId) {
        return this.menuService.listParentMenuByUserId(userId);
    }

    @RequestMapping(value = "/pageMenu.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<PageInfo<MenuVo>> pageMenu(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return this.menuService.selectMenuList(pageNum, pageSize);
    }

    @RequestMapping(value = "/selectParentMenu.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectParentMenu(HttpServletRequest request) {
        return this.menuService.selectParentMenu();
    }

    @RequestMapping(value = "/selectMenuByParentId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectMenuByParentId(@RequestParam("parentId") long parentId) {
        return this.menuService.selectMenuByParentId(parentId);
    }

    @RequestMapping(value = "/getMenuId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> getMenuId(@RequestParam("menuId") long menuId) {
        return this.menuService.selectMenuById(menuId);
    }

    @RequestMapping(value = "/createMenu.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> createMenu(@RequestBody MenuCreateDTO createDTO) {
        return this.menuService.insertMenu(createDTO);
    }

    @ApiOperation("启用（禁用菜单）")
    @RequestMapping(value = "/removeMenuById.do", method = RequestMethod.DELETE, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> removeMenuById(@RequestParam("menuId") long menuId, @RequestParam("status") int status) {
        return this.menuService.deleteMenuById(menuId, status);
    }

    @RequestMapping(value = "/modifyMenuById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> modifyMenuById(@RequestBody MenuModifyDTO modifyDTO) {
        return this.menuService.updateMenuById(modifyDTO);
    }
}
