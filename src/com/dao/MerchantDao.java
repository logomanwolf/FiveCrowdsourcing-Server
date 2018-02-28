package com.dao;

import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.BaseDao;
import com.entity.Merchant;



public class MerchantDao extends BaseDao{
	   // ºÏ≤È…Ãªß√‹¬Î
		public Merchant checkMerchant(String phone, String password) {
			String sql = "SELECT * FROM fivecrowdsourcing.merchant where phone=? AND password=?;";
			Merchant merchant = null;
			try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, phone);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {	
					merchant=new Merchant();
					merchant.setMerchantid(rs.getLong("merchantId"));
					merchant.setTofgid(rs.getLong("tofgId"));
					merchant.setName(rs.getString("name"));
					merchant.setIdcardnumber(rs.getString("idCardNumber"));
					merchant.setAddress(rs.getString("address"));
					merchant.setPhone(rs.getString("phone"));
					merchant.setStorename(rs.getString("storeName"));
					merchant.setPassword(rs.getString("password"));
					merchant.setBuslicensephoto(rs.getString("busLicensePhoto"));
					merchant.setFoodbuslicensephoto(rs.getString("foodBusLicensePhoto"));
					merchant.setIdcardphoto(rs.getString("idCardPhoto"));
					merchant.setMargin(rs.getLong("margin"));					
				}
				return merchant;
			} catch (SQLException se) {
				se.printStackTrace();
				return merchant;
			}
		}

}
