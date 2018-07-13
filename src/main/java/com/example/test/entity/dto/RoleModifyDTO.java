package com.example.test.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "RoleModifyDTO", description = "修改角色信息DTO")
public class RoleModifyDTO {

    @ApiModelProperty(name = "roleId", value = "角色id", required = true, dataType = "long")
    private long roleId;


    @ApiModelProperty(name = "roleName", value = "角色名", required = true, dataType = "String")
    private String roleName;

    @ApiModelProperty(name = "remark", value = "分页数", required = false, dataType = "String")
    private String remark;


}
