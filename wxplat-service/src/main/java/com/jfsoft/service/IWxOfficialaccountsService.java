package com.jfsoft.service;

import com.jfsoft.model.WxOfficialaccounts;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/14  13:44
 * @Description TODO(一句话描述类作用)
 */
public interface IWxOfficialaccountsService {
    /**
     * 根据appId查询appSecret
     * @param appId
     * @return
     */
    WxOfficialaccounts selectAppSecretByAppId(String appId);

    /**
     * 查询医院详情
     * wanggang
     * 2017-7-19 14:12:11
     */
    public WxOfficialaccounts getDetail(String hosId) throws Exception;

}
