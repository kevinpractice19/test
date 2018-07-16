package com.example.test.entity.vo;

import com.example.test.entity.po.MenuBtn;
import lombok.*;
import org.joda.time.DateTime;

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
    private int status;
    private String statusStr;
    private String createTime;
    private String updateTime;

    public MenuBtnVo(MenuBtn menuBtn) {
        this.id = menuBtn.getId();
        this.menuId = menuBtn.getMenuId();
        this.btnCode = menuBtn.getBtnCode();
        this.btnName = menuBtn.getBtnName();
        this.btnTitle = menuBtn.getBtnTitle();
        this.description = menuBtn.getDescription();
        this.status = menuBtn.getStatus();
        this.statusStr = menuBtn.getStatus() == 1 ? "使用" : "禁用";
        this.createTime = new DateTime(menuBtn.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss");
        this.updateTime = new DateTime(menuBtn.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss");
    }

}
