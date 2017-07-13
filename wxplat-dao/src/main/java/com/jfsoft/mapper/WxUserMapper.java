/**
 * WxUserMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxUser;

/**
 * 微信用户表
 * @author wanggang
 * 2017年7月13日 上午9:55:59
 */
public interface WxUserMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);
    
}