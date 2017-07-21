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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        /**
         * 获取openId
         */
        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
        String appSecret = wxOfficialaccounts.getAppsecret();
        String requestUrl = getopenId_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", "");
        String openId = jsonObject.get("openid").toString();

        //查询是否存在用户
        WxUser User = wxUserService.selectCountByOpenId(openId);
        String telphone="";

        if(null!=User){
            telphone = User.getTel();
            if(!telphone.equals(tel)){
                WxUser wxUser = new WxUser();
                wxUser.setAppid(appId);
                wxUser.setTel(telphone+","+tel);
                wxUser.setOpenId(openId);
                int b = wxUserService.updateTel(wxUser);
                map.put("status", Constants.RETURN_STATUS_SUCCESS);
                map.put("data", "openId为" + wxUser.getOpenId() + "的用户，使用新手机号" + tel + "进行登录");
            }else{
                map.put("status", Constants.RETURN_STATUS_SUCCESS);
                map.put("data", "该手机号已注册");
            }
        } else if(null==User){
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
            map.put("data", "成功保存" + i + "条数据");
        }
        return callback + "(" +JSON.toJSONString(map) + ")";
    }

    /**
     * 根据openId查询用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserInfo(String callback){
        WxUser User = wxUserService.selectCountByOpenId("123");
        map.put("status", Constants.RETURN_STATUS_SUCCESS);
        map.put("data", User);
        return callback + "(" +JSON.toJSONString(map) + ")";
    }

    /**
     * 更新用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateUserInfo(String params, String callback){
        WxUser user = JSON.parseObject(params, WxUser.class);
        try {
            int i = wxUserService.updataUserInfo(user);
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
    public String upPic(MultipartFile file, String callback){

        //判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File files = new File(picPath);
                if(!files.exists()){
                    files.mkdirs();
                }
                //文件保存路径
                String filePath = picPath+"/"+file.getOriginalFilename();
                map.put("filePath",tomcatImgPath + file.getOriginalFilename());
                //转存文件
                file.transferTo(new File(filePath));
                map.put("state","保存成功");
            } catch (Exception e) {
                map.put("state","保存失败");
                e.printStackTrace();
            }
        }

        return callback + "(" +JSON.toJSONString(map) + ")";
    }


    /**
     * 发送短信验证码
     * @param tel
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String sendCode(String tel, String callback) throws UnsupportedEncodingException {
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
