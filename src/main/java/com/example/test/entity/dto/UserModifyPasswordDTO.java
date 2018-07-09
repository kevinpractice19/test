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
@ApiModel(value = "UserModifyPasswordDTO", description = "修改用户密码DTO")
public class UserModifyPasswordDTO {

    @BaozunApiParam(name = "userId", description = "用户id", type = ParamType.JSON_BODY, valid = "required")
    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "long")
    private long userId;

    @BaozunApiParam(name = "password", description = "用户旧密码", type = ParamType.JSON_BODY, valid = "required|maxLength[50]")
    @ApiModelProperty(name = "password", value = "用户旧密码", required = true, dataType = "String")
    private String password;

    @BaozunApiParam(name = "newPassword", description = "用户新密码", type = ParamType.JSON_BODY, valid = "required|maxLength[50]")
    @ApiModelProperty(name = "newPassword", value = "用户新密码", required = true, dataType = "String")
    private String newPassword;

}
