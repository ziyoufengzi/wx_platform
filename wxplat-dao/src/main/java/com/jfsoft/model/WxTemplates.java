/**
 * WxTemplates.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-19 Created
 */
package com.jfsoft.model;

/**
 * 微信模板
 * @author wanggang
 * @version 1.0 2017-07-19
 */
public class WxTemplates {

    /**
     * 医院编码
     */
    private String hospitalid;

    /**
     * appId
     */
    private String appid;

    /**
     * 模板ID
     */
    private String templateid;

    /**
     * 10:LIS  20:PEIS  30:通用
     */
    private Integer temptype;

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid == null ? null : hospitalid.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid == null ? null : templateid.trim();
    }

    public Integer getTemptype() {
        return temptype;
    }

    public void setTemptype(Integer temptype) {
        this.temptype = temptype;
    }

}
