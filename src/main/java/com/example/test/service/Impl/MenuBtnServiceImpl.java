package com.example.test.service.Impl;

import com.example.test.entity.dto.OperationBtnCreateDTO;
import com.example.test.entity.po.OperationBtn;
import com.example.test.entity.po.RoleMenuBtn;
import com.example.test.entity.vo.OperationBtnVo;
import com.example.test.mapper.OperationBtnMapper;
import com.example.test.mapper.RoleMenuBtnMapper;
import com.example.test.service.OperationBtnService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.GovernCenterException;
import com.example.test.utils.ResultJson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("operationBtnService")
public class OperationBtnServiceImpl implements OperationBtnService {

    @Autowired
    private OperationBtnMapper operationBtnMapper;

    @Autowired
    private RoleMenuBtnMapper roleMenuBtnMapper;

    @Override
    public ResultJson<OperationBtnVo> selectOperationBtn(long id) {
        OperationBtn operationBtn = this.operationBtnMapper.selectOperationBtnById(id);
        return new ResultJson(new OperationBtnVo(operationBtn));
    }

    @Override
    @Transactional
    public ResultJson<OperationBtnVo> insertOperationBtn(OperationBtnCreateDTO createDTO) {
        OperationBtn operationBtn = new OperationBtn();
        try {
            BeanUtils.copyProperties(createDTO, operationBtn);
            if (!this.operationBtnMapper.insertOperationBtn(operationBtn)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
            RoleMenuBtn roleMenuBtn = new RoleMenuBtn();
            roleMenuBtn.setRoleId(createDTO.getRoleId());
            roleMenuBtn.setMenuBtnId(operationBtn.getId());
            if (!this.roleMenuBtnMapper.insertRoleMenuBtn(roleMenuBtn)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(210, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, new OperationBtnVo(this.operationBtnMapper.selectOperationBtnById(operationBtn.getId())));
    }
}
