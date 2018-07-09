package com.example.test.entity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuModifyDTO {

    private long menuId;

    private String menuName;

    private String menuPath;

    private long parentId;
}
