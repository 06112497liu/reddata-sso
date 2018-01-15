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
import com.bbd.bdsso.common.service.facade.result.BdssoRoleResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserRoleResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;
import com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO;

/**
 * 用户角色服务接口
 * 
 * @author byron
 * @version $Id: SsoUserRoleFacade.java, v 0.1 Sep 26, 2017 9:40:43 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/user_role")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoUserRoleFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoUserRoleVO 实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoUserRoleVO ssoUserRoleVO);

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
     * @param ssoUserRoleVO 实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoUserRoleVO ssoUserRoleVO);

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
    public BdssoUserRoleResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

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
    public SsoExListVO<SsoRoleVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                    @FormParam("key") String key);

    /**
     * 通过userId来查询全部的角色列表
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param userId        查询的用户id
     * @param roleId        查询的用户角色id
     * @return
     */
    @POST
    @Path("/queryByUserIdAndRoleId")
    public BdssoUserRoleResult queryByUserIdAndRoleId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("userId") String userId, @FormParam("roleId") String roleId);

    /**
     * 通过userId来查询全部的角色列表 
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param userId        查询的用户id
     * @return
     */
    @POST
    @Path("/queryByUserId")
    public BdssoRoleResult queryByUserId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("userId") String userId);

    /**
     * 通过userId和roleId来删除一条数据
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param userId        用户id
     * @param roleId        角色id
     * @return
     */
    @POST
    @Path("/deleteByParams")
    public BdssoBaseResult deleteByParams(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("userId") String userId, @FormParam("roleId") String roleId);

    /**
     * 通过userId来删除
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param userId        指定的用户id
     * @return
     */
    @POST
    @Path("/deleteByUserId")
    public BdssoBaseResult deleteByUserId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("userId") String userId);
}
