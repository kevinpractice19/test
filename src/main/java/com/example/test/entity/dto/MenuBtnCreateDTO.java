package com.example.test.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationBtnCreateDTO {
    private long menuId;
    private long roleId;
    private String btnCode;
    private String btnName;
    private String btnTitle;
    private String description;
}
