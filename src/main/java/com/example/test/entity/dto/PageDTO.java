package com.example.test.entity.dto;

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
@ApiModel(value = "PageDTO", description = "分页DTO")
public class PageDTO {

    @ApiModelProperty(name = "pageNum", value = "页码", required = false, dataType = "int")
    private int pageNum;

    @ApiModelProperty(name = "pageSize", value = "分页数", required = false, dataType = "int")
    private int pageSize;

}
