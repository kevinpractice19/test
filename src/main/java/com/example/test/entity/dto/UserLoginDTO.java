package com.newnoa.govern.entity.dto;

import com.noa.common.annotation.BaozunApiParam;
import com.noa.common.annotation.ParamType;
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
@ApiModel(value = "UserLoginDTO", description = "用户登录DTO")
public class UserLoginDTO {

    @BaozunApiParam(name = "account", description = "账号", type = ParamType.JSON_BODY, valid = "required|Mobile|maxLength[11]")
    @ApiModelProperty(name = "account", value = "用户账号", required = true, dataType = "String")
    private String account;

    @BaozunApiParam(name = "password", description = "用户密码", type = ParamType.JSON_BODY, valid = "required|maxLength[50]")
    @ApiModelProperty(name = "password", value = "用户密码", required = true, dataType = "String")
    private String password;

}
