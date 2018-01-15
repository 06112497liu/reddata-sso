/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;

/**
 * An ibatis based implementation of dao interface <tt>com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_user_role.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class IbatisSsoUserRoleDAO extends SqlMapClientDaoSupport implements SsoUserRoleDAO {
	/**
	 *  Insert one <tt>SsoUserRoleDO</tt> object to DB table <tt>sso_user_role</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_user_role(user_id,role_id,description,last_modifier,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoUserRole
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoUserRoleDO ssoUserRole) throws DataAccessException {
    	if (ssoUserRole == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-SSO-USER-ROLE-INSERT", ssoUserRole);

        return ssoUserRole.getId();
    }

	/**
	 *  Delete records from DB table <tt>sso_user_role</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user_role where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(Integer id) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-USER-ROLE-DELETE", id);
    }

	/**
	 *  Delete records from DB table <tt>sso_user_role</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user_role where (user_id = ?)</tt>
	 *
	 *	@param userId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByUserId(Integer userId) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-USER-ROLE-DELETE-BY-USER-ID", userId);
    }

	/**
	 *  Delete records from DB table <tt>sso_user_role</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user_role where ((user_id = ?) AND (role_id = ?))</tt>
	 *
	 *	@param userId
	 *	@param roleId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByUserIdAndRoleId(Integer userId, Integer roleId) throws DataAccessException {
        Map param = new HashMap();

        param.put("userId", userId);
        param.put("roleId", roleId);

        return getSqlMapClientTemplate().delete("MS-SSO-USER-ROLE-DELETE-BY-USER-ID-AND-ROLE-ID", param);
    }

	/**
	 *  Update DB table <tt>sso_user_role</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_user_role set user_id=?, role_id=?, description=?, last_modifier=?, gmt_modified=? where (id = ?)</tt>
	 *
	 *	@param ssoUserRole
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(SsoUserRoleDO ssoUserRole) throws DataAccessException {
    	if (ssoUserRole == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-SSO-USER-ROLE-UPDATE", ssoUserRole);
    }

	/**
	 *  Query DB table <tt>sso_user_role</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, role_id, description, last_modifier, gmt_create, gmt_modified from sso_user_role where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return SsoUserRoleDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserRoleDO query(Integer id) throws DataAccessException {

        return (SsoUserRoleDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-ROLE-QUERY", id);

    }

	/**
	 *  Query DB table <tt>sso_user_role</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, role_id, description, last_modifier, gmt_create, gmt_modified from sso_user_role where (user_id = ?)</tt>
	 *
	 *	@param userId
	 *	@return List<SsoUserRoleDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoUserRoleDO> queryByUserId(Integer userId) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-USER-ROLE-QUERY-BY-USER-ID", userId);

    }

	/**
	 *  Query DB table <tt>sso_user_role</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, role_id, description, last_modifier, gmt_create, gmt_modified from sso_user_role where (role_id = ?)</tt>
	 *
	 *	@param roleId
	 *	@return List<SsoUserRoleDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoUserRoleDO> queryByRoleId(Integer roleId) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-USER-ROLE-QUERY-BY-ROLE-ID", roleId);

    }

	/**
	 *  Query DB table <tt>sso_user_role</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, role_id, description, last_modifier, gmt_create, gmt_modified from sso_user_role where ((user_id = ?) AND (role_id = ?))</tt>
	 *
	 *	@param userId
	 *	@param roleId
	 *	@return SsoUserRoleDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserRoleDO queryByUserIdAndRoleId(Integer userId, Integer roleId) throws DataAccessException {
        Map param = new HashMap();

        param.put("userId", userId);
        param.put("roleId", roleId);

        return (SsoUserRoleDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-ROLE-QUERY-BY-USER-ID-AND-ROLE-ID", param);

    }

	/**
	 *  Delete records from DB table <tt>sso_user_role</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user_role where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-USER-ROLE-DELETE-BY-DESC", description);
    }

}