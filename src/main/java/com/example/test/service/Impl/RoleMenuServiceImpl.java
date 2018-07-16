package com.example.test.service.Impl;


import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.po.RoleMenu;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.mapper.MenuMapper;
import com.example.test.mapper.RoleMenuMapper;
import com.example.test.service.RoleMenuService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.GovernCenterException;
import com.example.test.utils.ResultJson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource(name = "roleMenuMapper")
    private RoleMenuMapper roleMenuMapper;

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Override
    public List<Long> selectRoleMenuIdById(List<Long> list) {
        return this.roleMenuMapper.selectRoleMenuIdById(list);
    }

    @Override
    public ResultJson<Boolean> deleteRoleMenuByMenuIdRoleId(long menuId, long roleId) {
        if (this.roleMenuMapper.deleteRoleMenuByMenuIdRoleId(menuId, roleId)) {
            return new ResultJson<>(EnumsUtils.SUCCESS, true);
        }
        return new ResultJson<>(EnumsUtils.DELETE_FAIL, false);
    }

//    @Override
//    public List<Long> selectRoleMenuByMenuBtnId(List<Long> list) {
//        return this.roleMenuMapper.selectRoleMenuByMenuBtnId(list);
//    }

    @Override
    @Transactional
    public ResultJson<RoleMenuVo> insertRoleMenu(RoleMenuCreateDTO createDTO) {
        try {
            RoleMenu roleMenu = new RoleMenu();
            List<Long> menuIdList = menuMapper.selectMenuByNameList(createDTO.getMenuNameList());
            if (CollectionUtils.isEmpty(menuIdList)) {
                throw new GovernCenterException(300, "该菜单不存在");
            }
            List<Long> menuIdListOld = roleMenuMapper.selectRoleMenuByRoleId(createDTO.getRoleId());  //查询该角色以前所拥有的菜单权限
            if (menuIdListOld.isEmpty()) {  //无
                for (long menuId : menuIdList) {
                    roleMenu.setRoleId(createDTO.getRoleId());
                    roleMenu.setMenuId(menuId);
                    if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
                        throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
                    }
                }
            } else {//有
                for (long menuId : menuIdList) {
                    //如果该用户以前的菜单权限不包含即将添加的的菜单权限则新增菜单权限，如果包含则无需再次添加
                    if (!menuIdListOld.contains(menuId)) {
                        roleMenu.setMenuId(menuId);
                        roleMenu.setRoleId(createDTO.getRoleId());
                        if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
                            throw new GovernCenterException(EnumsUtils.SUCCESS);
                        }
                    }
                }
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(210, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS);
    }


}
