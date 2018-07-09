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
@ApiModel(value = "UserResetPasswordDTO", description = "重置用户密码DTO")
public class UserResetPasswordDTO {

    @BaozunApiParam(name = "account", description = "账号", type = ParamType.JSON_BODY, valid = "required|Mobile|maxLength[11]")
    @ApiModelProperty(name = "account", value = "账号", required = true, dataType = "String")
    private String account;

}
