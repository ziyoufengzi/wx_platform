package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jfsoft.model.WxForward;
import com.jfsoft.service.IWxForwardService;
import com.jfsoft.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/17  13:46
 * @Description TODO(转发人控制器)
 */
@RestController
@RequestMapping("/forward")
public class WxForwardController {

    @Autowired
    private IWxForwardService wxForwardService;

    private static Logger logger = Logger.getLogger(WxForwardController.class);

    private static Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 增加转发人
     * @param params
     * @return
     */
    @RequestMapping(value = "/addforward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addForward(String params, String callback){
        try {
            String str = java.net.URLDecoder.decode(java.net.URLDecoder.decode(params, "UTF-8"),"UTF-8");
            WxForward wxForward = JSON.parseObject(str, WxForward.class);
            int i = wxForwardService.insert(wxForward);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", i+"条转发人存储成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return callback + "(" +JSON.toJSONString(map) + ")";
    }

    /**
     * 删除转发人
     * @param tel
     * @return
     */
    @RequestMapping(value = "delforward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String delForward(String tel, String callback){

        int i = wxForwardService.delForward(tel);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", "删除成功" + i + "条记录");
        return callback + "(" +JSON.toJSONString(map) + ")";

    }

    /**
     * 修改转发人
     * @param params
     * @return
     */
    @RequestMapping(value = "upforward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateForward(String params, String callback){
        String str = null;
        try {
            str = java.net.URLDecoder.decode(java.net.URLDecoder.decode(params, "UTF-8"),"UTF-8");
            WxForward wxForward = JSON.parseObject(str, WxForward.class);
            int i = wxForwardService.updateForWard(wxForward);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", "修改成功" + i + "条记录");
            logger.info("修改成功" + i + "条记录");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return callback + "(" +JSON.toJSONString(map) + ")";
    }

    /**
     * 查询单个转发人
     * @param
     * @return
     */
    @RequestMapping(value = "queryforward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getForward(String tel, String callback){

        WxForward wxForward = wxForwardService.selectForward(tel);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", wxForward);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxForward.class,
                "id", "name", "openid", "tel");
        String wxForwardJson = JSON.toJSONString(map, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
        return callback + "(" + wxForwardJson + ")";

    }

    /**
     * 查询转发人列表
     */
    @RequestMapping(value = "queryforwardlist", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getForward(String callback){

        List<WxForward> list = wxForwardService.queryforwardlist();
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", list);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxForward.class,
                "id", "name", "openid", "tel");

        String wxForwardJson = JSON.toJSONString(map, filter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);

        return callback + "(" + wxForwardJson + ")";

    }
}
