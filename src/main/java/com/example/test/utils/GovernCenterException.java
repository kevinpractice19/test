package com.newnoa.govern.common.util;

import com.newnoa.framework.exception.NoaException;

public class GovernCenterException extends NoaException {


    public GovernCenterException(EnumsUtils enumsUtils) {
        super(enumsUtils.getMsg());
        super.setErrCode(enumsUtils.getCode());
        super.setErrMsg(enumsUtils.getMsg());
    }

    public GovernCenterException(Integer code, String msg) {
        super(msg);
        super.setErrCode(code);
        super.setErrMsg(msg);
    }


}
