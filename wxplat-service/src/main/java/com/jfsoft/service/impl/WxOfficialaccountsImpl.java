package com.jfsoft.service.impl;

import com.jfsoft.mapper.WxOfficialaccountsMapper;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.service.IWxOfficialaccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/14  13:45
 * @Description TODO(一句话描述类作用)
 */
@Service
public class WxOfficialaccountsImpl implements IWxOfficialaccountsService {

    @Autowired
    private WxOfficialaccountsMapper wxOfficialaccountsMapper;

    @Override
    public WxOfficialaccounts selectAppSecretByAppId(String appId) {
        return wxOfficialaccountsMapper.selectAppSecretByAppId(appId);
    }

    public WxOfficialaccounts getDetail(String hosId) throws Exception {

        return wxOfficialaccountsMapper.selectByHosId(hosId);
    }

}
