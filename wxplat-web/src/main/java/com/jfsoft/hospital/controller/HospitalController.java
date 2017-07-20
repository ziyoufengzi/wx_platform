package com.jfsoft.hospital.controller;

import base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jfsoft.hospital.service.IHospitalService;
import com.jfsoft.model.WxOfficialaccounts;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院信息管理
 * wanggang
 * 2017年7月17日
 */
@Controller
@RequestMapping(value = "/hos")
@Api(value = "/hos", description = "医院信息管理")
public class HospitalController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IHospitalService hospitalService;

    /**
     * 医院信息列表
     * wanggang
     * 2017-7-17 09:30:17
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toListPage() {

        return "hospital/table";
    }

    /**
     * 查询医院信息列表数据
     * wanggang
     * 2017-7-17 09:47:14
     * @param pageIndex 页码
     * @param pageSize 每页显示的条数
     */
    @ResponseBody
    @RequestMapping(value = "/listDatas", method = RequestMethod.POST)
    public String getJsonData(String hosName, String pageIndex, String pageSize) {

        logger.debug("查询医院信息列表");

        Map<String, Object> result = new HashMap<String, Object>();

        List<WxOfficialaccounts> hospitalInfoList = new ArrayList<WxOfficialaccounts>();
        int count = 0;

        try {
            hospitalInfoList = hospitalService.findHospitalInfoList(hosName, pageIndex, pageSize);
            count = hospitalService.findHospitalInfoCount(hosName);

            result.put("list", hospitalInfoList);
            result.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("code", 0);
        result.put("message", "获取成功");

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxOfficialaccounts.class,
                "id", "hosid", "name", "appid", "appsecret", "tel", "createtimeStr");

        String hospitalJson = JSON.toJSONString(result, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return hospitalJson;
    }

    /**
     * 跳转到新增页面
     * wanggang
     * 2017-7-18 09:53:20
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd() {

        return "hospital/add";
    }

    /**
     * 新增医院信息
     * wanggang
     * 2017-7-20 11:58:05
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(HttpSession session, WxOfficialaccounts hospitalInfo) {

        String usercode = getUserCodeFromSession(session);
        hospitalInfo.setCreateper(usercode);

        try {
            hospitalService.save(hospitalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:list";
    }

    /**
     * 跳转到编辑页面
     * wanggang
     * 2017-7-18 09:53:20
     */
    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit(Map<String, Object> map, String hosId) {

        try {
            WxOfficialaccounts hospital = hospitalService.getDetail(hosId);
            map.put("hos", hospital);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hospital/edit";
    }

    /**
     * 修改医院信息
     * wanggang
     * 2017-7-20 11:58:05
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpSession session, WxOfficialaccounts hospitalInfo) {

        try {
            hospitalService.update(hospitalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:list";
    }

    /**
     * 删除医院信息
     * wanggang
     * 2017-7-20 13:35:48
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public int delete(String hosId) {

        int deleteCount = 0;
        try {
            deleteCount = hospitalService.delete(hosId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleteCount;
    }

}
