package com.jfsoft.report.service.impl;

import com.jfsoft.mapper.WxReportcardMapper;
import com.jfsoft.model.WxOfficialaccounts;
import com.jfsoft.model.WxReportcard;
import com.jfsoft.report.service.IWxReportcardService;
import com.jfsoft.utils.Constants;
import com.jfsoft.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * wanggang
 * 2017/7/19
 */
@Service
public class WxReportcardServiceImpl implements IWxReportcardService {

    @Value("${image.server.path}")
    private String imageServerPath;

    @Autowired
    private WxReportcardMapper wxReportcardMapper;
    
    public List<WxReportcard> findReportList(String pageNum, String pageSize) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        params.put("pageStart", (pageNumInt - 1)*pageSizeInt);
        params.put("pageSize", pageSizeInt);
        params.put("delFlag", Constants.IS_DELETE_FALSE);

        List<WxReportcard> wxReportcardList = wxReportcardMapper.findPage(params);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (WxReportcard wx : wxReportcardList) {

            //获取第一张图片，做为列表中显示的缩略图
            String imagePath = wx.getReportImgurl();
            //图片数量
            String countStr = imagePath.substring(imagePath.lastIndexOf("/") + 1);
            if(!StringUtils.isBlank(countStr)) {
                int imageCount = Integer.parseInt(countStr);
                //图片名称前缀
                String imageName_prefix = imagePath.substring(imagePath.indexOf("/img/") + 5, imagePath.lastIndexOf("/"));

                if (imageCount > 0) {
                    wx.setReportImgurl(imageServerPath + imagePath.substring(0, imagePath.lastIndexOf("/")) + "/" + imageName_prefix + "_1.jpg");
                }
            } else {
                wx.setReportImgurl("");
            }

            Date reportTime = wx.getReportTime();
            if (null != reportTime) {
                String reportTimeStr = sdf.format(reportTime);
                wx.setReportTimeStr(reportTimeStr);
            }
        }
        
        return wxReportcardList;
    }

    public WxReportcard findOne(String id) throws Exception {

        WxReportcard reportcard = wxReportcardMapper.selectByPrimaryKey(Integer.parseInt(id));

        List<String> imgUrlList = new ArrayList<String>();

        String imagePath = reportcard.getReportImgurl();
        //图片数量
        String countStr = imagePath.substring(imagePath.lastIndexOf("/") + 1);
        if(!StringUtils.isBlank(countStr)) {
            int imageCount = Integer.parseInt(countStr);
            //图片名称前缀
            String imageName_prefix = imagePath.substring(imagePath.indexOf("/img/") + 5, imagePath.lastIndexOf("/"));

            if (imageCount > 0) {
                for (int i=0;i<imageCount;i++) {
                    imgUrlList.add(imageServerPath + imagePath.substring(0, imagePath.lastIndexOf("/")) + "/" + imageName_prefix + "_" + (i+1) + ".jpg");
                }
                reportcard.setReportImgurlList(imgUrlList);
            }
        }

        //reportcard.setReportImgurl(imageServerPath + reportcard.getReportImgurl());
        Date reportTime = reportcard.getReportTime();
        if(null!=reportTime) {
            reportcard.setReportTimeStr(DateUtil.format(reportTime));
        }

        return reportcard;
    }

    public int save(WxReportcard wxReportcard) throws Exception {

        return wxReportcardMapper.insert(wxReportcard);
    }

    public int findPageCount(String pageNum, String pageSize) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        params.put("pageStart", (pageNumInt - 1)*pageSizeInt);
        params.put("pageSize", pageSizeInt);
        params.put("delFlag", Constants.IS_DELETE_FALSE);

        return wxReportcardMapper.findPageCount(params);
    }
    
}
