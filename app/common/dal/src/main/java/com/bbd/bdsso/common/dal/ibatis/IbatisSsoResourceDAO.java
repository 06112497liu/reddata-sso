/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.daointerface.SsoResourceDAO;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoResourceDO;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbd.bdsso.common.dal.dataobject.SsoResourceDO;

/**
 * An ibatis based implementation of dao interface <tt>com.bbd.bdsso.common.dal.daointerface.SsoResourceDAO</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_resource.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class IbatisSsoResourceDAO extends SqlMapClientDaoSupport implements SsoResourceDAO {
	/**
	 *  Insert one <tt>SsoResourceDO</tt> object to DB table <tt>sso_resource</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_resource(type,resource_name,is_enable,description,last_modifier,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoResource
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoResourceDO ssoResource) throws DataAccessException {
    	if (ssoResource == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-SSO-RESOURCE-INSERT", ssoResource);

        return ssoResource.getId();
    }

	/**
	 *  Delete records from DB table <tt>sso_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_resource where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(Integer id) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-RESOURCE-DELETE", id);
    }

	/**
	 *  Update DB table <tt>sso_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_resource set type=?, resource_name=?, is_enable=?, description=?, last_modifier=?, gmt_modified=? where (id = ?)</tt>
	 *
	 *	@param ssoResource
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(SsoResourceDO ssoResource) throws DataAccessException {
    	if (ssoResource == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-SSO-RESOURCE-UPDATE", ssoResource);
    }

	/**
	 *  Query DB table <tt>sso_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, type, resource_name, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_resource where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return SsoResourceDO
	 *	@throws DataAccessException
	 */	 
    public SsoResourceDO query(Integer id) throws DataAccessException {

        return (SsoResourceDO) getSqlMapClientTemplate().queryForObject("MS-SSO-RESOURCE-QUERY", id);

    }

	/**
	 *  Query DB table <tt>sso_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, type, resource_name, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_resource where (resource_name = ?)</tt>
	 *
	 *	@param resourceName
	 *	@return SsoResourceDO
	 *	@throws DataAccessException
	 */	 
    public SsoResourceDO queryByResourceName(String resourceName) throws DataAccessException {

        return (SsoResourceDO) getSqlMapClientTemplate().queryForObject("MS-SSO-RESOURCE-QUERY-BY-RESOURCE-NAME", resourceName);

    }

	/**
	 *  Query DB table <tt>sso_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select b.id id, b.type type, b.resource_name resource_name, b.is_enable is_enable, b.last_modifier last_modifier, c.description description, b.gmt_create gmt_create, b.gmt_modified gmt_modified from sso_auth a, sso_resource b, sso_auth_resource c where ((a.id = c.auth_id) AND (b.id = c.resource_id))</tt>
	 *
	 *	@param authName
	 *	@return List<SsoResourceDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoResourceDO> queryByAuthName(String authName) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-RESOURCE-QUERY-BY-AUTH-NAME", authName);

    }

	/**
	 *  Delete records from DB table <tt>sso_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_resource where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-RESOURCE-DELETE-BY-DESC", description);
    }

	@Override
	public List<SsoResourceDO> queryByAppIdAndUserName(String userId, String appName) throws DataAccessException {
		Map param = new HashMap();

		param.put("userId", userId);
		param.put("appName", appName);

		return getSqlMapClientTemplate().queryForList("MS-SSO-RESOURCE-QUERY-BY-APP-ID-AND-USER-NAME", param);

	}

}