/**
 * WxUser.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-13 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 微信用户表
 * 
 * @author wanggang
 * @version 1.0 2017-07-13
 */
public class WxUser {

    /**
     */
    private Integer id;

    /**
     * 医院公众号唯一ID
     */
    private String appid;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 身份证号
     */
    private String cid;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 电话号码
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
    private String deltag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag == null ? null : deltag.trim();
    }

}
