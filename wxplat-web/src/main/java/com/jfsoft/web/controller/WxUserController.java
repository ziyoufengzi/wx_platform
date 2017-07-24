package com.jfsoft.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jfsoft.model.WxForward;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxUser;
import com.jfsoft.service.IWxOfficialaccountsService;
import com.jfsoft.service.IWxUserService;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.HttpUtil;
import com.jfsoft.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    private static Logger logger = LoggerFactory.getLogger(WxUserController.class);

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

    @Value("${pic.path}")
    private String picPath;

    @Value("${tomcat.img.path}")
    private String tomcatImgPath;



    /**
     * 注册登录接口
     * @param appId
     * @param tel
     * @param code
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(String appId, String tel, String code, String callback, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        /**
         * 获取openId
         */
        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
        String appSecret = wxOfficialaccounts.getAppsecret();
        String requestUrl = getopenId_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", "");
        String openId = jsonObject.get("openid").toString();

        //查询是否存在用户
        WxUser User = null;
        try {
            User = wxUserService.selectCountByOpenId(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String telphone="";

        if(null!=User){
            telphone = User.getTel();
            if(!telphone.equals(tel)){

                try {
                    WxUser wxUser = new WxUser();
                    wxUser.setAppid(appId);
                    wxUser.setTel(telphone+","+tel);
                    wxUser.setOpenId(openId);
                    int b = wxUserService.updateTel(wxUser);
                    map.put("status", Constants.RETURN_STATUS_SUCCESS);
                    map.put("data", "openId为" + wxUser.getOpenId() + "的用户，使用新手机号" + tel + "进行登录");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                map.put("status", Constants.RETURN_STATUS_SUCCESS);
                map.put("data", "该手机号已注册");
            }
        } else if(null==User){
            /**
             * 存储用户信息(登录时)
             * appId,openId,tel
             */
            int i = 0;
            try {
                WxUser wxUser = new WxUser();
                wxUser.setAppid(appId);
                wxUser.setTel(tel);
                wxUser.setOpenId(openId);
                i = wxUserService.insert(wxUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", "成功保存" + i + "条数据");
        }
        return callback + "(" +JSON.toJSONString(map) + ")";
    }

    /**
     * 根据tel查询用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserInfo(String tel, String callback){
        Map<String, Object> map = new HashMap<String, Object>();
        WxUser user = null;
        String wxUserJson = "";
        String str = "";
        try {
            user = wxUserService.getDetail(tel);
            String date = WeixinUtil.getDateSx();
            if(user.getSex().equals("男")){
                str = "先生";
            }else {
                str = "女士";
            }
            map.put("title", date + user.getName() +  str);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", user);
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(WxUser.class,
                    "name", "sex", "age", "tel", "email", "picpath");
            wxUserJson = JSON.toJSONString(map, filter,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullStringAsEmpty);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return callback + "(" +wxUserJson + ")";
    }

    /**
     * 更新用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateUserInfo(String params, String callback){

        Map<String, Object> map = new HashMap<String, Object>();

        String str = null;

        try {
            str = java.net.URLDecoder.decode(java.net.URLDecoder.decode(params, "UTF-8"),"UTF-8");
            WxUser wxUser = JSON.parseObject(str, WxUser.class);
            int i = wxUserService.updataUserInfo(wxUser);
            map.put("status", Constants.RETURN_STATUS_SUCCESS);
            map.put("data", "成功更新" + i + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callback + "(" +JSON.toJSONString(map) + ")";
    }


    /**
     * 头像上传接口
     * @param file
     * @return
     */
    @RequestMapping(value = "/upPic", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String upPic(HttpServletResponse response, @RequestPart MultipartFile file, String tel){

        Map<String, Object> map = new HashMap<String, Object>();

        //判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File files = new File(picPath);
                if(!files.exists()){
                    files.mkdirs();
                }
                //文件保存路径
                String filePath = picPath+"/"+file.getOriginalFilename();
                //转存文件
                file.transferTo(new File(filePath));
                String picpath = tomcatImgPath + file.getOriginalFilename();
                WxUser wxUser = new WxUser();
                wxUser.setTel(tel);
                wxUser.setPicpath(picpath);
                int i = wxUserService.updatePicPath(wxUser);
                map.put("picpath", picpath);
                map.put("status", Constants.RETURN_STATUS_SUCCESS);
            } catch (Exception e) {
                map.put("status", Constants.RETURN_STATUS_FAILURE);
                e.printStackTrace();
            }
        }

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min

        return JSON.toJSONString(map);
    }


    /**
     * 发送短信验证码
     * @param tel
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String sendCode(String tel, String callback) throws UnsupportedEncodingException {

        Map<String, Object> map = new HashMap<String, Object>();
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

        return callback + "(" +JSON.toJSONString(map) + ")";
    }
}
