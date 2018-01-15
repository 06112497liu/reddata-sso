/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.daointerface;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoResourceDO;
import com.bbd.commons.lang.util.page.PageList;

public interface ExtraSsoResourceDAO {

    public PageList fuzzyQueryForPage(String key, int pageSize, int pageNum) throws DataAccessException;

    public List<ExSsoResourceDO> queryAllByParams() throws DataAccessException;

}