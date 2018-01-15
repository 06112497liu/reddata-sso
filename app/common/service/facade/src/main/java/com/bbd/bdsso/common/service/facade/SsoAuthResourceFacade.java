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

import com.bbd.bdsso.common.service.facade.result.BdssoAuthResourceResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoResourceResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoResourceVO;

/**
 * 用户角色权限服务接口
 * 
 * @author byron
 * @version $Id: SsoAuthResourceFacade.java, v 0.1 Sep 26, 2017 9:40:54 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/auth_resource")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoAuthResourceFacade {

    /**
     * 新增一条记录
     * 
     * @param uid                   用户id
     * @param token                 用户访问token
     * @param ssoAuthResourceVO     实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAuthResourceVO ssoAuthResourceVO);

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
     * @param uid                   用户id
     * @param token                 用户访问token
     * @param ssoAuthResourceVO     实体
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoAuthResourceVO ssoAuthResourceVO);

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
    public BdssoAuthResourceResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

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
    public SsoExListVO<SsoResourceVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                        @FormParam("key") String key);

    /**
     * 通过authId来查询列表
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authId        查询的权限id
     * @return
     */
    @POST
    @Path("/queryByAuthId")
    public BdssoResourceResult queryByRoleId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authId") String authId);

    /**
     * 通过authId和resourceId来查询权限
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authId        查询的权限id
     * @param resourceId    查询的资源id
     * @return
     */
    @POST
    @Path("/queryByAuthIdAndResourceId")
    public BdssoAuthResourceResult queryByRoleIdAndAuthId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authId") String authId,
                                                          @FormParam("resourceId") String resourceId);

    /**
     * 通过authId和resourceId来删除一条数据
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authId        权限id
     * @param resourceId    资源id
     * @return
     */
    @POST
    @Path("/deleteByParams")
    public BdssoBaseResult deleteByParams(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authId") String authId, @FormParam("resourceId") String resourceId);

    /**
     * 通过authId来删除
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param authId        指定的权限id
     * @return
     */
    @POST
    @Path("/deleteByAuthId")
    public BdssoBaseResult deleteByAuthId(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("authId") String authId);
}
