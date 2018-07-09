package com.example.test.utils;


import lombok.Getter;

@Getter
public enum EnumsUtils {

        SUPER_ADMIN_ID(1, "超级管理员"),

        SUCCESS(100, "操作成功"),

        NONE_NOTES(100, "没有该信息"),

        INSERT_FAIL(210, "添加失败"),

        DELETE_FAIL(220, "删除失败"),

        UPDATE_FAIL(230, "修改失败"),

        SUPER_ADMIN(240, "该用户是超级管理员不可删除"),

        DELETE_SUBMENU(250, "此菜单是父菜单,请先删除所有子菜单"),

        RESET_PASSWORD_FAIL(260,"密码重置失败"),

        FIND_FAIL(300, "查询失败"),

        FIND_IS_EXISTS(310, "该记录已存在, 请勿重复提交"),

        FIND_NOT_EXISTS(320, "该记录不存在"),

        USER_NOT_EXISTS(330, "该用户不存在"),

        ACCOUNT_PASSWORD_MISMATCH(340, "账号密码不匹配"),

        ACCOUNT_IS_ENABLE(350, "此账号已被禁用"),

        MENU_PATH_NOT_EMPTY(360, "当前添加菜单为子菜单，路径不能为空"),

        PROJECT_NAME_NUMBER_IS_EXISTS(370, "当前项目名或项目编号已存在"),

        TOKEN_IS_NULL(401,"token不存在"),

        APPID_NOT_EXISTS(402, "APPID不存在"),

        TEAM_NOT_EXISTS(404, "TEAM不存在"),

        TEAM_IS_CHINESE(403, "请输入团队英文名称，如（casaba）"),

        APP_IS_CHINESE(405, "appid请输入英文，如（payment）"),

        APPID_IS_EXISTS(406, "APPID已存在"),

        TEAM_IS_EXISTS(407, "TEAM已存在");


        private int code;

        private String msg;

        EnumsUtils(int code, String msg) {
                this.code = code;
                this.msg = msg;
        }


}
