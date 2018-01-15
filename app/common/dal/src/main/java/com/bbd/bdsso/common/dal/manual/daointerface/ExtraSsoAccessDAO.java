/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.daointerface;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessGroupDO;
import com.bbd.commons.lang.util.page.PageList;

public interface ExtraSsoAccessDAO {

    public PageList fuzzyQueryForPage(String key, Date date, int pageSize, int pageNum) throws DataAccessException;

    public List<ExSsoAccessGroupDO> queryForGroup() throws DataAccessException;

}