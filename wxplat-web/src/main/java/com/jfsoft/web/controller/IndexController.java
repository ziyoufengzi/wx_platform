package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxUser;
import com.jfsoft.service.IWxOfficialaccountsService;
import com.jfsoft.service.IWxUserService;
import com.jfsoft.utils.WeixinUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IndexController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    private IWxOfficialaccountsService wxOfficialaccountsService;

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 查询报告单
     * chenxiaochun
     * @param code
     * @return
     */
    @GetMapping("/index")
    public void getReport(@RequestParam String code, @RequestParam String appId){
        String appSecret = "";
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.httpRequest(getOpenIdUrl, "POST", "");
        String openId = jsonObject.getJSONObject("openid").toJSONString();
        //查询历史报告单
        //返回报告单路径
    }

    /**
     * 注册登录接口
     * @param appId
     * @param tel
     * @param code
     * @return
     */
    @GetMapping("/login")
    public void login(@RequestParam String appId, @RequestParam String tel, @RequestParam String code){
        /**
         * 获取openId
         */
        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
        String appSecret = wxOfficialaccounts.getAppsecret();
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+
                "&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.httpRequest(getOpenIdUrl, "POST", "");
        String openId = jsonObject.getJSONObject("openid").toJSONString();
        /**
         * 存储用户信息
         * appId,name,sex,age,cid.email,tel,
         */
        WxUser wxUser = new WxUser();
        wxUser.setAppid(appId);
        wxUser.setTel(tel);
        wxUser.setOpenId(openId);
        int i = wxUserService.insert(wxUser);
    }
}
