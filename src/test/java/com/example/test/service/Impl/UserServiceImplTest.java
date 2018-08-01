package com.example.test.service.Impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.test.entity.dto.UserCreateDTO;
import com.example.test.entity.dto.UserModifyDTO;
import com.example.test.entity.po.User;
import com.example.test.entity.vo.UserVo;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import com.example.test.utils.ResultJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    private static Date date = new Date();

    private static User user = new User() ;
    static {
        user.setId(2L);
        user.setAccount("15271050320");
        user.setPassword("888888");
        user.setUserName("aaa");
        user.setLastLoginIp("192.168.1.110");
        user.setLastLoginTime(date);
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setUserToken("97f8d6726da39cfee50478f63f72a416");
        user.setStatus(1);
    }

    @Mock
    private UserMapper userMapper;

    @Before
    public void setUp() {
        Mockito.when(userMapper.selectUserById(1L)).thenReturn(user);

    }

    @Test
    public void selectUserById() {
        ResultJson<UserVo> userVoResultJson = this.userService.selectUserById(1L);
        assertNotNull(userVoResultJson.getData());
        assertEquals(userVoResultJson.getData().getAccount(), "15271050320");
        System.out.println(userVoResultJson.getData().getAccount());
    }


    @Test
    public void insertUser() {
        User user = new User();
        user.setId(2L);
        user.setAccount("15271050320");
        user.setPassword("888888");
        user.setUserName("aaa");
        user.setLastLoginIp("192.168.1.110");
        user.setLastLoginTime(new Date());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserToken("97f8d6726da39cfee50478f63f72a416");
        user.setStatus(1);
//        Mockito.when(userMapper.insertUser(user)).thenReturn(true);
        UserCreateDTO createDTO = new UserCreateDTO();
        BeanUtils.copyProperties(user, createDTO);
        ResultJson<UserVo> userVoResultJson = this.userService.insertUser(createDTO);
        assertNotNull(userVoResultJson.getData().getAccount());

    }

    @Test
    public void updateUserByIdTest() {
        UserModifyDTO userModifyDTO = new UserModifyDTO();

        userModifyDTO.setUserId(user.getId());
        userModifyDTO.setAccount(user.getAccount());
        userModifyDTO.setPassword(user.getPassword());
        userModifyDTO.setUserName(user.getUserName());
        userModifyDTO.setLastLoginIp(user.getLastLoginIp());
        userModifyDTO.setLastLoginTime(user.getLastLoginTime());
        userModifyDTO.setCreateTime(user.getCreateTime());
        userModifyDTO.setUpdateTime(user.getUpdateTime());
        userModifyDTO.setUserToken(user.getUserToken());
        userModifyDTO.setStatus(user.getStatus());

        Mockito.when(userMapper.updateUserByIdTest(userModifyDTO)).thenReturn(true);
        ResultJson<UserVo> userVo = userService.updateUserByIdTest(userModifyDTO);
        assertNotNull(userVo);
        System.out.println(userVo.getData().getAccount());
    }
}
//
//      userModifyDTO.setUserId(2L);
//        userModifyDTO.setAccount("15271050320");
//        userModifyDTO.setPassword("888888");
//        userModifyDTO.setUserName("aaa");
//        userModifyDTO.setLastLoginIp("192.168.1.110");
//        userModifyDTO.setLastLoginTime(new Date());
//        userModifyDTO.setCreateTime(new Date());
//        userModifyDTO.setUpdateTime(new Date());
//        userModifyDTO.setUserToken("97f8d6726da39cfee50478f63f72a416");
//        userModifyDTO.setStatus(1);