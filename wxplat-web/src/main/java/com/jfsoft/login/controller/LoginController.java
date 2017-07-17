package com.jfsoft.login.controller;

import com.jfsoft.utils.Constants;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

/**
 * 登录
 * wanggang
 * 2017-7-13 14:56:52
 */
@Controller
@RequestMapping(value = "/login")
@Api(value = "/login", description = "登录，验证用户权限")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 跳转到登录页
     * wanggang
     * 2017-7-14 09:18:05
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String toLogin() {

        return "loginSystem";
    }

    /**
     * 登录（创建新的会话）
     * wanggang
     * 2017-7-13 15:14:16
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String signIn(Map<String,Object> map, String username, String password) {

        map.put("username", username);

        return "index";
    }

    /**
     * 退出（销毁会话）
     * wanggang
     * 2017-7-13 15:27:56
     */
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }

        session.invalidate();

        return "loginSystem";
    }

}
