/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade;

import com.bbd.bdsso.common.service.facade.result.BdssoDataDictionaryResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;

import javax.ws.rs.*;

/**
 * 数据字典服务接口
 * 
 * @author hyjun
 * @version $Id: DataDictionaryFacade.java, v 0.1 Sep 26, 2017 9:40:11 AM hyjun Exp $
 */
@Path("/api/v1.0/bdsso/dataDictionay")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoDataDictionaryFacade {

    /**
     * 删除一条记录
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @return
     */
    @POST
    @Path("/query")
    BdssoDataDictionaryResult query(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("type") String type);


}
