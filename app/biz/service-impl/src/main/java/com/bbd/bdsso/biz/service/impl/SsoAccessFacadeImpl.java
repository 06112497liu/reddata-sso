/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAccessDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccessDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccessDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessDO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessGroupDO;
import com.bbd.bdsso.common.service.facade.SsoAccessFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAccessResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoExAccessResult;
import com.bbd.bdsso.common.service.facade.result.BdssoSummaryResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAccessVO;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.core.model.convertor.SsoAccessConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * 用户访问服务接口服务实现
 * 
 * @author byron
 * @version $Id: SsoAccessFacadeImpl.java, v 0.1 Sep 26, 2017 4:10:41 PM byron Exp $
 */
public class SsoAccessFacadeImpl implements SsoAccessFacade {

    /** 日志 */
    private final Logger      logger = LoggerFactory.getLogger(SsoAccessFacadeImpl.class);

    /** 用户权限DAO */
    @Autowired
    private SsoAccessDAO      ssoAccessDAO;

    /** 手工用户权限DAO */
    @Autowired
    private ExtraSsoAccessDAO extraSsoAccessDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate     bdssoTransactionTemplate;

    /** 用户DAO */
    @Autowired
    private SsoUserDAO        ssoUserDAO;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccessFacade#delete(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult delete(String uid, String token, String id) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ssoAccessDAO.delete(Integer.parseInt(id));
                logger.info("删除用户访问token，[uid={}]", uid);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccessFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAccessVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoAccessVO ssoAccessVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAccessVO, "实体为空");
            }

            @Override
            public void service() {
                ssoAccessDAO.update(SsoAccessConvertor.convertVo2Do(ssoAccessVO));
                logger.info("更新用户访问token，[uid={}]", uid);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccessFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAccessResult queryById(String uid, String token, String id) {
        final BdssoAccessResult result = new BdssoAccessResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAccessDO> list = new ArrayList<SsoAccessDO>();

                // 查询
                SsoAccessDO ssoAccessDO = ssoAccessDAO.query(Integer.parseInt(id));
                list.add(ssoAccessDO);

                // 设置结果
                result.setResultList(SsoAccessConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccessFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoExAccessResult fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoExAccessResult result = new BdssoExAccessResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoAccessDAO.fuzzyQueryForPage(key, new Date(), pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoAccessDO> listCopy = (ArrayList<ExSsoAccessDO>) pageList.clone();

                List<ExSsoAccessGroupDO> groupList = extraSsoAccessDAO.queryForGroup();

                // 搜索出来的应用和对应人数统计
                HashMap<String, HashMap<String, String>> appCount = new HashMap<String, HashMap<String, String>>();
                for (ExSsoAccessGroupDO exSsoAccessGroupDO : groupList) {
                    if (appCount.containsKey(exSsoAccessGroupDO.getAppName())) {
                        HashMap<String, String> subResult = appCount.get(exSsoAccessGroupDO.getAppName());
                        subResult.put(exSsoAccessGroupDO.getUserName(), null);
                        appCount.put(exSsoAccessGroupDO.getAppName(), subResult);
                    } else {
                        HashMap<String, String> subResult = new HashMap<String, String>();
                        subResult.put(exSsoAccessGroupDO.getUserName(), null);
                        appCount.put(exSsoAccessGroupDO.getAppName(), subResult);
                    }
                }

                // 设置结果
                result.setResultList(SsoAccessConvertor.convertExDos2ExVos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
                result.setOnline(ssoAccessDAO.queryByDate(new Date()).size());
                result.setTotalUser(new Long(ssoUserDAO.queryTotalUser()).intValue());
                result.setAppResult(appCount);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccessFacade#querySummaryInfo(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoSummaryResult querySummaryInfo(String uid, String token) {
        final BdssoSummaryResult result = new BdssoSummaryResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
            }

            @Override
            public void service() {
                // 设置结果
                result.setOnline(ssoAccessDAO.queryByDate(new Date()).size());
				result.setTotalUser(new Long(ssoUserDAO.queryTotalUser()).intValue());
            }

        }, result);

        return result;
    }

}
