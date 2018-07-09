package com.example.test.service.Impl;

import com.example.test.entity.dto.OperationBtnCreateDTO;
import com.example.test.entity.po.OperationBtn;
import com.example.test.entity.vo.OperationBtnVo;
import com.example.test.mapper.OperationBtnMapper;
import com.example.test.service.OperationBtnService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.ResultJson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("operationBtnService")
public class OperationBtnServiceImpl implements OperationBtnService {

    @Autowired
    private OperationBtnMapper operationBtnMapper;

    @Override
    public ResultJson<OperationBtnVo> selectOperationBtn(long id) {
        OperationBtn operationBtn = this.operationBtnMapper.selectOperationBtnById(id);
        return new ResultJson(new OperationBtnVo(operationBtn));
    }

    @Override
    public ResultJson<Boolean> insertOperationBtn(OperationBtnCreateDTO createDTO) {
        OperationBtn  operationBtn = new OperationBtn();
        BeanUtils.copyProperties(createDTO, operationBtn);
        if (this.operationBtnMapper.insertOperationBtn(operationBtn)){
            return new ResultJson<>(EnumsUtils.SUCCESS, true);
        }
        return null;
    }
}
