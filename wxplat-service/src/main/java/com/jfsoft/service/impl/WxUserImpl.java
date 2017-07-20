package com.jfsoft.service.impl;

import com.jfsoft.mapper.WxUserMapper;
import com.jfsoft.model.WxUser;
import com.jfsoft.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/14  13:54
 * @Description TODO(一句话描述类作用)
 */
@Service
public class WxUserImpl implements IWxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    public int insert(WxUser record) {
        return wxUserMapper.insert(record);
    }

    public int selectCountByOpenId(String openId) {
        return wxUserMapper.selectCountByOpenId(openId);
    }

    public int updateTel(WxUser wxUser) {
        return wxUserMapper.updateTel(wxUser);
    }

    public WxUser getDetail(String tel) throws Exception {
        return wxUserMapper.selectByTel(tel);
    }

}
