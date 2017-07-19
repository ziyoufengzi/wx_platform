/**
 * WxReportcard.java
 * Copyright© 2017 北京金风易通科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-19 Created
 */
package com.jfsoft.model;

import java.util.Date;

/**
 * 微信报告单表
 * 
 * @author wanggang
 * @version 1.0 2017-07-19
 */
public class WxReportcard {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 医院编码
     */
    private Integer hospitalId;

    /**
     * 体检号
     */
    private String testNo;

    /**
     * 报告单名称
     */
    private String reportName;

    /**
     * 报告单时间
     * 默认：CURRENT_TIMESTAMP
     */
    private Date reportTime;

    /**
     * 报告单内容
     */
    private String reportContent;

    /**
     * 报告单图片路径
     */
    private String reportImgurl;

    /**
     * 报告单类型:1,Lis;2,PEIS;3,通用类消息
     */
    private String reportType;

    /**
     * 是否删除:0,否;1,是
     */
    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo == null ? null : testNo.trim();
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent == null ? null : reportContent.trim();
    }

    public String getReportImgurl() {
        return reportImgurl;
    }

    public void setReportImgurl(String reportImgurl) {
        this.reportImgurl = reportImgurl == null ? null : reportImgurl.trim();
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

}
