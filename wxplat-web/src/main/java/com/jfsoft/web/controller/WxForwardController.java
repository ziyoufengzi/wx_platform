package com.jfsoft.web.controller;

import org.apache.log4j.Logger;
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

//    private static Logger logger = Logger.getLogger(WxForwardController.class);
//
//    private static Map<String, Object> map = new HashMap<String, Object>();
//
//    /**
//     * 增加转发人
//     * @param forward
//     * @return
//     */
////    @RequestMapping(value = "forward", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
////    @ResponseBody
////    public String addForward(@RequestParam Forward forward){
////
////        return "";
////    }
//
//    /**
//     * 删除转发人
//     * @param openId
//     * @return
//     */
//    @RequestMapping(value = "forward", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public String addForward(@PathParam("id") String openId){
//
//        return "";
//    }
//
//    /**
//     * 修改转发人
//     * @param forward
//     * @return
//     */
////    @RequestMapping(value = "forward", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
////    @ResponseBody
////    public String addForward(@RequestParam Forward forward){
////
////        return "";
////    }
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
