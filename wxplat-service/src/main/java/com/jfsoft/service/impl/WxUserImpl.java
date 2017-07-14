package com.jfsoft.service.impl;

import com.jfsoft.mapper.WxUserMapper;
import com.jfsoft.model.WxUser;
import com.jfsoft.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/14  13:54
 * @Description TODO(一句话描述类作用)
 */
public class WxUserImpl implements IWxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Override
    public int insert(WxUser record) {
        return wxUserMapper.insert(record);
    }
}
