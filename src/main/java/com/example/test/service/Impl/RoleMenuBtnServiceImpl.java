package com.example.test.service.Impl;

import com.example.test.entity.dto.RoleMenuBtnCreateDTO;
import com.example.test.entity.po.RoleMenuBtn;
import com.example.test.entity.vo.RoleMenuBtnVo;
import com.example.test.mapper.RoleMenuBtnMapper;
import com.example.test.service.RoleMenuBtnService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleMenuBtnService")
public class RoleMenuBtnServiceImpl implements RoleMenuBtnService {

    @Autowired
    private RoleMenuBtnMapper roleMenuBtnMapper;


    @Override
    public ResultJson<RoleMenuBtnVo> insertRoleMenuBtn(RoleMenuBtnCreateDTO createDTO) {
        RoleMenuBtn roleMenuBtn = new RoleMenuBtn();
        roleMenuBtn.setRoleId(createDTO.getRoleId());
        roleMenuBtn.setMenuBtnId(createDTO.getMenuBtnId());
        if (this.roleMenuBtnMapper.insertRoleMenuBtn(roleMenuBtn)) {
            return new ResultJson<>(EnumsUtils.SUCCESS);
        }
        return new ResultJson<>(EnumsUtils.INSERT_FAIL);
    }

    @Override
    public List<Long> selectRoleMenuBtnByRoleId(List<Long> roleIdList) {
        List<Long> list = this.roleMenuBtnMapper.selectRoleMenuBtnByRoleId(roleIdList);
        return list;
    }
}
