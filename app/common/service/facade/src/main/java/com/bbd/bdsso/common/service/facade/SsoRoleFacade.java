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
import com.bbd.bdsso.common.service.facade.result.BdssoRoleResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;

/**
 * 角色服务接口
 * 
 * @author byron
 * @version $Id: SsoRoleFacade.java, v 0.1 Sep 26, 2017 9:39:50 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/role")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoRoleFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoRoleVO     实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoRoleVO ssoRoleVO);

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
     * @param ssoRoleVO     实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoRoleVO ssoRoleVO);

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
    public BdssoRoleResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 根据角色名称来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param roleName      角色名称
     * @return
     */
    @POST
    @Path("/queryByRoleName")
    public BdssoBaseResult queryByName(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("roleName") String roleName);

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
    public BdssoForPageResult<SsoRoleVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                           @FormParam("key") String key);
}
