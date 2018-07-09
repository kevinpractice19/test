package com.example.test.entity.dto;

import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyPasswordDTO {
    private long userId;

    private String password;

    private String newPassword;

}
