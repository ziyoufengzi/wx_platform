package com.jfsoft.service;

import com.jfsoft.model.WxUser;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/14  13:54
 * @Description TODO(一句话描述类作用)
 */
public interface IWxUserService {

    int insert(WxUser record);

    int selectCountByOpenId(String openId);

    int updateTel(WxUser wxUser);

    /**
     * 查询微信用户详情
     * wanggang
     * 2017-7-19 14:22:01
     */
    public WxUser getDetail(String tel) throws Exception;

}
