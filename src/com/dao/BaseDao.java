package com.dao;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Statement;
import javax.naming.*;

public class BaseDao {
	DataSource dataSource;
	
	// �ڹ��췽���з�������Դ����
	public BaseDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/fivecrowdsouring");
		} catch (NamingException ne) {
			System.out.println("Exception:" + ne);
		}
	}

	// ����һ�����Ӷ���
	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	//��ѯ
	@SuppressWarnings("rawtypes")
	public List query(String querySql) throws Exception {
		Statement stateMent = (Statement)getConnection().createStatement();
		return (List) stateMent.executeQuery(querySql);
	}
	
	//����
	public int update(String insertSql) throws Exception {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeUpdate(insertSql);
	}

}
