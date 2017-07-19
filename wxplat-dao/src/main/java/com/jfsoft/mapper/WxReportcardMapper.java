/**
 * WxReportcardMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-19 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxReportcard;

/**
 * 微信报告单
 * @author wanggang
 * 2017年7月19日 上午11:18:25
 */
public interface WxReportcardMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WxReportcard record);

    int insertSelective(WxReportcard record);

    WxReportcard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxReportcard record);

    int updateByPrimaryKey(WxReportcard record);
    
}