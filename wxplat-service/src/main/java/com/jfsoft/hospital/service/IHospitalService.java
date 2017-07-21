package com.jfsoft.hospital.service;

import com.jfsoft.model.WxOfficialaccounts;

import java.util.List;

/**
 * 医院信息业务接口
 * wanggang
 * 2017年7月17日09:48:02
 */
public interface IHospitalService {

    /**
     * 分页查询医院信息列表
     * @param hospitalName 医院名称
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return
     * @throws Exception
     */
    public List<WxOfficialaccounts> findHospitalInfoList(String hospitalName, String pageNum, String pageSize) throws Exception;

    /**
     * 查询医院信息总数
     * @param hospitalName
     * @return
     * @throws Exception
     */
    public int findHospitalInfoCount(String hospitalName) throws Exception;

    /**
     * 新增医院信息
     * @author wanggang
     * 2017/7/18 10:52
     * @param
     */
    public int save(WxOfficialaccounts wxOfficialaccounts) throws Exception;

    /**
     * 获取医院详情
     * wanggang
     * 2017-7-20 11:02:26
     */
    public WxOfficialaccounts getDetail(String hosId) throws Exception;

    /**
     * 更新医院信息
     * wanggang
     * 2017-7-20 11:02:26
     */
    public int update(WxOfficialaccounts wxOfficialaccounts) throws Exception;

    /**
     * 删除医院信息
     * wanggang
     * 2017-7-20 13:34:37
     */
    public int delete(String hosId) throws Exception;

}
