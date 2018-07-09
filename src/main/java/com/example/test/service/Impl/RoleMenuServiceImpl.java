package com.newnoa.govern.service.impl;

import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.EnumsUtils;
import com.newnoa.govern.entity.po.RoleMenu;
import com.newnoa.govern.entity.vo.RoleMenuVo;
import com.newnoa.govern.mapper.MenuMapper;
import com.newnoa.govern.mapper.RoleMenuMapper;
import com.newnoa.govern.service.RoleMenuService;
import org.springframework.stereotype.Service;

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

    public ResultJson<RoleMenuVo> insertOrUpdateRoleMenu(long roleId, List<String> menuNameList) {
        List<Integer> menuIdListNew = menuMapper.selectMenuByNameList(menuNameList);
        if (menuIdListNew.isEmpty()) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        List<Integer> menuIdListOld = roleMenuMapper.selectRoleMenuByRoleId(roleId);
        if (menuIdListOld.isEmpty()) {
            return new ResultJson<>(EnumsUtils.FIND_FAIL);
        }
        for (long menuId : menuIdListOld) {
            if (!menuIdListNew.contains((int) roleId))  //如果新传过来的角色不包含此用户以前的角色id则删除
                roleMenuMapper.deleteRoleMenuByMenuIdRoleId(roleId, menuId);
        }
        for (long menuId : menuIdListNew) {
            if (!menuIdListOld.contains((int) menuId)) {
                roleMenuMapper.insertRoleMenu(new RoleMenu(roleId, menuId));
            }
        }
        return new ResultJson<>(EnumsUtils.SUCCESS);
    }

}
