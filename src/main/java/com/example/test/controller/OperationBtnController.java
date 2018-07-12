package com.example.test.controller;

import com.example.test.entity.dto.OperationBtnCreateDTO;
import com.example.test.entity.vo.OperationBtnVo;
import com.example.test.service.OperationBtnService;
import com.example.test.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/operationBtn")
public class OperationBtnController {

    @Autowired
    private OperationBtnService operationBtnService;

    @RequestMapping(value = "/createOperationBtn.do", method = RequestMethod.POST)
    public ResultJson<OperationBtnVo> createOperationBtn(@RequestBody OperationBtnCreateDTO createDTO) {
        return this.operationBtnService.insertOperationBtn(createDTO);
    }

    @RequestMapping(value = "/getOperationBtnById.do", method = RequestMethod.GET)
    public ResultJson<OperationBtnVo> getOperationBtnById(@RequestParam("id") long id) {
        return this.operationBtnService.selectOperationBtn(id);
    }

}
