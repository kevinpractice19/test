package com.example.test.utils;

import com.example.test.entity.po.User;
import com.example.test.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationToken {

    @Autowired
    UserMapper userMapper;

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public String generateUser(long userId) throws OAuthSystemException {
        OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String newAccessToken = null;
        newAccessToken = authIssuerImpl.accessToken();
        User user = new User();
        user.setUserToken(newAccessToken);
        user.setId(userId);
        boolean result = userMapper.updateUserByUserToken(user);
        if (!result) {
            log.error("更新token失败!!!");
            throw new GovernCenterException(210, "更新token失败");

        }
        return user.getUserToken();
    }
}
