/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccessDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessGroupDO;
import com.bbd.commons.lang.util.page.PageList;
import com.bbd.commons.lang.util.page.Paginator;

public class IbatisExtraSsoAccessDAO extends SqlMapClientDaoSupport implements ExtraSsoAccessDAO {

    @Override
    public PageList fuzzyQueryForPage(String key, Date date, int pageSize, int pageNum) throws DataAccessException {
        Map param = new HashMap();

        param.put("key", key);
        param.put("date", date);

        Paginator paginator = new Paginator();
        paginator.setItemsPerPage(pageSize);
        paginator.setPage(pageNum);

        paginator.setItems(((Integer) getSqlMapClientTemplate().queryForObject("MS-BDSSO-Extra-SSO-ACCESS-FUZZY-QUERY-FOR-PAGE-COUNT-FOR-PAGING", param)).intValue());

        PageList pageList = new PageList();
        pageList.setPaginator(paginator);

        if (paginator.getBeginIndex() <= paginator.getItems()) {
            param.put("offset", new Integer(paginator.getOffset()));
            param.put("pageSize", new Integer(pageSize));
            pageList.addAll(getSqlMapClientTemplate().queryForList("MS-Extra-SSO-ACCESS-FUZZY-QUERY-FOR-PAGE", param));
        }

        return pageList;
    }

    /** 
     * @see com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccessDAO#queryForGroup()
     */
    @Override
    public List<ExSsoAccessGroupDO> queryForGroup() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("MS-Extra-SSO-ACCESS-QUERY-FOR-GROUP");
    }

}