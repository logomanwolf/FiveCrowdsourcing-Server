package com.dao;

import java.sql.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.dao.BaseDao;
import com.entity.Merchant;
import com.mysql.jdbc.Statement;


public class MerchantDao extends BaseDao{
	BaseDao basedao = new BaseDao();
	
	public Merchant Login(String phone, String password) throws Exception {
		String sql = "select password from merchant where phone = '"+phone+"'";
		Merchant merchant = null;
		String message;
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			List list = query(sql); // ���ݿ��ѯ����
			if (list.size()>0) {
				merchant = (Merchant)(list.get(0));
				if (merchant.getPassword().equals(password)) {
					message = "��½�ɹ�";
					System.out.println(message);
					return merchant;
				} else {
					message = "��¼ʧ�ܣ���¼�������";
					System.out.println(message);
					return null;
				}
			} else {
				message = "�õ�½�˺�δע��";
				System.out.println(message);
				return null;
			}
		} catch (SQLException e) {
			message = "���ݿ��ѯ����";
			System.out.println(message);
			e.printStackTrace();
		}
		return null;
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
