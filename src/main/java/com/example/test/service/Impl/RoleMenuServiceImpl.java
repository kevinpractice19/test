package com.example.test.service.Impl;


import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.po.RoleMenu;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.mapper.MenuMapper;
import com.example.test.mapper.RoleMenuMapper;
import com.example.test.service.RoleMenuService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.ResultJson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource(name = "roleMenuMapper")
    private RoleMenuMapper roleMenuMapper;

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Override
    public List<Integer> selectRoleMenuIdById(List<Integer> list) {
        return this.roleMenuMapper.selectRoleMenuIdById(list);
    }

    @Override
    public boolean deleteRoleMenuByMenuId(long menuId) {
        return this.roleMenuMapper.deleteRoleMenuByMenuId(menuId);
    }

    @Override
    public List<Integer> selectRoleMenuByMenuBtnId(List<Integer> list) {
        return this.roleMenuMapper.selectRoleMenuByMenuBtnId(list);
    }

    @Transactional
    public ResultJson<RoleMenuVo> insertOrUpdateRoleMenu(RoleMenuCreateDTO createDTO) {
        List<Integer> menuIdListNew = menuMapper.selectMenuByNameList(createDTO.getMenuNameList());
        if (menuIdListNew.isEmpty()) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        List<Integer> menuIdListOld = roleMenuMapper.selectRoleMenuByRoleId(createDTO.getRoleId());
        if (menuIdListOld.isEmpty()) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        for (long menuId : menuIdListOld) {
            if (!menuIdListNew.contains((int) createDTO.getRoleId()))  //如果新传过来的角色不包含此用户以前的角色id则删除
                roleMenuMapper.deleteRoleMenuByMenuIdRoleId(createDTO.getRoleId(), menuId);
        }
        for (long menuId : menuIdListNew) {
            if (!menuIdListOld.contains((int) menuId)) {
                roleMenuMapper.insertRoleMenu(new RoleMenu(createDTO.getRoleId(), menuId));
            }
        }
        return new ResultJson<>(EnumsUtils.SUCCESS);
    }

}
