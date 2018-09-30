package com.example.test.service.Impl;

import com.example.test.entity.dto.UserCreateDTO;
import com.example.test.entity.dto.UserModifyDTO;
import com.example.test.entity.dto.UserModifyStatusDTO;
import com.example.test.entity.po.Role;
import com.example.test.entity.po.User;
import com.example.test.entity.po.UserRole;
import com.example.test.entity.vo.UserVo;
import com.example.test.mapper.RoleMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.mapper.UserRoleMapper;
import com.example.test.utils.ResultJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    private static Date date = new Date();
    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private UserRoleMapper userRoleMapper;

    private User user = new User();
    private UserCreateDTO createDTO = new UserCreateDTO();
    private UserRole userRole = new UserRole();
    private Role role = new Role();

    private UserModifyDTO userModifyDTO = new UserModifyDTO();

    private UserModifyStatusDTO userModifyStatusDTO = new UserModifyStatusDTO();


    @Before
    public void setUp() {
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
        role.setId(1L);
        role.setRoleId(1L);
        role.setRoleName("test");
        role.setRemark("test");
        role.setCreateTime(date);
        role.setUpdateTime(date);
        userRole.setId(1L);
        userRole.setUserId(user.getId());
        userRole.setRoleId(createDTO.getRoleId());
        userModifyDTO.setUserId(2L);
        userModifyDTO.setAccount("15271050320");
        userModifyDTO.setPassword("888888");
        userModifyDTO.setUserName("aaa");
        userModifyDTO.setLastLoginIp("192.168.1.110");
        userModifyDTO.setLastLoginTime(new Date());
        userModifyDTO.setCreateTime(new Date());
        userModifyDTO.setUpdateTime(new Date());
        userModifyDTO.setUserToken("97f8d6726da39cfee50478f63f72a416");
        userModifyDTO.setStatus(1);
        BeanUtils.copyProperties(user, createDTO);
        createDTO.setRoleId(1L);
        BeanUtils.copyProperties(userModifyDTO, user);
        user.setId(userModifyDTO.getUserId());
        userModifyStatusDTO.setUserId(user.getId());
        userModifyStatusDTO.setStatus(2);
        Mockito.when(userMapper.updateUserStatusById(userModifyStatusDTO.getUserId(), userModifyStatusDTO.getStatus())).thenReturn(true);
        Mockito.when(userMapper.selectUserById(2L)).thenReturn(user);
        Mockito.when(roleMapper.selectRoleById(1L)).thenReturn(role);
        Mockito.when(userMapper.insertUser(user)).thenReturn(true);
        Mockito.when(userRoleMapper.insertUserRole(userRole)).thenReturn(true);
        Mockito.when(userMapper.updateUserByIdTest(user)).thenReturn(true);


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
        ResultJson<UserVo> userVoResultJson = this.userService.insertUser(createDTO);
        assertNotNull(userVoResultJson);

    }

    @Test
    public void updateUserByIdTest() {
        ResultJson<UserVo> userVo = userService.updateUserByIdTest(userModifyDTO);
        assertNotNull(userVo);
        System.out.println(userVo.getData().getAccount());
    }

    @Test
    public void updateUserStatusById() {
        ResultJson<Boolean> userVoResultJson = this.userService.updateUserStatusById(userModifyStatusDTO);
        assertEquals(userVoResultJson.getData(), true);
    }
}
