package com.jfsoft.report.controller;

import base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxReportcard;
import com.jfsoft.report.service.IWxReportcardService;
import com.jfsoft.utils.WeixinUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报告单
 * wanggang
 * 2017/7/19
 */
@Controller
@RequestMapping(value = "/report")
@Api(value = "/report", description = "报告单管理")
public class ReportCardController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IWxReportcardService wxReportcardService;
    
    /**
     * 查询报告单列表
     * chenxiaochun
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getReportList(String pageNum, String pageSize){

        logger.debug("查询医院信息列表");

        Map<String, Object> result = new HashMap<String, Object>();

        List<WxReportcard> wxReportcardList = new ArrayList<WxReportcard>();
        int count = 0;

        try {
            wxReportcardList = wxReportcardService.findReportList(pageNum, pageSize);

            result.put("list", wxReportcardList);
            result.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("code", 0);
        result.put("message", "获取成功");

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxOfficialaccounts.class,
                "id", "reportName", "reportTimeStr", "reportImgurl");

        String wxReportcardJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return wxReportcardJson;
    }

    /**
     * 查询报告单详情
     * wanggang
     * 2017-7-19 13:27:49
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
    public String getDetail(String id) {

        WxReportcard wxReportcard = null;

        try {
            wxReportcard = wxReportcardService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxOfficialaccounts.class,
                "id", "reportName", "reportTimeStr", "reportImgurl");

        String detailJson = JSON.toJSONString(wxReportcard, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return detailJson;
    }

}
