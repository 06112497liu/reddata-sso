/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoVisitHistoryVO;

/**
 * 访问历史记录服务接口
 * 
 * @author byron
 * @version $Id: SsoVisitHistoryFacade.java, v 0.1 Sep 26, 2017 9:43:03 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/visit_history")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoVisitHistoryFacade {

    /**
     * 新增一条记录
     * 
     * @param ticket        用户票据
     * @param ip            用户ip
     * @param appName       用户访问的应用名
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("ticket") String ticket, @FormParam("ip") String ip, @FormParam("appName") String appName);

    /**
     * 根据关键字模糊查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param pageSize      每页显示的数目
     * @param pageNum       当前页码
     * @param key           查询关键字
     * @return
     */
    @POST
    @Path("/fuzzyQueryForPage")
    public BdssoForPageResult<SsoVisitHistoryVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize,
                                                                   @FormParam("pageNum") int pageNum, @FormParam("key") String key);
}
