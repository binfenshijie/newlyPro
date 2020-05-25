package com.newly.vas.business.login.service.impl;

import com.newly.vas.business.login.bean.LoginInfoDO;
import com.newly.vas.business.login.mapper.LoginInfoMapper;
import com.newly.vas.business.login.service.LoginInfoService;
import com.newly.common.service.impl.BaseServiceImpl;
import com.newly.vas.business.login.bean.LoginInfoDO;
import com.newly.vas.business.login.mapper.LoginInfoMapper;
import com.newly.vas.business.login.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginInfoServiceImpl extends BaseServiceImpl<LoginInfoMapper, LoginInfoDO> implements LoginInfoService {
    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public LoginInfoMapper getDAO() {
        return loginInfoMapper;
    }
}
