package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.*;
import com.entity.Merchant;
import com.entity.Typeofgoods;
import com.mysql.jdbc.Statement;

public class MerchantDao extends BaseDao implements IMerchantDao {

	// ºÏ≤È…Ãªß√‹¬Î
	public Merchant checkMerchant(String phone, String password) {
		String sql = "SELECT * FROM fivecrowdsourcing.merchant where phone=? AND password=?;";
		Merchant merchant = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				merchant = new Merchant();
				merchant.setMerchantid(rs.getLong("merchantId"));
				merchant.setTofgid(rs.getLong("tofgId"));
				merchant.setName(rs.getString("name"));
				merchant.setIdcardnumber(rs.getString("idCardNumber"));
				merchant.setAddress(rs.getString("address"));
				merchant.setPhone(rs.getString("phone"));
				merchant.setStorename(rs.getString("storeName"));
				merchant.setPassword(rs.getString("password"));
				merchant.setBuslicensephoto(rs.getString("busLicensePhoto"));
				merchant.setFoodbuslicensephoto(rs
						.getString("foodBusLicensePhoto"));
				merchant.setIdcardphoto(rs.getString("idCardPhoto"));
				merchant.setMargin(rs.getLong("margin"));
			}
			return merchant;
		} catch (SQLException se) {
			se.printStackTrace();
			return merchant;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#addMerchant(com.entity.Merchant)
	 */

	public int addMerchant(Merchant merchant) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#findMerchants()
	 */
	@Override
	public List<Merchant> findMerchants() {
		// TODO Auto-generated method stub
		List<Merchant> result = new ArrayList<Merchant>();
		String query = "select * from merchant";
		ResultSet rs = this.executeQuery(query, null);
		try {
			while (rs.next()) {

				Merchant merchant = new Merchant();
				merchant = new Merchant();
				merchant.setMerchantid(rs.getLong("merchantId"));
				merchant.setTofgid(rs.getLong("tofgId"));
				merchant.setName(rs.getString("name"));
				merchant.setIdcardnumber(rs.getString("idCardNumber"));
				merchant.setAddress(rs.getString("address"));
				merchant.setPhone(rs.getString("phone"));
				merchant.setStorename(rs.getString("storeName"));
				merchant.setPassword(rs.getString("password"));
				merchant.setBuslicensephoto(rs.getString("busLicensePhoto"));
				merchant.setFoodbuslicensephoto(rs
						.getString("foodBusLicensePhoto"));
				merchant.setIdcardphoto(rs.getString("idCardPhoto"));
				merchant.setMargin(rs.getLong("margin"));
				result.add(merchant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#findMerchants(java.lang.String)
	 */
	@Override
	public Merchant findMerchantById(String merchantId) {
		Merchant merchant = new Merchant();
		String query = "select * from merchant where merchantId=?";
		ArrayList<String> params = new ArrayList<>();
		params.add(merchantId);
		ResultSet rs = this.executeQuery(query, params);
		try {
			while (rs.next()) {
				merchant = new Merchant();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return merchant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#findMerchantsByDept(java.lang.String)
	 */
	@Override
	public List<Merchant> findMerchantsByDept(String dept) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#findMerchantsByRole(java.lang.String)
	 */
	@Override
	public List<Merchant> findMerchantsByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#delMerchantById(int)
	 */
	@Override
	public int delMerchantById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#updateMerchantById(int, com.entity.Merchant)
	 */
	@Override
	public int updateMerchantById(int id, Merchant role) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.IMerchantDao#checkMerchant(java.lang.String)
	 */
	@Override
	public boolean checkMerchant(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Typeofgoods findTypeOfGoodsNameById(Long tofgId) {
		// TODO Auto-generated method stub
		Typeofgoods typeofgoods = new Typeofgoods();
		String query = "select * from typeofgoods where tofgId=?";
		ArrayList<String> params = new ArrayList<>();
		params.add(tofgId.toString());
		ResultSet rs = this.executeQuery(query, params);
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				Long delMethodId = (long) rs.getInt("delMethodId");
				typeofgoods.setTofgid(tofgId);
				typeofgoods.setName(name);
				typeofgoods.setDelmethodid(delMethodId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return typeofgoods;
	}
}
