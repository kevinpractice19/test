package com.example.test.mapper;

import com.example.test.entity.po.OperationBtn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationBtnMapper {

    boolean insertOperationBtn(@Param("operationBtn") OperationBtn operationBtn);

    OperationBtn selectOperationBtnById(@Param("id") long id);

    List<OperationBtn> selectOperationBtn();
}
