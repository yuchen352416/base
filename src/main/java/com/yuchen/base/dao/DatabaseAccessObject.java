package com.yuchen.base.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yuchen.base.util.StructuredQueryLanguageUtil;

public class DatabaseAccessObject {

	private static final String CONNECTION_DRIVER = "com.mysql.jdbc.Driver";

	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static Connection connection = null;
	private static QueryRunner qr = null;
	
	public DatabaseAccessObject() {
		init();
		qr = new QueryRunner();
	}

	/**
	 * initialization connection
	 */
	private void init() {
		if (null == connection) {
			try {
				Class.forName(CONNECTION_DRIVER);
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_db?useUnicode=true&characterEncoding=utf-8", USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void destroy() throws SQLException {
		DbUtils.close(connection);
	}
	
	public Long insert(Object object) {
		Long result = null;
		Map<String, Object> map = StructuredQueryLanguageUtil.getInsertSQL(object);
		if (map != null) {
			String sql = (String) map.get("sql");
			Object[] params = (Object[]) map.get("params");
			try {
				qr.update(connection, sql, params);
				result = (Long) qr.query(connection, "select last_insert_id()", new ScalarHandler<Long>(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * Delete data by id.<br>
	 * The primary key of the table must be id
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Object object) {
		boolean result = false;
		Map<String, Object> map = StructuredQueryLanguageUtil.getDeleteSQL(object);
		try {
			if (map != null) {
				String sql = (String) map.get("sql");
				Object[] params = (Object[]) map.get("params");
				int count = qr.update(connection, sql, params);
				if (count > 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * update Data By Object id.<br>
	 * The primary key of the table must be id
	 * 
	 * @param object Need to modify the object
	 * @param isfull Whether all updates (including NULL)
	 * @return
	 */
	public boolean update(Object object, boolean isfull) {
		Boolean result = false;
		Map<String, Object> map = StructuredQueryLanguageUtil.getUpdateSQL(object, isfull);
		try {
			if (map != null) {
				String sql = (String) map.get("sql");
				Object[] params = (Object[]) map.get("params");
				int count = qr.update(connection, sql, params);
				if (count > 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Object get(Serializable id) {
		
		String sql = "";
		Object[] params = {3};
		
		
		return null;
	}
	
}
