package com.example.test.service.Impl;


import com.example.test.entity.po.Menu;
import com.example.test.entity.po.Role;
import com.example.test.entity.po.RoleMenu;
import com.example.test.entity.vo.RoleVo;
import com.example.test.mapper.MenuMapper;
import com.example.test.mapper.RoleMapper;
import com.example.test.mapper.RoleMenuMapper;
import com.example.test.service.RoleService;
import com.example.test.utils.Constant;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Resource(name = "roleMenuMapper")
    private RoleMenuMapper roleMenuMapper;


    @Override
    public ResultJson<PageInfo<RoleVo>> selectRole(int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = Constant.PAGE_NUM;
        }
        if (pageSize < 1) {
            pageSize = Constant.PAGE_SIZE;
        }
        PageInfo<RoleVo> pageInfo = new PageInfo<>();
        List<RoleVo> voList = new ArrayList<>();
        Page<RoleVo> page = PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = this.roleMapper.selectRole();
        roleList.forEach(role -> voList.add(new RoleVo(role)));
        pageInfo.setPageInfo(pageNum, pageSize, (int) page.getTotal(), voList);
        return new ResultJson<>(EnumsUtils.SUCCESS, pageInfo);
    }

    @Override
    public ResultJson<RoleVo> selectRoleById(long roleId) {
        Role role = this.roleMapper.selectRoleById(roleId);
        return role == null ? new ResultJson<>(EnumsUtils.FIND_FAIL) : new ResultJson<>(EnumsUtils.SUCCESS, new RoleVo(role));
    }


    @Override
    public ResultJson<RoleVo> insertRole(RoleVo roleVo, String menuName) {
        RoleMenu roleMenu = new RoleMenu();
        Role role1 = new Role(roleVo);
        if (!this.roleMapper.insertRole(role1)) {
            return new ResultJson<>(EnumsUtils.INSERT_FAIL);
        }
        if (menuName != null) {
            String[] menuNameStr = menuName.split("&");
            for (int i = 0; i < menuNameStr.length; i++) {
                Menu menu1 = menuMapper.selectMenuByMenuName(menuNameStr[i]);
                roleMenu.setRoleId(role1.getId());
                roleMenu.setMenuId(menu1.getId());
                if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
                    new ResultJson<>(EnumsUtils.INSERT_FAIL);
                }
            }
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, this.selectRoleById(role1.getId()).getData());
    }

    @Override
    @CacheEvict(value = "roleVo", key = "#roleId+'roleVo'")
    public ResultJson<Boolean> deleteRoleById(long roleId) {
        ResultJson resultJson = this.selectRoleById(roleId);
        if (resultJson.getData() == null) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        if (this.roleMapper.deleteRoleById(roleId)) {
            return new ResultJson<>(EnumsUtils.SUCCESS, true);
        }
        return new ResultJson<>(EnumsUtils.DELETE_FAIL, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "roleVo", key = "#roleVo.roleId+'roleVo'")
    public ResultJson<RoleVo> updateRoleById(RoleVo roleVo) {
        Role role = new Role(roleVo);
        if (this.roleMapper.updateRoleById(role)) {
            return new ResultJson<>(EnumsUtils.SUCCESS, new RoleVo(this.roleMapper.selectRoleById(role.getId())));
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL);
    }

    @Override
    public List<Integer> selectIdByRoleName(List<String> roleName) {
        return roleMapper.selectIdByRoleName(roleName);
    }
}
