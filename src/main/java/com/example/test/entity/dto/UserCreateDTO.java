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
@ApiModel(value = "UserCreateDTO", description = "创建用户DTO")
public class UserCreateDTO {

    @BaozunApiParam(name = "userName", description = "用户名", type = ParamType.JSON_BODY, valid = "required|maxLength[10]")
    @ApiModelProperty(name = "userName", value = "用户名", required = true, dataType = "String")
    private String userName;

    @BaozunApiParam(name = "account", description = "账号", type = ParamType.JSON_BODY, valid = "required|Mobile|maxLength[11]")
    @ApiModelProperty(name = "account", value = "账号", required = true, dataType = "String")
    private String account;

    @BaozunApiParam(name = "password", description = "密码", type = ParamType.JSON_BODY, valid = "required|maxLength[50]")
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String")
    private String password;

}
