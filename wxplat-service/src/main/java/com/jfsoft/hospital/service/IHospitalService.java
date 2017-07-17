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

}
