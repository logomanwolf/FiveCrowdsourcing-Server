package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.*;
import com.entity.Merchant;
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
				Long merchantid = (long) rs.getInt("merchantId");
				String account = rs.getString("idCardNumber");
				String pwd = rs.getString("password");
				String name = rs.getString("name");
				Long margin = (long) rs.getInt("margin");
				String address = rs.getString("address");
				Merchant merchant = new Merchant();
				merchant.setIdcardnumber(account);
				merchant.setMerchantid(merchantid);
				merchant.setName(name);
				merchant.setMargin(margin);
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
	public List<Merchant> findMerchants(String name) {
		// TODO Auto-generated method stub
		return null;
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
}
