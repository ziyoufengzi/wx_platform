/**
 * WxOfficialaccountsMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxOfficialaccounts;

/**
 * 医院公众号
 * @author wanggang
 * 2017年7月13日 上午9:54:03
 */
public interface WxOfficialaccountsMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(WxOfficialaccounts record);

    int insertSelective(WxOfficialaccounts record);

    WxOfficialaccounts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxOfficialaccounts record);

    int updateByPrimaryKey(WxOfficialaccounts record);
    
}