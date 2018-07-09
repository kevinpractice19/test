package com.example.test.entity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuCreateDTO {

    private String menuName;

    private String menuPath;

    private long parentId;

//    @ApiModelProperty(name = "envName", value = "环境名称", required = false, dataType = "String")
//    private String roleName;
}
