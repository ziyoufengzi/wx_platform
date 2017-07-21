package com.jfsoft.report.service.impl;

import com.jfsoft.mapper.WxReportcardMapper;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxReportcard;
import com.jfsoft.report.service.IWxReportcardService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wanggang
 * 2017/7/19
 */
@Service
public class WxReportcardServiceImpl implements IWxReportcardService {

    @Autowired
    private WxReportcardMapper wxReportcardMapper;
    
    public List<WxReportcard> findReportList(String pageNum, String pageSize) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        params.put("pageStart", (pageNumInt - 1)*pageSizeInt);
        params.put("pageSize", pageSizeInt);

        List<WxReportcard> wxReportcardList = wxReportcardMapper.findPage(params);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (WxReportcard wx : wxReportcardList) {
            Date reportTime = wx.getReportTime();
            String reportTimeStr = sdf.format(reportTime);
            wx.setReportTimeStr(reportTimeStr);
        }
        
        return wxReportcardList;
    }

    public WxReportcard findOne(String id) throws Exception {

        return wxReportcardMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    public int save(WxReportcard wxReportcard) throws Exception {

        return wxReportcardMapper.insert(wxReportcard);
    }
    
}
