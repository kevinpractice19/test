package com.example.test.service.Impl;


import com.example.test.entity.dto.PageDTO;
import com.example.test.entity.dto.RoleCreateDTO;
import com.example.test.entity.dto.RoleModifyDTO;
import com.example.test.entity.po.Role;
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
    public ResultJson<PageInfo<RoleVo>> selectRole(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 1) {
            pageDTO.setPageNum(Constant.PAGE_NUM);
        }
        if (pageDTO.getPageSize() < 1) {
            pageDTO.setPageSize(Constant.PAGE_SIZE);
        }
        PageInfo<RoleVo> pageInfo = new PageInfo<>();
        List<RoleVo> voList = new ArrayList<>();
        Page<RoleVo> page = PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Role> roleList = this.roleMapper.selectRole();
        roleList.forEach(role -> voList.add(new RoleVo(role)));
        pageInfo.setPageInfo(pageDTO.getPageNum(), pageDTO.getPageSize(), (int) page.getTotal(), voList);
        return new ResultJson<>(EnumsUtils.SUCCESS, pageInfo);
    }

    @Override
    public ResultJson<RoleVo> selectRoleById(long roleId) {
        Role role = this.roleMapper.selectRoleById(roleId);
        return role == null ? new ResultJson<>(EnumsUtils.FIND_FAIL) : new ResultJson<>(EnumsUtils.SUCCESS, new RoleVo(role));
    }


    @Override
    @Transactional
    public ResultJson<RoleVo> insertRole(RoleCreateDTO createDTO) {
//        RoleMenu roleMenu = new RoleMenu();
        Role role1 = new Role();
        role1.setRemark(createDTO.getRemark());
        role1.setRoleName(createDTO.getRoleName());
        if (!this.roleMapper.insertRole(role1)) {
            return new ResultJson<>(EnumsUtils.INSERT_FAIL);
        }
//        if (!CollectionUtils.isEmpty(createDTO.getMenuNameList())) {
//            for (int i = 0; i < createDTO.getMenuNameList().size(); i++) {
//                Menu menu1 = menuMapper.selectMenuByMenuName(createDTO.getMenuNameList().get(i));
//                roleMenu.setRoleId(role1.getId());
//                roleMenu.setMenuId(menu1.getId());
//                if (!this.roleMenuMapper.insertRoleMenu(roleMenu)) {
//                    new ResultJson<>(EnumsUtils.INSERT_FAIL);
//                }
//            }
//        }
        return new ResultJson<>(EnumsUtils.SUCCESS, this.selectRoleById(role1.getId()).getData());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "roleVo", key = "#modifyDTO.roleId+'roleVo'")
    public ResultJson<RoleVo> updateRoleById(RoleModifyDTO modifyDTO) {
        Role role = new Role();
        role.setRoleName(modifyDTO.getRoleName());
        role.setId(modifyDTO.getRoleId());
        role.setRemark(modifyDTO.getRemark());
        if (this.roleMapper.updateRoleById(role)) {
            return new ResultJson<>(EnumsUtils.SUCCESS, new RoleVo(this.roleMapper.selectRoleById(role.getId())));
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL);
    }
}
