/**
 * WxReportcardMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-19 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxReportcard;

import java.util.List;
import java.util.Map;

/**
 * 微信报告单
 * @author wanggang
 * 2017年7月19日 上午11:18:25
 */
public interface WxReportcardMapper {

    int insert(WxReportcard record);

    int insertSelective(WxReportcard record);

    WxReportcard selectByPrimaryKey(Integer id);

    /**
     * 分页查询报告单
     * wanggang
     * 2017年7月19日
     */
    List<WxReportcard> findPage(Map<String, Object> params);

    /**
     * 查询报告单总数
     * wanggang
     * 2017-7-21 17:51:14
     */
    int findPageCount(Map<String, Object> params);
    
}