/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import com.bbd.bdsso.common.service.facade.vo.SsoUserVO;

import java.util.List;

/**
 * bdsso用户信息结果
 * 
 * @author weizhang
 * @version $Id: BdssoUserInfoResult.java, v 0.1 2017年11月13日 下午2:58:40 weizhang@bbdservice.com Exp $
 */
public class BdssoUserInfosResult extends BdssoBaseResult {

    private static final long serialVersionUID = -5458306590794779152L;

    List<SsoUserVO> data;

    public List<SsoUserVO> getData() {
        return data;
    }

    public void setData(List<SsoUserVO> data) {
        this.data = data;
    }
}
