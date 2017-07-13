/**
 * WxForward.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 转发人
 * 
 * @author wanggang
 * @version 1.0 2017-07-13
 */
public class WxForward {

    /**
     */
    private Integer id;

    /**
     * 微信用户唯一ID
     */
    private String openid;

    /**
     * 转发人姓名
     */
    private String name;

    /**
     * 转发人电话
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
