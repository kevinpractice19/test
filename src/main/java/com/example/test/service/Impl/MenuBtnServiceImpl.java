package com.example.test.service.Impl;

import com.example.test.entity.dto.MenuBtnCreateDTO;
import com.example.test.entity.dto.MenuBtnModifyDTO;
import com.example.test.entity.dto.PageDTO;
import com.example.test.entity.po.MenuBtn;
import com.example.test.entity.po.RoleMenuBtn;
import com.example.test.entity.vo.MenuBtnVo;
import com.example.test.mapper.MenuBtnMapper;
import com.example.test.mapper.RoleMenuBtnMapper;
import com.example.test.service.MenuBtnService;
import com.example.test.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("MenuBtnService")
public class MenuBtnServiceImpl implements MenuBtnService {

    @Autowired
    private MenuBtnMapper menuBtnMapper;

    @Autowired
    private RoleMenuBtnMapper roleMenuBtnMapper;

    @Override
    public ResultJson<MenuBtnVo> selectMenuBtnById(long id) {
        MenuBtn menuBtn = this.menuBtnMapper.selectMenuBtnById(id);
        return new ResultJson<>(new MenuBtnVo(menuBtn));
    }

    @Override
    @Transactional
    public ResultJson<MenuBtnVo> insertMenuBtn(MenuBtnCreateDTO createDTO) {
        MenuBtn menuBtn = new MenuBtn();
        try {
            BeanUtils.copyProperties(createDTO, menuBtn);
            if (!this.menuBtnMapper.insertMenuBtn(menuBtn)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
            RoleMenuBtn roleMenuBtn = new RoleMenuBtn();
            roleMenuBtn.setRoleId(createDTO.getRoleId());
            roleMenuBtn.setMenuBtnId(menuBtn.getId());
            if (!this.roleMenuBtnMapper.insertRoleMenuBtn(roleMenuBtn)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(210, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, new MenuBtnVo(this.menuBtnMapper.selectMenuBtnById(menuBtn.getId())));
    }

    @Override
    public ResultJson<MenuBtnVo> updateMenuBtnById(MenuBtnModifyDTO modifyDTO) {
        MenuBtn menuBtn = new MenuBtn();
        BeanUtils.copyProperties(modifyDTO, menuBtn);
        menuBtn.setId(modifyDTO.getMenuBtnId());
        if (this.menuBtnMapper.updateMenuBtnById(menuBtn)) {
            return new ResultJson<>(EnumsUtils.SUCCESS, new MenuBtnVo(this.menuBtnMapper.selectMenuBtnById(menuBtn.getId())));
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL);
    }

    @Override
    @Transactional
    public ResultJson<Boolean> updateMenuBtnStatusById(long menuBtnId, int status) {
        try {
            if (!this.menuBtnMapper.updateMenuBtnStatusById(menuBtnId, status)) {
                throw new GovernCenterException(EnumsUtils.UPDATE_FAIL);
            }
            if (!this.roleMenuBtnMapper.updateRoleMenuBtnStatusById(menuBtnId, status)) {
                throw new GovernCenterException(EnumsUtils.UPDATE_FAIL);
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(230, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS);
    }

    @Override
    public ResultJson<PageInfo<MenuBtnVo>> selectMenuBtn(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 1) {
            pageDTO.setPageNum(Constant.PAGE_NUM);
        }
        if (pageDTO.getPageSize() < 1) {
            pageDTO.setPageSize(Constant.PAGE_SIZE);
        }
        PageInfo<MenuBtnVo> pageInfo = new PageInfo<>();
        List<MenuBtnVo> menuBtnVoList = new ArrayList<>();
        Page<MenuBtn> page = PageHelper.startPage(pageDTO);
        List<MenuBtn> menuBtnList = this.menuBtnMapper.selectMenuBtn();
        menuBtnList.forEach(menuBtn -> menuBtnVoList.add(new MenuBtnVo(menuBtn)));
        pageInfo.setPageInfo(pageDTO.getPageNum(), pageDTO.getPageSize(), (int) page.getTotal(), menuBtnVoList);
        return new ResultJson<>(EnumsUtils.SUCCESS, pageInfo);
    }
}

