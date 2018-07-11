package com.example.test.service.Impl;

import com.example.test.entity.dto.MenuCreateDTO;
import com.example.test.entity.dto.MenuModifyDTO;
import com.example.test.entity.po.Menu;
import com.example.test.entity.po.OperationBtn;
import com.example.test.entity.po.RoleMenu;
import com.example.test.entity.vo.MenuVo;
import com.example.test.mapper.MenuMapper;
import com.example.test.mapper.OperationBtnMapper;
import com.example.test.mapper.RoleMenuMapper;
import com.example.test.service.MenuService;
import com.example.test.service.RoleMenuService;
import com.example.test.service.UserRoleService;
import com.example.test.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("menuService")
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    @Resource(name = "roleMenuService")
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private OperationBtnMapper operationBtnMapper;

    @Override
    public ResultJson<List<MenuVo>> listParentMenuByUserId(long userId) {
        List<Integer> roleIdList = this.userRoleService.getRoleIdListById(userId);
        List<Integer> menuIdList = this.roleMenuService.selectRoleMenuIdById(roleIdList);
        List<Integer> operationIdList = this.roleMenuService.selectRoleMenuByOperationBtnId(roleIdList);  //根据roleId查询出该角色所拥有所有按钮权限
        List<OperationBtn> operationBtnList = this.operationBtnMapper.selectOperationBtnByIdList(operationIdList); //根据按钮idList查询出按钮所有信息
        List<Menu> menuList = this.menuMapper.selectMenuByIdList(menuIdList); //查出所有顶级菜单
        List<MenuVo> result = new ArrayList<>();
        for (Menu menu : menuList) {
            result.add(this.getSubMenu(new MenuVo(menu), operationBtnList));
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, result);
    }

    private MenuVo getSubMenu(MenuVo menuVo, List<OperationBtn> operationBtnList) {
        if (menuVo != null) {
            List<Menu> menuList = menuMapper.selectMenuByParentId(menuVo.getMenuId());
            List<MenuVo> menuVoList = this.poTransformationVoMethod(menuList);
            if (!CollectionUtils.isEmpty(menuVoList)) {
                menuVo.setMenuVoList(menuVoList);
                for (MenuVo subMenu : menuVoList) {
                    List<OperationBtn> btnList = this.operationBtnMapper.selectOperationBtnByMenuId(subMenu.getMenuId());
                    for (OperationBtn operationBtn : btnList) {
                        if (operationBtnList.contains(operationBtn)) {
                            subMenu.getOperationBtnList().add(operationBtn);
                        }
                    }
                    this.getSubMenu(subMenu, operationBtnList);
                }
            }
        }
        return menuVo;
    }


    private List<MenuVo> poTransformationVoMethod(List<Menu> menuList) {
        List<MenuVo> resultVo = new ArrayList<>(menuList.size());
        for (Menu menu : menuList) {
            MenuVo menuVo = new MenuVo();
            menuVo.setMenuId(menu.getId());
            BeanUtils.copyProperties(menu, menuVo);
            resultVo.add(menuVo);
        }
        return resultVo;
    }


    @Override
    public ResultJson<PageInfo<MenuVo>> selectMenuList(int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = Constant.PAGE_NUM;
        }
        if (pageSize < 1) {
            pageSize = Constant.PAGE_SIZE;
        }
        PageInfo<MenuVo> pageInfo = new PageInfo<>();
        List<MenuVo> voList = new ArrayList<>();
        Page<MenuVo> page = PageHelper.startPage(pageNum, pageSize, true);
        List<Menu> menuList = this.menuMapper.selectMenuList();
        for (Menu menu : menuList) {
            String parentName = this.menuMapper.selectParentName(menu.getParentId());
            voList.add(getMenuVo(menu, parentName));
        }
        pageInfo.setPageInfo(pageNum, pageSize, (int) page.getTotal(), voList);
        return new ResultJson<>(EnumsUtils.SUCCESS, pageInfo);
    }


    private MenuVo getMenuVo(Menu menu, String parentName) {
        if (menu == null)
            return null;
        log.debug("菜单详情不为空");
        return new MenuVo(menu, parentName);
    }

    @Override
    @Transactional
    public ResultJson<MenuVo> insertMenu(MenuCreateDTO createDTO) {
        RoleMenu roleMenu = new RoleMenu();
        Menu menu = new Menu();
        BeanUtils.copyProperties(createDTO, menu);
        menu.setParentId(createDTO.getParentId());
        try {
            Menu nameIsExists = this.menuMapper.selectMenuByMenuName(createDTO.getMenuName());
            if (nameIsExists != null) {
                throw new GovernCenterException(EnumsUtils.FIND_IS_EXISTS);
            }
            if (createDTO.getParentId() == 0) {
                List<Menu> menuList = this.menuMapper.selectParentMenu();
                if (!CollectionUtils.isEmpty(menuList)) {
                    Menu lastMenu = menuList.get(menuList.size() - 1);
                    menu.setMenuSort(lastMenu.getMenuSort() + 1);
                } else {
                    menu.setMenuSort(1);
                }
                if (!this.menuMapper.insertMenu(menu)) {
                    throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
                }
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(1);
                if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
                    throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
                }
            } else {
                List<Menu> menuList = this.menuMapper.selectMenuByParentId(createDTO.getParentId());
                if (!CollectionUtils.isEmpty(menuList)) {
                    Menu lastMenu = menuList.get(menuList.size() - 1);
                    menu.setMenuSort(lastMenu.getMenuSort() + 1);
                } else {
                    menu.setMenuSort(1);
                }
                if (!this.menuMapper.insertMenu(menu)) {
                    throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
                }
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(1);
                if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
                    throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
                }
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(210, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, this.selectMenuById(menu.getId()).getData());
    }

    @Override
    @CacheEvict(value = "menuVo", key = "#menuId+'menuVo'")
    public ResultJson<MenuVo> deleteMenuById(long menuId) {
        List<Menu> mList = this.menuMapper.selectMenuByParentId(menuId);
        if (!mList.isEmpty()) {
            return new ResultJson<>(EnumsUtils.DELETE_SUBMENU);
        }
        if (!this.menuMapper.deleteMenuById(menuId)) {
            return new ResultJson<>(EnumsUtils.DELETE_FAIL);
        }
        if (!this.roleMenuService.deleteRoleMenuByMenuId(menuId)) {
            return new ResultJson<>(EnumsUtils.DELETE_FAIL);
        }
        return new ResultJson<>(EnumsUtils.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "menuVo", key = "#modifyDTO.menuId+'menuVo'")
    public ResultJson<MenuVo> updateMenuById(MenuModifyDTO modifyDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(modifyDTO, menu);
        menu.setId(modifyDTO.getMenuId());
        try {
            Menu nameIsExists = this.menuMapper.selectMenuByMenuName(modifyDTO.getMenuName());
            if (nameIsExists != null) {
                throw new GovernCenterException(EnumsUtils.FIND_IS_EXISTS);
            }
            Menu result = menuMapper.selectMenuById(modifyDTO.getMenuId());
            if (result.getParentId() == 0) {
                //如果当前修改的菜单为父菜单，则先查询该父菜单下有无子菜单存在
                List<Menu> childMenuList = this.menuMapper.selectMenuByParentIdList(result.getId());
                if (!CollectionUtils.isEmpty(childMenuList)) {
                    throw new GovernCenterException(101, "当前修改的菜单为父菜单，请先修改或删除子菜单");
                }
                //根据父菜单id查询所有的子菜单,根据查询结果对菜单进行排序
                List<Menu> menuList = this.menuMapper.selectMenuByParentId(modifyDTO.getParentId());
                if (!CollectionUtils.isEmpty(menuList)) {
                    //如果menuList不为空，则取最后一条记录的menuSort加1
                    Menu lastMenu = menuList.get(menuList.size() - 1);
                    menu.setMenuSort(lastMenu.getMenuSort() + 1);
                } else {
                    //如果menuList为空，表示该父菜单下暂无子菜单，则menuSort为1
                    menu.setMenuSort(1);
                }
                if (!this.menuMapper.updateMenuById(menu)) {
                    throw new GovernCenterException(EnumsUtils.UPDATE_FAIL);
                }
            } else {
                //根据父菜单id查询所有的子菜单
                List<Menu> menuList = this.menuMapper.selectMenuByParentId(modifyDTO.getParentId());
                if (!CollectionUtils.isEmpty(menuList)) {
                    Menu lastMenu = menuList.get(menuList.size() - 1);
                    menu.setMenuSort(lastMenu.getMenuSort() + 1);
                } else {
                    menu.setMenuSort(1);
                }
                if (!this.menuMapper.updateMenuById(menu)) {
                    throw new GovernCenterException(EnumsUtils.UPDATE_FAIL);
                }
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(220, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, new MenuVo(this.menuMapper.selectMenuById(menu.getId())));
    }

    @Override
    public ResultJson<List<MenuVo>> selectMenuByParentId(long parentId) {
        List<Menu> menuList = this.menuMapper.selectMenuByParentId(parentId);
        if (menuList == null) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        List<MenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu -> menuVoList.add(new MenuVo(menu)));
        return new ResultJson<>(menuVoList);
    }

    @Override
    public ResultJson<List<MenuVo>> selectParentMenu() {
        List<Menu> menuList = this.menuMapper.selectParentMenu();
        if (menuList == null) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        List<MenuVo> menuVoList = new ArrayList<>();
        menuList.forEach(menu -> menuVoList.add(new MenuVo(menu)));
        return new ResultJson<>(menuVoList);
    }

    @Override
    @Cacheable(value = "menuVo", key = "#menuId+'menuVo'")
    public ResultJson<MenuVo> selectMenuById(long menuId) {
        Menu menu = this.menuMapper.selectMenuById(menuId);
        return menu == null ? new ResultJson<>(EnumsUtils.FIND_FAIL) : new ResultJson<>(EnumsUtils.SUCCESS, new MenuVo(menu));

    }
}
