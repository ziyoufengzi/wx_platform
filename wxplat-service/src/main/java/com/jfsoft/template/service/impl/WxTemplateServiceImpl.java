package com.jfsoft.template.service.impl;

import com.jfsoft.mapper.WxTemplatesMapper;
import com.jfsoft.model.WxTemplates;
import com.jfsoft.template.service.IWxTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信模板业务接口实现类
 * wanggang
 * 2017/7/19
 */
@Service
public class WxTemplateServiceImpl implements IWxTemplateService {

    @Autowired
    private WxTemplatesMapper wxTemplatesMapper;

    public WxTemplates getDetail(String hospitalId, String appId, String tempType) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("hospitalId", hospitalId);
        params.put("appId", appId);
        params.put("tempType", tempType);

        return wxTemplatesMapper.getDetail(params);
    }

}
