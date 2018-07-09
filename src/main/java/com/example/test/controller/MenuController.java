package com.newnoa.govern.controller;

import com.baozun.framework.entity.PageInfo;
import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.dto.MenuCreateDTO;
import com.newnoa.govern.entity.dto.MenuModifyDTO;
import com.newnoa.govern.entity.vo.MenuVo;
import com.newnoa.govern.service.MenuService;
import com.noa.common.annotation.BaozunApi;
import com.noa.common.annotation.BaozunApiParamClass;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {

    @Resource(name = "menuService")
    private MenuService menuService;

    @ApiOperation("根据用户id查询导航菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "long")
    })
    @RequestMapping(value = "/selectMenuByUserId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectMenuByUserId(HttpServletRequest request) {
        long userId = ObjectUtils.objectToLong(request.getParameter("userId"));
        return this.menuService.listParentMenuByUserId(userId);
    }


    @ApiOperation("查询导航菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "分页数", required = false, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/pageMenu.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<PageInfo<MenuVo>> pageMenu(HttpServletRequest request) {
        int pageNum = ObjectUtils.objectToInt(request.getParameter("pageNum"));
        int pageSize = ObjectUtils.objectToInt(request.getParameter("pageSize"));
        return this.menuService.selectMenuList(pageNum, pageSize);
    }


    @ApiOperation("查询导航父菜单")
    @RequestMapping(value = "/selectParentMenu.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectParentMenu(HttpServletRequest request) {
        return this.menuService.selectParentMenu();
    }


    @ApiOperation("根据父菜单查询导航子菜单")
    @RequestMapping(value = "/selectMenuByParentId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<List<MenuVo>> selectMenuByParentId(@RequestParam("parentId") long parentId) {
        return this.menuService.selectMenuByParentId(parentId);
    }

    @ApiOperation("根据菜单id查询菜单详情")
    @RequestMapping(value = "/getMenuId.do", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> getMenuId(@RequestParam("menuId") long menuId) {
        return this.menuService.selectMenuById(menuId);
    }


    @ApiOperation("创建导航菜单")
    @BaozunApiParamClass(entity = MenuCreateDTO.class)
    @BaozunApi(name = "创建导航菜单", description = "创建导航菜单", version = "v1.0")
    @RequestMapping(value = "/createMenu.do", method = RequestMethod.PUT, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> createMenu(@RequestBody MenuCreateDTO createDTO) {
        return this.menuService.insertMenu(createDTO);
    }


    @ApiOperation("删除导航菜单")
    @RequestMapping(value = "/removeMenuById.do/{menuId}", method = RequestMethod.DELETE, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> removeMenuById(@PathVariable("menuId") long menuId) {
        return this.menuService.deleteMenuById(menuId);
    }


    @ApiOperation("修改导航菜单")
    @BaozunApiParamClass(entity = MenuModifyDTO.class)
    @BaozunApi(name = "修改导航菜单", description = "修改导航菜单", version = "v1.0")
    @RequestMapping(value = "/modifyMenuById.do", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON_UTF8_VALUE)
    public ResultJson<MenuVo> modifyMenuById(@RequestBody MenuModifyDTO modifyDTO) {
        return this.menuService.updateMenuById(modifyDTO);
    }
}
