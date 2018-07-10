package com.example.test.entity.vo;

import com.example.test.entity.po.OperationBtn;
import lombok.*;
import org.joda.time.DateTime;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationBtnVo {

    private long id;

    private String menuId;

    private String btnCode;
    private String btnName;
    private String btnTitle;
    private String description;

    private String createTime;
    private String updateTime;

    public OperationBtnVo(OperationBtn operationBtn) {
        this.id = operationBtn.getId();
        this.menuId = operationBtn.getMenuId();
        this.btnCode = operationBtn.getBtnCode();
        this.btnName = operationBtn.getBtnName();
        this.btnTitle = operationBtn.getBtnTitle();
        this.description = operationBtn.getDescription();
        this.createTime = new DateTime(operationBtn.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
        this.updateTime = new DateTime(operationBtn.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
    }

}
