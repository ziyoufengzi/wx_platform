/**
 * WxOfficialaccountsMapper.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.mapper;

import com.jfsoft.model.WxOfficialaccounts;

import java.util.List;
import java.util.Map;

/**
 * 医院公众号
 * @author wanggang
 * 2017年7月13日 上午9:54:03
 */
public interface WxOfficialaccountsMapper {

    /**
     * 根据appId查询appSecret
     * @param appId
     * @return
     */
    WxOfficialaccounts selectAppSecretByAppId(String appId);

    /**
     * 分页查询医院信息
     * wanggang
     * 2017-7-17 10:30:30
     */
    List<WxOfficialaccounts> findPage(Map<String, Object> params);

    /**
     * 查询医院总数
     * wanggang
     * 2017-7-17 12:40:19
     */
    int findPageCount(Map<String, Object> params);

    /**
     * 新增医院信息
     * @author wanggang
     * 2017/7/18 10:49
     * @param
     */
    int insert(WxOfficialaccounts record);

}