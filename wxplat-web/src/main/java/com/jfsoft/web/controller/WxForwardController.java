package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfsoft.model.WxForward;
import com.jfsoft.service.IWxForwardService;
import com.jfsoft.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/17  13:46
 * @Description TODO(转发人控制器)
 */
@RestController
@RequestMapping("/forward/*")
public class WxForwardController {

    @Autowired
    private IWxForwardService wxForwardService;

    private static Logger logger = Logger.getLogger(WxForwardController.class);

    private static Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 增加转发人
     * @param wxForward
     * @return
     */
    @RequestMapping(value = "forward", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addForward(@RequestParam WxForward wxForward){

        int i = wxForwardService.insert(wxForward);

        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", i+"条转发人存储成功");

        return JSON.toJSONString(map);
    }

    /**
     * 删除转发人
     * @param openId
     * @return
     */
    @RequestMapping(value = "forward", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String delForward(@PathParam("id") String openId){

        int i = wxForwardService.delForward(openId);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", "删除成功");
        return JSON.toJSONString(map);
    }

    /**
     * 修改转发人
     * @param wxForward
     * @return
     */
    @RequestMapping(value = "forward", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateForward(@RequestParam WxForward wxForward){
        wxForward.setName(wxForward.getName());
        wxForward.setTel(wxForward.getTel());
        int i = wxForwardService.updateForWard(wxForward);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", "删除成功");
        return JSON.toJSONString(map);
    }
//
//    /**
//     * 查询单个转发人
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "forward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public String getForward(@RequestParam String openId){
//
//        return "";
//    }
//
//    /**
//     * 查询转发人列表
//     */
//    @RequestMapping(value = "forward", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public String getForward(){
//
//        return "";
//    }
}
