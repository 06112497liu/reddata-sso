/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.bbd.bdsso.common.service.facade.result.BdssoAppResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoTicketResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUploadResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoAppVO;
import com.bbd.bdsso.common.service.facade.vo.SsoFileVO;

/**
 * 应用管理服务接口
 * 
 * @author byron
 * @version $Id: SsoAppFacade.java, v 0.1 Sep 26, 2017 9:39:01 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/app")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoAppFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoAccessVO   实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAppVO ssoAppVO);

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
     * @param ssoAppVO      实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAppVO ssoAppVO);

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
    public BdssoAppResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 根据应用名来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param appName       应用名
     * @return
     */
    @POST
    @Path("/queryByName")
    public BdssoBaseResult queryByName(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("appName") String appName);

    /**
     * 根据应用名来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param appName       应用名
     * @return
     */
    @POST
    @Path("/queryByNameWithoutAuth")
    public BdssoAppResult queryByNameWithoutAuth(@FormParam("appName") String appName);

    /**
     * 验证ticket，没有权限限制
     * 
     * @param ticket       验证标识
     * @return
     */
    @POST
    @Path("/verifyTicket")
    public BdssoBaseResult verifyTicket(@FormParam("ticket") String ticket);

    /**
     * 解析ticket
     * 
     * @param ticket
     * @return
     */
    @POST
    @Path("/parseTicket")
    public BdssoUserResult parseTicket(@FormParam("ticket") String ticket);

    /**
     * 创建ticket标识，没有权限限制
     * 
     * @param uid           用户id
     * @param token         用户使用token
     * @return
     */
    @POST
    @Path("/buildTicket")
    public BdssoTicketResult buildTicket(@FormParam("uid") String uid, @FormParam("token") String token);

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
    public BdssoForPageResult<SsoAppVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                          @FormParam("key") String key);

    /**
     * 上传文件
     * 
     * @param uid            用户id
     * @param token          用户访问token
     * @param file           文件模型
     * @return
     */
    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public BdssoUploadResult uploadFile(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @MultipartForm SsoFileVO file);

}
