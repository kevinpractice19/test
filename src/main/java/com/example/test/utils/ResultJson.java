package com.newnoa.govern.common.json;

import com.newnoa.govern.common.util.EnumsUtils;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@ToString
@NoArgsConstructor
public class ResultJson<T> implements Serializable{

        /**
         * 返回编码
         */
        private int code;

        /**
         * 返回消息
         */
        private String msg;

        /**
         * 返回数据
         */
        private T data;



        public ResultJson(T date) {
            this.data = date;
        }

        public ResultJson(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public ResultJson(int code, T data) {
            this.code = code;
            this.data = data;
        }

        public ResultJson(String msg, T data) {
            this.msg = msg;
            this.data = data;
        }

        public ResultJson(EnumsUtils enumsUtils, T data) {
            this.code = enumsUtils.getCode();
            this.msg = enumsUtils.getMsg();
            this.data = data;
        }

        public ResultJson(EnumsUtils enumsUtils) {
            this.code = enumsUtils.getCode();
            this.msg = enumsUtils.getMsg();
        }

}
