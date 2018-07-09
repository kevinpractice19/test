package com.newnoa.govern.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserPageDTO", description = "用户分页DTO")
public class UserPageDTO {

    @ApiModelProperty(name = "pageNum", value = "页码", required = false, dataType = "int")
    private int pageNum;

    @ApiModelProperty(name = "pageSize", value = "分页数", required = false, dataType = "int")
    private int pageSize;

    @ApiModelProperty(name = "account", value = "账号", required = false, dataType = "String")
    private String account;

    @ApiModelProperty(name = "userName", value = "用户名", required = false, dataType = "String")
    private String userName;
}
