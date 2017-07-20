package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxUser;
import com.jfsoft.service.IWxOfficialaccountsService;
import com.jfsoft.service.IWxUserService;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.HttpUtil;
import com.jfsoft.utils.WeixinUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/13  13:12
 * @Description TODO(微信业务主入口)
 */
@RestController
@RequestMapping("/user")
public class WxUserController {

    public final static String getopenId_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public final static String sendCode_url = "http://dx.10659com.com:83/ApiService.asmx/Send";

    public final static String send_str = "account=ACCOUNT&pwd=PWD&product=PRODUCT&mobile=TEL&message=MESSAGE";

    private static Logger logger = Logger.getLogger(WxUserController.class);

    private Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    private IWxOfficialaccountsService wxOfficialaccountsService;

    @Autowired
    private IWxUserService wxUserService;

    @Value("${sms.account}")
    private String account;

    @Value("${sms.pwd}")
    private String pwd;

    @Value("${sms.product}")
    private String product;


    /**
     * 查询报告单(微信菜单唯一入口)
     * chenxiaochun
     * @param code
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void getReport(@RequestParam String code, @RequestParam String appId){
        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
        String appSecret = wxOfficialaccounts.getAppsecret();
        String requestUrl = getopenId_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", "");
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
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(@RequestParam String appId, @RequestParam String tel, @RequestParam String code){

        /**
         * 获取openId
         */
        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
        String appSecret = wxOfficialaccounts.getAppsecret();
        String requestUrl = getopenId_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", "");
        String openId = jsonObject.getJSONObject("openid").toJSONString();
        //查询是否存在用户
        int a = wxUserService.selectCountByOpenId(openId);
        if(0==a){
            /**
             * 存储用户信息(登录时)
             * appId,openId,tel
             */
            WxUser wxUser = new WxUser();
            wxUser.setAppid(appId);
            wxUser.setTel(tel);
            wxUser.setOpenId(openId);
            int i = wxUserService.insert(wxUser);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", "成功保存" + i);
        }else {
            WxUser wxUser = new WxUser();
            wxUser.setAppid(appId);
            wxUser.setTel(tel);
            wxUser.setOpenId(openId);
            int b = wxUserService.updateTel(wxUser);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", "成功保存" + b);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserInfo(@RequestParam String openId){
        WxUser wxUser = wxUserService.selectUserInfoByOpenId(openId);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", wxUser);
        return JSON.toJSONString(map);
    }

    /**
     * 更新用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateUserInfo(@RequestParam String params){
        return "";
    }

    /**
     * 头像上传接口
     * @param file
     * @return
     */
    @RequestMapping(value = "/upPic", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String upPic(MultipartFile file){
        return "";
    }

    /**
     * 发送短信验证码
     * @param tel
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation(value="发送短信验证码", notes="传入tel发送验证码")
    @ApiImplicitParam(name = "tel", value = "电话号码", required = true, dataType = "Long")
    public String sendCode(String tel) throws UnsupportedEncodingException {
        //生成6位验证码
        String code;
        while (true){
            code =  new Random().nextInt(999999) + "";
            if(code.length()==6) break;
        }
        String str = "您的验证码是"+code+",如非本人操作请忽略本短信【金风易通】";

        String msg = URLEncoder.encode(str,"gb2312");

        String requestMsg = send_str.replace("ACCOUNT", account).replace("PWD", pwd).replace("PRODUCT", product)
                                     .replace("TEL", tel).replace("MESSAGE", msg);

        String state = HttpUtil.sendPostUrl(sendCode_url,requestMsg,"UTF-8");

        logger.info("发送状态:"+state);

        map.put("code",code);
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data","成功发送验证码至" + tel);
        return JSON.toJSONString(map);
    }

}
