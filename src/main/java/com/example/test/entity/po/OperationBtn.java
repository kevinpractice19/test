package com.example.test.entity.po;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationBtn {

    private long id;

    private String menuId;

    private String btnCode;
    private String btnName;
    private String btnTitle;
    private String description;

    private Date createTime;
    private Date updateTime;


}
