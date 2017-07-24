package com.jfsoft.service;

import com.jfsoft.model.WxForward;
import com.jfsoft.model.WxUser;

import java.util.List;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/19  15:38
 * @Description TODO(一句话描述类作用)
 */
public interface IWxForwardService {
    int insert(WxForward wxForward);

    int delForward(String tel);

    int updateForWard(WxForward wxForward);

    WxForward selectForward(String tel);

    List<WxForward> queryforwardlist();
}
