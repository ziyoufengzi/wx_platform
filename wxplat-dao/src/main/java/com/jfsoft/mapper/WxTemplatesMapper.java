/**
 * WxTemplatesMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-19 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxTemplates;

import java.util.Map;

public interface WxTemplatesMapper {

	int insert(WxTemplates record);

    int insertSelective(WxTemplates record);

    /**
     * 查询微信模板详情
     * wanggang
     * 2017-7-19 15:50:01
     */
    WxTemplates getDetail(Map<String, Object> params);

}