/**
 * WxOfficialaccounts.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 医院公众号
 * 
 * @author wanggang
 * @version 1.0 2017-07-13
 */
public class WxOfficialaccounts {

    /**
     */
    private Integer id;

    /**
     * 医院编码
     */
    private String hosid;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 医院公众号appId
     */
    private String appid;

    /**
     * 医院公众号appSecret
     */
    private String appsecret;

    /**
     * 医院电话
     */
    private String tel;

    /**
     * 创建时间
     */
    private Date creattime;

    /**
     * 创建人
     */
    private String creatper;

    /**
     * 删除标志
     */
    private Integer deltag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid == null ? null : hosid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getCreatper() {
        return creatper;
    }

    public void setCreatper(String creatper) {
        this.creatper = creatper == null ? null : creatper.trim();
    }

    public Integer getDeltag() {
        return deltag;
    }

    public void setDeltag(Integer deltag) {
        this.deltag = deltag;
    }

}
