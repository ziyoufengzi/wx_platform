package com.jfsoft.service.impl;

import com.jfsoft.mapper.WxForwardMapper;
import com.jfsoft.model.WxForward;
import com.jfsoft.service.IWxForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/19  15:40
 * @Description TODO(一句话描述类作用)
 */
@Service
public class WxForwardServiceImpl implements IWxForwardService{

    @Autowired
    private WxForwardMapper wxForwardMapper;

    @Override
    public int insert(WxForward wxForward) {
        return wxForwardMapper.insert(wxForward);
    }

    @Override
    public int delForward(String openId){
        return wxForwardMapper.delForward(openId);
    }

    @Override
    public int updateForWard(WxForward wxForward) {
        return wxForwardMapper.updateForWard(wxForward);
    }

    @Override
    public WxForward selectForward(String tel) {
        return wxForwardMapper.selectForward(tel);
    }

    @Override
    public List<WxForward> queryforwardlist() {
        return wxForwardMapper.queryforwardlist();
    }
}
