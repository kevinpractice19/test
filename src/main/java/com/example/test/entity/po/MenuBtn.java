package com.example.test.entity.po;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuBtnVo {

    private long id;

    private long menuId;

    private String btnCode;
    private String btnName;
    private String btnTitle;
    private String description;

    private Date createTime;
    private Date updateTime;


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuBtnVo) {
            MenuBtnVo operationBtn = (MenuBtnVo) obj;
            return this.menuId==(operationBtn.getMenuId()) && this.btnCode.equals(operationBtn.getBtnCode()) && this.btnName.equals(operationBtn.getBtnName()) && btnTitle.equals(operationBtn.getBtnTitle())
                    && this.description.equals(operationBtn.getDescription()) && this.createTime.equals(operationBtn.getCreateTime()) && this.updateTime.equals(operationBtn.getUpdateTime());
        }
        return super.equals(obj);
    }
}
