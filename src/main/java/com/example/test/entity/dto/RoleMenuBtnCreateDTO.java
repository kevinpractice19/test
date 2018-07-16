package com.example.test.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "RoleMenuBtnCreateDTO", description = "创建角色按钮关联DTO")
public class RoleMenuBtnCreateDTO {

    private long roleId;

    private long menuBtnId;

}
