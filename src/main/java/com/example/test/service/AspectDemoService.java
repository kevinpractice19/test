package com.example.test.service;

import com.example.test.entity.vo.AspectDemoVo;
import com.example.test.utils.ResultJson;

public interface AspectDemoService {

    ResultJson<AspectDemoVo> selectAspectDemoById(int id);
}
