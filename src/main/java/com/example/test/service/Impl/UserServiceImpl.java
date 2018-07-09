package com.newnoa.govern.service.impl;

import com.baozun.framework.entity.PageInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.newnoa.framework.annotation.RepeatSubmit;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.*;
import com.newnoa.govern.entity.dto.*;
import com.newnoa.govern.entity.po.User;
import com.newnoa.govern.entity.po.UserRole;
import com.newnoa.govern.entity.vo.UserVo;
import com.newnoa.govern.interceptor.AuthorizationToken;
import com.newnoa.govern.mapper.UserMapper;
import com.newnoa.govern.mapper.UserRoleMapper;
import com.newnoa.govern.service.OperationRecordService;
import com.newnoa.govern.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userRoleMapper")
    private UserRoleMapper userRoleMapper;

    @Autowired
    private AuthorizationToken authorizationToken;

    @Autowired
    private OperationRecordService operationRecordService;


    @Override
    public ResultJson<PageInfo<UserVo>> pageUser(UserPageDTO pageDTO) {
        if (pageDTO.getPageNum() < 1) {
            pageDTO.setPageNum(Constant.PAGE_NUM);
        }
        if (pageDTO.getPageSize() < 1) {
            pageDTO.setPageSize(Constant.PAGE_SIZE);
        }
        PageInfo<UserVo> pageInfo = new PageInfo<>();
        List<UserVo> voList = new ArrayList<>();
        Page<UserVo> page = PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<User> userList = this.userMapper.selectUser(pageDTO.getAccount(), pageDTO.getUserName());
        userList.forEach(user -> voList.add(new UserVo(user)));
        pageInfo.setPageInfo(pageDTO.getPageNum(), pageDTO.getPageSize(), (int) page.getTotal(), voList);
        return new ResultJson<>(EnumsUtils.SUCCESS, pageInfo);
    }

    @Override
    @CachePut(value = "userVo", key = "#userId+'userVo'")
    public ResultJson<UserVo> selectUserById(long userId) {
        User user = userMapper.selectUserById(userId);
        return user == null ? new ResultJson<>(EnumsUtils.FIND_FAIL) : new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(user));
    }

    @Override
    public ResultJson<UserVo> selectUserByAccount(String account) {
        User user = this.userMapper.selectUserByAccount(account);
        if (user != null && user.getStatus() == 2) {
            return new ResultJson<>(EnumsUtils.ACCOUNT_IS_ENABLE);
        }
        return user == null ? new ResultJson<>(EnumsUtils.USER_NOT_EXISTS) : new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(user));
    }

    @Override
    @RepeatSubmit
    @Transactional
    public ResultJson<UserVo> insertUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userCreateDTO, user);
        try {
            int count = this.userMapper.selectUserByAccountCount(user.getAccount());
            if (count >= 1) {
                throw new GovernCenterException(EnumsUtils.FIND_IS_EXISTS);
            }
            user.setPassword(PasswordUtils.generate(userCreateDTO.getPassword()));
            if (!this.userMapper.insertUser(user)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
            UserRole userRole = new UserRole();
            userRole.setRoleId(1);
            userRole.setUserId(user.getId());
            if (!this.userRoleMapper.insertUserRole(userRole)) {
                throw new GovernCenterException(EnumsUtils.INSERT_FAIL);
            }
        } catch (GovernCenterException e) {
            throw new GovernCenterException(210, e.getMessage());
        }
        this.operationRecordService.insertOperationRecord(user.getId(), Constant.INSERT, Constant.USER_INFO);
        return new ResultJson<>(EnumsUtils.SUCCESS, this.selectUserById(user.getId()).getData());
    }

    @Override
    @RepeatSubmit
    @CacheEvict(value = "userVo", key = "#userModifyStatusDTO.userId+'userVo'")
    public ResultJson<Boolean> updateUserStatusById(UserModifyStatusDTO userModifyStatusDTO) {
        ResultJson<UserVo> resultJson = this.selectUserById(userModifyStatusDTO.getUserId());
        Boolean result = this.userMapper.updateUserStatusById(userModifyStatusDTO.getUserId(), userModifyStatusDTO.getStatus());
        if (resultJson.getData() != null && result) {
            this.operationRecordService.insertOperationRecord(userModifyStatusDTO.getUserId(), Constant.ENABLE, Constant.USER_INFO);
            return new ResultJson<>(EnumsUtils.SUCCESS, true);
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL, false);
    }


    @Override
    @RepeatSubmit
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "userVo", key = "#userModifyDTO.userId+'userVo'")
    public ResultJson<UserVo> updateUserById(UserModifyDTO userModifyDTO) {
        try {
            User user = new User();
            user.setId(userModifyDTO.getUserId());
            BeanUtils.copyProperties(userModifyDTO, user);
            if (!this.userMapper.updateUserById(user)) {
                throw new GovernCenterException(EnumsUtils.UPDATE_FAIL);
            }
            this.operationRecordService.insertOperationRecord(userModifyDTO.getUserId(), Constant.MODIFY, Constant.USER_INFO);
        }catch (GovernCenterException e) {
            throw new GovernCenterException(230, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(this.userMapper.selectUserById(userModifyDTO.getUserId())));

    }

    @Override
    @RepeatSubmit
    @CachePut(value = "userVo", key = "#userModifyPasswordDTO.userId+'userVo'")
    public ResultJson<UserVo> updateUserByPassword(UserModifyPasswordDTO userModifyPasswordDTO) {
        try {
            User result = this.userMapper.selectUserById(userModifyPasswordDTO.getUserId());
            if (result == null) {
                throw new GovernCenterException(EnumsUtils.USER_NOT_EXISTS);
            }
            Boolean verifyOldPwd = PasswordUtils.verify(userModifyPasswordDTO.getPassword(), result.getPassword());
            if (!verifyOldPwd) {
                log.info("用户密码与数据库密码校验不通过");
                throw new GovernCenterException(400, "旧密码错误");
            }
            String newPassword = PasswordUtils.generate(userModifyPasswordDTO.getNewPassword());
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userModifyPasswordDTO.getUserId());
            map.put("password", result.getPassword());
            map.put("newPassword", newPassword);
            if (this.userMapper.updateUserByPassword(map)) {
                this.operationRecordService.insertOperationRecord(userModifyPasswordDTO.getUserId(), Constant.MODIFY, Constant.USER_PASSWORD);
                return new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(this.userMapper.selectUserById(userModifyPasswordDTO.getUserId())));
            }
        }catch (GovernCenterException e) {
            throw new GovernCenterException(230, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.UPDATE_FAIL);
    }

    @Override
    @RepeatSubmit
    public ResultJson<UserVo> resetPassword(UserResetPasswordDTO resetPasswordDTO) {
        try {
            User user = this.userMapper.selectUserByAccount(resetPasswordDTO.getAccount());
            if (user == null) {
                throw new GovernCenterException(EnumsUtils.USER_NOT_EXISTS);
            }
            String randomPassword = RandomStringUtils.randomNumeric(8);
            String password = PasswordUtils.generate(DigestUtils.md5DigestAsHex(randomPassword.getBytes()));
            if (this.userMapper.resetPassword(user.getAccount(), password)) {
                this.operationRecordService.insertOperationRecord(user.getId(), Constant.PASSWORD_RESET, Constant.USER_INFO);
                return new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(user, randomPassword));
            }
        }catch (GovernCenterException e) {
            throw new GovernCenterException(230, e.getMessage());
        }
        return new ResultJson<>(EnumsUtils.RESET_PASSWORD_FAIL);
    }

    @Override
    public ResultJson<UserVo> getLogin(UserLoginDTO userLoginDTO) {
        String account = userLoginDTO.getAccount().replaceAll("\\s*", "");
        int count = userMapper.selectUserByAccountCount(account);
        if (count == 0) {
            return new ResultJson<>(EnumsUtils.USER_NOT_EXISTS);
        }
        User user = this.userMapper.selectUserByAccount(account);
        if (user.getStatus() == 2) {
            return new ResultJson<>(EnumsUtils.ACCOUNT_IS_ENABLE);
        }
        Boolean pwdResult = PasswordUtils.verify(userLoginDTO.getPassword(), user.getPassword());
        if (!pwdResult) {
            log.info("用户密码与数据库密码校验不通过");
            return new ResultJson<>(EnumsUtils.ACCOUNT_PASSWORD_MISMATCH);
        }
        User login = this.userMapper.getLogin(account, user.getPassword());
        if (login == null) {
            return new ResultJson<>(EnumsUtils.ACCOUNT_PASSWORD_MISMATCH);
        }
        String token = null;
        try {
            token = authorizationToken.generateUser(login.getId());
        } catch (OAuthSystemException e) {
            log.error("登录失败", e);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        user.setUserToken(token);
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(IpAddressUtils.getIpAddress(request));
        user.setId(login.getId());
        this.userMapper.updateUserById(user);
//        this.operationRecordService.insertOperationRecord(user.getId(), Constant.LOGIN_IN, Constant.USER_INFO);
        return new ResultJson<>(EnumsUtils.SUCCESS, new UserVo(user));
    }
}