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
@ApiModel(value = "UserModifyStatusDTO", description = "启用(禁用)用户DTO")
public class UserModifyStatusDTO {

    @BaozunApiParam(name = "userId", description = "用户id", type = ParamType.JSON_BODY, valid = "required|isNaturalNoZero")
    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "long")
    private long userId;

    @BaozunApiParam(name = "status", description = "用户状态", type = ParamType.JSON_BODY, valid = "required|isNaturalNoZero")
    @ApiModelProperty(name = "status", value = "用户状态", required = true, dataType = "long")
    private int status;
}
