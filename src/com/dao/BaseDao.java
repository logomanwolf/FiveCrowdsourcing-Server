package com.dao;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Statement;
import javax.naming.*;

public class BaseDao {
	DataSource dataSource;
	
	// 在构造方法中返回数据源对象
	public BaseDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/fivecrowdsouring");
		} catch (NamingException ne) {
			System.out.println("Exception:" + ne);
		}
	}

	// 返回一个连接对象
	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
}
