/**
 * WxForwardMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxForward;

/**
 * 微信转发人
 * wanggang
 * 2017-7-13
 */
public interface WxForwardMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(WxForward record);

    int insertSelective(WxForward record);

    WxForward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxForward record);

    int updateByPrimaryKey(WxForward record);

}