/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.daointerface;

import org.springframework.dao.DataAccessException;

import com.bbd.commons.lang.util.page.PageList;

public interface ExtraSsoAccountValidDAO {

    public PageList fuzzyQueryForPage(String key, int pageSize, int pageNum) throws DataAccessException;

}