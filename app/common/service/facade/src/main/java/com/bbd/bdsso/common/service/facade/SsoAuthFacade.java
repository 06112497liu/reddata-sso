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

import com.bbd.bdsso.common.service.facade.result.BdssoAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthAddParamVO;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExAuthVO;

/**
 * 用户权限服务接口
 * 
 * @author byron
 * @version $Id: SsoAuthFacade.java, v 0.1 Sep 26, 2017 9:40:00 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/auth")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoAuthFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoAuthVO     实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAuthVO ssoAuthVO);

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
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoAuthVO     实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAuthVO ssoAuthVO);

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
    public BdssoAuthResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 根据权限码来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authCode      权限码
     * @return
     */
    @POST
    @Path("/queryByAuthCode")
    public BdssoBaseResult queryByAuthCode(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authCode") String authCode);

    /**
     * 根据权限名称来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authName      权限名称
     * @return
     */
    @POST
    @Path("/queryByAuthName")
    public BdssoBaseResult queryByAuthName(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authName") String authName);

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
    public BdssoForPageResult<SsoAuthAddParamVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize,
                                                                   @FormParam("pageNum") int pageNum, @FormParam("key") String key);

    /**
     * 权限认证，权限列表认证
     * 
     * @param ticket        用户ticket
     * @param authCode      权限编码
     * @param appName       应用名
     * @return
     */
    @POST
    @Path("/authCodeByTicket")
    public BdssoBaseAuthResult authByParams(@HeaderParam("ticket") String ticket, @FormParam("authCode") String authCode, @FormParam("appName") String appName);

    /**
     * 权限认证，权限列表认证
     * 
     * @param ticket        用户ticket
     * @param ssoExAuthVO   单点登陆权限扩展VO
     * @return
     */
    @POST
    @Path("/authCodeListByTicket")
    public BdssoBaseAuthResult authByParams(@HeaderParam("ticket") String ticket, SsoExAuthVO ssoExAuthVO);

    /**
     * 权限认证，权限列表认证
     * 
     * @param ticket        用户ticket
     * @param appName       应用名
     * @return
     */
    @POST
    @Path("/authByTicketAndAppName")
    public BdssoBaseAuthResult authByParams(@HeaderParam("ticket") String ticket, @FormParam("appName") String appName);

}
