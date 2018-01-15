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

import com.bbd.bdsso.common.service.facade.result.BdssoBaseAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoResourceResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoResourceVO;

/**
 * 用户资源管理接口
 * 
 * @author byron
 * @version $Id: SsoResourceFacade.java, v 0.1 Sep 26, 2017 9:40:00 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/resource")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoResourceFacade {

    /**
     * 新增一条记录
     * 
     * @param uid               用户id
     * @param token             用户访问token
     * @param ssoResourceVO     实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoResourceVO ssoResourceVO);

    /**
     * 删除一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param id            资源id
     * @return
     */
    @POST
    @Path("/delete")
    public BdssoBaseResult delete(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 更新一条记录
     * 
     * @param uid               用户id
     * @param token             用户访问token
     * @param ssoResourceVO     实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoResourceVO ssoResourceVO);

    /**
     * 根据资源id来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param id            资源id
     * @return
     */
    @POST
    @Path("/queryById")
    public BdssoResourceResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 根据资源名来查询
     * 
     * @param uid               用户id
     * @param token             用户访问token
     * @param resourceName      资源名
     * @return
     */
    @POST
    @Path("/queryByResourceName")
    public BdssoBaseResult queryByResourceName(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("resourceName") String resourceName);

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
    public BdssoForPageResult<SsoResourceVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize,
                                                               @FormParam("pageNum") int pageNum, @FormParam("key") String key);

    /**
     * 资源认证，资源列表认证
     * 
     * @param ticket        用户ticket
     * @param appName       应用名
     * @return
     */
    @POST
    @Path("/queryByTicketAndAppName")
    public BdssoBaseAuthResult authByParams(@HeaderParam("ticket") String ticket, @FormParam("appName") String appName);
}
