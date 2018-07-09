package com.newnoa.govern.service.impl;

import com.baozun.framework.entity.PageInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.common.util.EnumsUtils;
import com.newnoa.govern.entity.po.Menu;
import com.newnoa.govern.entity.po.Role;
import com.newnoa.govern.entity.po.RoleMenu;
import com.newnoa.govern.entity.vo.RoleVo;
import com.newnoa.govern.mapper.MenuMapper;
import com.newnoa.govern.mapper.RoleMapper;
import com.newnoa.govern.mapper.RoleMenuMapper;
import com.newnoa.govern.service.OperationRecordService;
import com.newnoa.govern.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OperationRecordService operationRecordService;

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
    @CachePut(value = "roleVo", key = "#roleId+'roleVo'")
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
        this.operationRecordService.insertOperationRecord(role1.getId(), Constant.INSERT, Constant.ROLE_INFO);
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
            this.operationRecordService.insertOperationRecord(roleId, Constant.REMOVE, Constant.ROLE_INFO);
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
            this.operationRecordService.insertOperationRecord(role.getId(), Constant.MODIFY, Constant.ROLE_INFO);
            return new ResultJson<>(EnumsUtils.SUCCESS, new RoleVo(this.roleMapper.selectRoleById(role.getId())));
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL);
    }

    @Override
    public List<Integer> selectIdByRoleName(List<String> roleName) {
        return roleMapper.selectIdByRoleName(roleName);
    }
}
