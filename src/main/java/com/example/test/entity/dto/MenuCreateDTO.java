package com.example.test.entity.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuCreateDTO {

    private String menuName;

    private String menuPath;

    private long parentId;

    private List<Long> roleId;
}
