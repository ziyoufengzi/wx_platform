package com.jfsoft.template.service;

import com.jfsoft.model.WxTemplates;

/**
 * 微信模板业务接口
 * wanggang
 * 2017-07-19 15:40
 */
public interface IWxTemplateService {

    /**
     * 获得消息模板详情
     * wanggang
     * 2017年7月19日15:52:14
     */
    public WxTemplates getDetail(String hospitalId, String appId, String tempType) throws Exception;

}
