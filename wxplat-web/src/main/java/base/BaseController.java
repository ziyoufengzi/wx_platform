package base;

import com.jfsoft.utils.Constants;

import javax.servlet.http.HttpSession;

/**
 * Controller基类
 * wanggang
 * 2017/7/18
 */
public class BaseController {

    /**
     * 保存code到session
     */
    protected void saveUserCodeToSession(HttpSession session, String userId) {

        session.setAttribute(Constants.SESSION_USER_KEY, userId);
    }

    /**
     * 获取code
     */
    protected String getUserCodeFromSession(HttpSession session) {
        String userCode = String.valueOf(session.getAttribute(Constants.SESSION_USER_KEY));
        return userCode;
    }
    /**
     * 保存登录的name到session
     */
    protected void saveUserNameToSession(HttpSession session, String name) {

        session.setAttribute(Constants.SESSION_USER_NAME, name);
    }
    /**
     * 获取name
     * @return
     */
    protected String getUserNameFromSession(HttpSession session){
        String name = String.valueOf(session.getAttribute(Constants.SESSION_USER_NAME));
        return name;
    }

}
