package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfsoft.utils.WeixinUtil;

import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/13  13:12
 * @Description TODO(微信业务主入口)
 */
@RestController
@RequestMapping("/wx")
@Api(value = "/wx", description = "入口类")
public class loginController {

    /**
     * 查询报告单
     * @param code
     * @return
     */
    @GetMapping("/index")
    public HttpEntity getReport(@RequestParam String code, @RequestParam String appId){
        String appSecret = "";
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.httpRequest(getOpenIdUrl, "POST", "");
        String openId = jsonObject.getJSONObject("openid").toJSONString();
        //查询历史报告单
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 登录接口
     * @param appId
     * @param tel
     * @param code
     * @return
     */
    @GetMapping("/login")
    public  HttpEntity login(@RequestParam String appId, @RequestParam String tel, @RequestParam String code){
        /**
         * 获取openId
         */
        String appSecret = "";
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.httpRequest(getOpenIdUrl, "POST", "");
        //调用存储service，存入tel,
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
