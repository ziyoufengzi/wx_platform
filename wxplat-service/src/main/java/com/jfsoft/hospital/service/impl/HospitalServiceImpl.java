package com.jfsoft.hospital.service.impl;

import com.jfsoft.hospital.service.IHospitalService;
import com.jfsoft.mapper.WxOfficialaccountsMapper;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院信息业务接口实现类
 * wanggang
 * 2017年7月17日09:48:02
 */
@Service
public class HospitalServiceImpl implements IHospitalService {

    @Autowired
    private WxOfficialaccountsMapper wxOfficialaccountsMapper;

    public List<WxOfficialaccounts> findHospitalInfoList(String hospitalName, String pageNum, String pageSize) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        if(!StringUtils.isBlank(hospitalName)) {
            params.put("hospitalName", hospitalName.trim());
        }
        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        params.put("pageStart", (pageNumInt - 1)*pageSizeInt);
        params.put("pageSize", pageSizeInt);

        List<WxOfficialaccounts> hospitalInfoList = wxOfficialaccountsMapper.findPage(params);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (WxOfficialaccounts wx : hospitalInfoList) {
            Date createTime = wx.getCreatetime();
            String createTimeStr = sdf.format(createTime);
            wx.setCreatetimeStr(createTimeStr);
        }

        return hospitalInfoList;
    }

    public int findHospitalInfoCount(String hospitalName) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        if(!StringUtils.isBlank(hospitalName)) {
            params.put("hospitalName", hospitalName.trim());
        }

        int count = wxOfficialaccountsMapper.findPageCount(params);

        return count;
    }

    public int save(WxOfficialaccounts wxOfficialaccounts) throws Exception {

        wxOfficialaccounts.setCreatetime(new Date());
        wxOfficialaccounts.setDeltag(Integer.parseInt(Constants.IS_DELETE_FALSE));

        int count = wxOfficialaccountsMapper.insert(wxOfficialaccounts);

        return count;
    }

}
