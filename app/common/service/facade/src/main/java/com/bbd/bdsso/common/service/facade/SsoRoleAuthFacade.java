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
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoRoleAuthResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO;

/**
 * 用户角色权限服务接口
 * 
 * @author byron
 * @version $Id: SsoRoleAuthFacade.java, v 0.1 Sep 26, 2017 9:40:54 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/role_auth")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoRoleAuthFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoRoleAuthVO 实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoRoleAuthVO ssoRoleAuthVO);

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
     * @param ssoRoleAuthVO 实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoRoleAuthVO ssoRoleAuthVO);

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
    public BdssoRoleAuthResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

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
    public SsoExListVO<SsoAuthVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                    @FormParam("key") String key);

    /**
     * 通过roleId来查询权限列表
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param roleId        查询的角色id
     * @return
     */
    @POST
    @Path("/queryByRoleId")
    public BdssoAuthResult queryByRoleId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("roleId") String roleId);

    /**
     * 通过roleId和authId来查询权限
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param roleId        查询的角色id
     * @param authId        查询的权限id
     * @return
     */
    @POST
    @Path("/queryByRoleIdAndAuthId")
    public BdssoRoleAuthResult queryByRoleIdAndAuthId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("roleId") String roleId, @FormParam("authId") String authId);

    /**
     * 通过roleId和authId来删除一条数据
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param roleId        角色id
     * @param authId        权限id
     * @return
     */
    @POST
    @Path("/deleteByParams")
    public BdssoBaseResult deleteByParams(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("roleId") String roleId, @FormParam("authId") String authId);

    /**
     * 通过roleId来删除
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param roleId        指定的角色id
     * @return
     */
    @POST
    @Path("/deleteByRoleId")
    public BdssoBaseResult deleteByRoleId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("roleId") String roleId);
}
