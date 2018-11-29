package com.example.test.controller;

import com.example.test.entity.dto.UserMenuCreateDTO;
import com.example.test.entity.po.UserMenu;
import com.example.test.entity.vo.UserMenuVo;
import com.example.test.service.UserMenuService;
import com.example.test.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @RequestMapping(value = "createUserMenu.do", method = RequestMethod.POST)
    public ResultJson<UserMenuVo> createUserMenu(@RequestBody UserMenuCreateDTO userMenu){
        return this.userMenuService.insertUserMenu(userMenu);
    }
}
