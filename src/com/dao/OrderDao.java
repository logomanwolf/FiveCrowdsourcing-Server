package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Deliveryorder;
import com.entity.Merchant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.ToolsClass.LocationUtils;
import com.dao.BaseDao;
import com.mysql.jdbc.Statement;

public class OrderDao extends BaseDao{
	public int insertDeliveryorder(Deliveryorder deliveryorder){
		String sql = "insert into fivecrowdsourcing.deliveryorder(merchantId,delMethodId,estimatedTime,estimatedTotalPrice,orderTime)"
				+ "values (?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, deliveryorder.getMerchantid());
			pstmt.setLong(2, deliveryorder.getDelmethodid());
			pstmt.setLong(3, deliveryorder.getEstimatedtime());
			pstmt.setDouble(4, deliveryorder.getEstimatedtotalprice());
			pstmt.setString(5, deliveryorder.getOrdertime());
			int rs = pstmt.executeUpdate();

			return rs;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	
	public long getDeliveryorderId(Deliveryorder deliveryorder){
		String sql = "select delOrderId form fivecrowdsourcing.deliveryorder where merchantId=? and "
				+ "delMethodId=? and estimatedTime=? and estimatedTotalPrice=? amd orderTime=?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, deliveryorder.getMerchantid());
			pstmt.setLong(2, deliveryorder.getDelmethodid());
			pstmt.setLong(3, deliveryorder.getEstimatedtime());
			pstmt.setDouble(4, deliveryorder.getEstimatedtotalprice());
			pstmt.setString(5, deliveryorder.getOrdertime());
			ResultSet rs = pstmt.executeQuery();
			long delorderid = 0;
			while(rs.next())
				delorderid = rs.getLong("delOrderId");
			return delorderid;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	
	//获得附近商店的配送单
	@SuppressWarnings("null")
	public List<Deliveryorder> getNearByDelOrder(double lat1, double lng1) {
		String sql = "SELECT * FROM fivecrowdsourcing.deliveryorder;";
		String sql2 = "select * from fivecrowdsourcing.merchant where merchantId = ?;";
		double lat2 = 0,lng2 = 0;

		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			while (rs.next()) {
				pstmt2.setDouble(1, rs.getDouble("merchantId"));
				ResultSet rs2 = pstmt2.executeQuery();
				while(rs2.next()){
					lat2 = rs2.getDouble("latitude");
					lng2 = rs2.getDouble("longitude");
				}
				
				if(LocationUtils.getDistance(lat1, lng1, lat2, lng2)<=3000){
					deliveryorder.setDelorderid(rs.getLong("delOrderId"));
					deliveryorder.setMerchantid(rs.getLong("merchantId"));
					deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
					deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
					deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
					deliveryorder.setOrdertime(rs.getString("orderTime"));
					list.add(deliveryorder);
				}
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
}
