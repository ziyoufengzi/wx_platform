package com.jfsoft.report.service;

import com.jfsoft.model.WxReportcard;

import java.util.List;

/**
 * 报告单业务接口
 * wanggang
 * 2017年7月19日 
 */
public interface IWxReportcardService {

    /**
     * 分页查询报告单
     * wanggang
     * 2017-7-19 13:18:11
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<WxReportcard> findReportList(String pageNum, String pageSize) throws Exception;

    /**
     * 查询报告单详情
     * wanggang
     * 2017-7-19 13:24:44
     */
    public WxReportcard findOne(String id) throws Exception;

    /**
     * 保存报告单到微信平台
     * wanggang
     * 2017-7-19 15:26:09
     */
    public int save(WxReportcard wxReportcard) throws Exception;

}
