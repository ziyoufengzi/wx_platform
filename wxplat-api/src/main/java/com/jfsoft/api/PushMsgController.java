package com.jfsoft.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.service.IWxOfficialaccountsService;
import com.jfsoft.service.IWxUserService;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.WeixinUtil;
import com.jfsoft.vo.TemplateData;
import com.jfsoft.vo.WxTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxAccessToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/17  10:58
 * @Description TODO(外部接口类)
 */
@RestController
@RequestMapping("/push")
@Api(value = "/push", description = "外部接口类")
public class PushMsgController {

    private static Logger logger = Logger.getLogger(PushMsgController.class);

    private static Map<String, Object> map = new HashMap<String, Object>();

    public final static String sendTemplate_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public final static String getAllTemplate_url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

    @Autowired
    private IWxOfficialaccountsService wxOfficialaccountsService;

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 推送模板消息
     * @param
     * @param
     */
//    @RequestMapping(value = "", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    @ApiOperation(value="发送模板消息", notes="根据传入模板参数发送模板消息")
//    public String sendTemplateMessage(){
//
//        WxOfficialaccounts wxOfficialaccounts = wxOfficialaccountsService.selectAppSecretByAppId(appId);
//        String appSecret = wxOfficialaccounts.getAppsecret();
//        //获取accessToken | 有效期2小时 | 每日获取次数上限2000次
//        WxAccessToken accessToken = WeixinUtil.getAccessToken(appId, appSecret);
//        //获取模版列表
//        String requestUrl = sendTemplate_url.replace("ACCESS_TOKEN", accessToken.getAccessToken());
//
//        WxTemplate wxTemplate = new WxTemplate();
//        wxTemplate.setTemplate_id("VDmSUjWnGjP_UTuMV3vYTzmPBCQvJEoQYy6GO2WnCUo");
//        wxTemplate.setTouser(openId);   //要推送用户的openId
//        wxTemplate.setUrl("http://music.163.com/#/song?id=27867140");   //报告单展示页面
//        //组装模板信息
//        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
//        TemplateData first = new TemplateData();
//        first.setColor("#000000");
//        first.setValue(name + "您好,您的最近一期健康报告已生成，详情如下:");
//        m.put("first", first);
//
//        TemplateData keyword1 = new TemplateData();
//        keyword1.setColor("#000000");
//        keyword1.setValue(date);
//        m.put("keyword1", keyword1);
//
//        TemplateData keyword2 = new TemplateData();
//
//        keyword2.setColor("#000000");
//        keyword2.setValue();    //LIS显示项目名称，PEIS侧显示体检报告单
//        m.put("keyword2", keyword2);
//
//
//        TemplateData remark = new TemplateData();
//        remark.setColor("#000000");
//        remark.setValue("感谢您的支持，如需查看报告详细信息请点击");
//        m.put("remark", remark);
//
//        wxTemplate.setData(m);
//        //将模板信息转为json
//        String jsonString = JSONObject.toJSONString(wxTemplate);
//        //发送
//        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", jsonString);
//        int result = 0;
//        if (null != jsonObject) {
//            if (0 != jsonObject.getInteger("errcode")) {
//                result = jsonObject.getInteger("errcode");
//                logger.error("发送失败"+result);
//                map.put("status", Constants.RETURN_STATUS_FAILURE);
//                map.put("data", "发送失败" + result);
//            }
//        }
//        logger.info("模板消息发送结果："+result);
//        map.put("status", Constants.RETURN_STATUS_SUCCESS);
//        map.put("data", "发送成功"+result);
//
//        return JSON.toJSONString(map);
//
//    }

}
