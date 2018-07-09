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
@ApiModel(value = "UserModifyDTO", description = "修改用户DTO")
public class UserModifyDTO {

    @BaozunApiParam(name = "userId", description = "用户id", type = ParamType.JSON_BODY, valid = "required")
    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "long")
    private long userId;

    @BaozunApiParam(name = "userName", description = "用户名", type = ParamType.JSON_BODY, valid = "required|maxLength[20]")
    @ApiModelProperty(name = "userName", value = "用户名", required = true, dataType = "String")
    private String userName;


}
