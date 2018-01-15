/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.daointerface.SsoAuthResourceDAO;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoAuthResourceDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.bbd.bdsso.common.dal.dataobject.SsoAuthResourceDO;

/**
 * An ibatis based implementation of dao interface <tt>com.bbd.bdsso.common.dal.daointerface.SsoAuthResourceDAO</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_auth_resource.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class IbatisSsoAuthResourceDAO extends SqlMapClientDaoSupport implements SsoAuthResourceDAO {
	/**
	 *  Insert one <tt>SsoAuthResourceDO</tt> object to DB table <tt>sso_auth_resource</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_auth_resource(auth_id,resource_id,description,last_modifier,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoAuthResource
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoAuthResourceDO ssoAuthResource) throws DataAccessException {
    	if (ssoAuthResource == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-SSO-AUTH-RESOURCE-INSERT", ssoAuthResource);

        return ssoAuthResource.getId();
    }

	/**
	 *  Delete records from DB table <tt>sso_auth_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_auth_resource where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(Integer id) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-AUTH-RESOURCE-DELETE", id);
    }

	/**
	 *  Delete records from DB table <tt>sso_auth_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_auth_resource where (auth_id = ?)</tt>
	 *
	 *	@param authId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByAuthId(Integer authId) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-AUTH-RESOURCE-DELETE-BY-AUTH-ID", authId);
    }

	/**
	 *  Delete records from DB table <tt>sso_auth_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_auth_resource where ((auth_id = ?) AND (resource_id = ?))</tt>
	 *
	 *	@param authId
	 *	@param resourceId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByAuthIdAndResourceId(Integer authId, Integer resourceId) throws DataAccessException {
        Map param = new HashMap();

        param.put("authId", authId);
        param.put("resourceId", resourceId);

        return getSqlMapClientTemplate().delete("MS-SSO-AUTH-RESOURCE-DELETE-BY-AUTH-ID-AND-RESOURCE-ID", param);
    }

	/**
	 *  Update DB table <tt>sso_auth_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_auth_resource set auth_id=?, resource_id=?, description=?, last_modifier=?, gmt_modified=? where (id = ?)</tt>
	 *
	 *	@param ssoAuthResource
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(SsoAuthResourceDO ssoAuthResource) throws DataAccessException {
    	if (ssoAuthResource == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-SSO-AUTH-RESOURCE-UPDATE", ssoAuthResource);
    }

	/**
	 *  Query DB table <tt>sso_auth_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, auth_id, resource_id, description, last_modifier, gmt_create, gmt_modified from sso_auth_resource where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return SsoAuthResourceDO
	 *	@throws DataAccessException
	 */	 
    public SsoAuthResourceDO query(Integer id) throws DataAccessException {

        return (SsoAuthResourceDO) getSqlMapClientTemplate().queryForObject("MS-SSO-AUTH-RESOURCE-QUERY", id);

    }

	/**
	 *  Query DB table <tt>sso_auth_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, auth_id, resource_id, description, last_modifier, gmt_create, gmt_modified from sso_auth_resource where (resource_id = ?)</tt>
	 *
	 *	@param resourceId
	 *	@return List<SsoAuthResourceDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoAuthResourceDO> queryByResourceId(Integer resourceId) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-AUTH-RESOURCE-QUERY-BY-RESOURCE-ID", resourceId);

    }

	/**
	 *  Query DB table <tt>sso_auth_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, auth_id, resource_id, description, last_modifier, gmt_create, gmt_modified from sso_auth_resource where (auth_id = ?)</tt>
	 *
	 *	@param authId
	 *	@return List<SsoAuthResourceDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoAuthResourceDO> queryByAuthId(Integer authId) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-AUTH-RESOURCE-QUERY-BY-AUTH-ID", authId);

    }

	/**
	 *  Query DB table <tt>sso_auth_resource</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, auth_id, resource_id, description, last_modifier, gmt_create, gmt_modified from sso_auth_resource where ((auth_id = ?) AND (resource_id = ?))</tt>
	 *
	 *	@param authId
	 *	@param resourceId
	 *	@return SsoAuthResourceDO
	 *	@throws DataAccessException
	 */	 
    public SsoAuthResourceDO queryByAuthIdAndResourceId(Integer authId, Integer resourceId) throws DataAccessException {
        Map param = new HashMap();

        param.put("authId", authId);
        param.put("resourceId", resourceId);

        return (SsoAuthResourceDO) getSqlMapClientTemplate().queryForObject("MS-SSO-AUTH-RESOURCE-QUERY-BY-AUTH-ID-AND-RESOURCE-ID", param);

    }

	/**
	 *  Delete records from DB table <tt>sso_auth_resource</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_auth_resource where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-AUTH-RESOURCE-DELETE-BY-DESC", description);
    }

}