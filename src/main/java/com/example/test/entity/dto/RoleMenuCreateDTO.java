package com.example.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuCreateDTO {

    private long roleId;

    private List<String> menuNameList;

//    private long menuBtnId;
//
//    private long menuType;
}
