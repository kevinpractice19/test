package com.newnoa.govern.mapper;

import com.newnoa.govern.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userMapper")
public interface UserMapper {

        /**
         * 查询所有用户
         *
         * @return
         */
        List<User> selectUser(@Param("account") String account, @Param("userName") String userName);

        /**
         * 根据用户ID查询用户详情
         *
         * @param userId
         * @return
         */
        User selectUserById(@Param("userId") long userId);

        /**
         * 用户登录
         *
         * @param account
         * @param password
         * @return
         */
        User getLogin(@Param("account") String account, @Param("password") String password);

        /**
         * 创建新用户
         *
         * @param user
         * @return
         */
        boolean insertUser(@Param("user") User user);

        /**
         * 根据用户id启用该用户
         *
         * @param userId
         * @return
         */
        boolean updateUserStatusById(@Param("userId") long userId, @Param("status") int status);

        /**
         * 修改用户信息
         *
         * @param user
         * @return
         */
        boolean updateUserById(@Param("user") User user);

        /**
         * 根据用户账号查询该用户信息
         *
         * @param account
         * @return
         */
        User selectUserByAccount(@Param("account") String account);

        /**
         * 根据账号查询该账号是否存在
         *
         * @param account
         * @return
         */
        int selectUserByAccountCount(@Param("account") String account);

        /**
         * 根据用户id更新密码
         * @param map
         * @return
         */
        boolean updateUserByPassword(Map<String, Object> map);

        /**
         * 根据用户token查询
         * @param userToken
         * @return
         */
        User selectUserByToken(@Param("userToken") String userToken);

        /**
         * 更新用户token
         * @param user
         * @return
         */
        boolean updateUserByUserToken(@Param("user") User user);

        /**
         * 重置密码
         * @param account
         * @param password
         * @return
         */
        boolean resetPassword(@Param("account") String account, @Param("password") String password);

}
