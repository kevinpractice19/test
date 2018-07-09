package com.newnoa.govern.entity.dto;

import com.noa.common.annotation.BaozunApiParam;
import com.noa.common.annotation.ParamType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "MenuCreateDTO", description = "创建菜单DTO")
public class MenuCreateDTO {

    @BaozunApiParam(name = "menuName", description = "菜单名称", type = ParamType.JSON_BODY, valid = "required")
    @ApiModelProperty(name = "menuName", value = "菜单名称", required = true, dataType = "String")
    private String menuName;

    @BaozunApiParam(name = "menuPath", description = "菜单路径", type = ParamType.JSON_BODY, valid = "required")
    @ApiModelProperty(name = "menuPath", value = "菜单路径", required = true, dataType = "String")
    private String menuPath;

    @BaozunApiParam(name = "parentId", description = "父菜单id", type = ParamType.JSON_BODY, valid = "required|number")
    @ApiModelProperty(name = "parentId", value = "父菜单id", required = true, dataType = "long")
    private long parentId;

//    @ApiModelProperty(name = "envName", value = "环境名称", required = false, dataType = "String")
//    private String roleName;
}
