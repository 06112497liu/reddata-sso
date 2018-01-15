/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.bbd.bdsso.common.service.facade.result.*;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoUserVO;

import java.util.List;

/**
 * 用户服务接口
 * 
 * @author byron
 * @version $Id: SsoUserFacade.java, v 0.1 Sep 13, 2017 3:19:26 PM byron Exp $
 */
@Path("/api/v1.0/bdsso/user")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoUserFacade {

    /**
     * 新增一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param ssoUserVO     实体
     * @return
     */
    @POST
    @Path("/add")
    public BdssoBaseResult add(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoUserVO ssoUserVO);

    /**
     * 用户登陆
     * 
     * @param userName      用户名
     * @param password      密码
     * @return uid, token   用户id和有效token
     */
    @POST
    @Path("/login")
    public BdssoUserResult login(@FormParam("userName") String userName, @FormParam("password") String password);

    /**
     * 删除用户
     * 
     * @param uid       用户id
     * @param token     用户使用token
     * @param id        资源id
     * @return
     */
    @POST
    @Path("/delete")
    public BdssoBaseResult delete(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 注销
     * 
     * @param uid    用户id
     * @param token     用户token
     * @return
     */
    @POST
    @Path("/logout")
    public BdssoBaseResult logout(@HeaderParam("uid") String uid, @HeaderParam("token") String token);

    /**
     * 校验用户名是否存在
     * 
     * @param userName
     * @return
     */
    @POST
    @Path("/checkUserName")
    public BdssoBaseResult checkUserName(@FormParam("userName") String userName);

    /**
     * 校验邮箱是否存在
     * 
     * @param email
     * @return
     */
    @POST
    @Path("/checkEmail")
    public BdssoBaseResult checkEmail(@FormParam("email") String email);

    /**
     * 发送验证码
     * 
     * @param email         用户邮箱
     * @return
     */
    @POST
    @Path("/sendVerifyCode")
    public BdssoBaseResult sendVerifyCode(@FormParam("email") String email);

    /**
     * 用户注册
     * 
     * @param userName      用户名
     * @param email         邮箱
     * @param password      密码
     * @param verifyCode    验证码
     * @return uid, token   用户id和有效token
     */
    @POST
    @Path("/regist")
    public BdssoUserResult regist(@FormParam("userName") String userName, @FormParam("email") String email, @FormParam("password") String password, @FormParam("verifyCode") String verifyCode);

    /**
     * 忘记密码
     * 
     * @param email         用户邮箱
     */
    @POST
    @Path("/forgetPsw")
    public BdssoBaseResult forgetPsw(@FormParam("email") String email);

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
    public BdssoForPageResult<SsoUserVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize, @FormParam("pageNum") int pageNum,
                                                           @FormParam("key") String key);

    /**
     * 根据区域查询用户，如果区域为null则查询全部
     * @return
     */
    @POST
    @Path("/findUserByRegion")
    public BdssoUserInfosResult findUserByRegion(@FormParam("region") String region);
    /**
     * 更新密码
     * 
     * @param uid           用户id
     * @param token         用户使用token
     * @param oldPsw        旧密码
     * @param newPsw        新密码
     * @return
     */
    @POST
    @Path("/resetPsw")
    public BdssoBaseResult resetPsw(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("oldPsw") String oldPsw, @FormParam("newPsw") String newPsw);

    /**
     * 更新信息
     * 
     * @param uid           用户id
     * @param token         用户使用token
     * @param ssoUserVO     单点登陆用户VO
     * @return
     */
    @POST
    @Path("/update")
    public BdssoBaseResult update(@HeaderParam("uid") String uid, @HeaderParam("token") String token, SsoUserVO ssoUserVO);

    /**
     * 根据ticket查询用户信息
     * 
     * @param ticket       验证标识
     * @return
     */
    @GET
    @Path("/queryByTicket")
    public BdssoUserInfoResult queryByTicket(@QueryParam("ticket") String ticket);

    /**
     * 使用用户凭据登出，主要用于接入SSO的系统调用
     * @param ticket 用户凭据
     * @return
     */
    @POST
    @Path("/logoutByTicket")
    BdssoBaseResult logoutByTicket(@HeaderParam("ticket") String ticket);

    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    @POST
    @Path("/queryByUserId")
    BdssoUserInfoResult queryByUserId(Integer userId);
}
