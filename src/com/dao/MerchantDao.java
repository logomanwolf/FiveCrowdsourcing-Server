package com.dao;

import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.BaseDao;
import com.entity.Merchant;


public class MerchantDao extends BaseDao{
	BaseDao basedao = new BaseDao();
	
	public String Login(String phone, String password,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sql = "select password from merchant where phone = '"+phone+"'";
		Merchant merchant = null;
		String message;
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			List list = basedao.query(sql); // ���ݿ��ѯ����
			String result="";
			if (list.size()>0) {
				merchant = (Merchant)(list.get(0));
				if (merchant.getPassword().equals(password)) {
					result="success";
					message = "��½�ɹ�";
					System.out.println(message);
					JSONObject jsonObject = new JSONObject(merchant);
					jsonObject.put("result", result);
					response.getWriter().append(jsonObject.toString());
					return result;
				} else {
					result="false";
					message = "��¼ʧ�ܣ���¼�������";
					System.out.println(message);
					return result;
				}
			} else {
				result = "false";
				message = "�õ�½�˺�δע��";
				System.out.println(message);
				return result;
			}
		} catch (SQLException e) {
			message = "���ݿ��ѯ����";
			System.out.println(message);
			e.printStackTrace();
		}
		return "false";
	}

}
