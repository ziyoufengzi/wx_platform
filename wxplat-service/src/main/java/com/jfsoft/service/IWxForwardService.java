package com.jfsoft.service;

import com.jfsoft.model.WxForward;
import com.jfsoft.model.WxUser;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/19  15:38
 * @Description TODO(一句话描述类作用)
 */
public interface IWxForwardService {
    int insert(WxForward wxForward);

    int delForward(String openId);

    int updateForWard(WxForward wxForward);
}
