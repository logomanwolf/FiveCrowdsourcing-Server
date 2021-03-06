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

import com.ToolsClass.EstimateUtils;
import com.dao.BaseDao;
import com.mysql.jdbc.Statement;

public class OrderDao extends BaseDao {
	public int insertDeliveryorder(Deliveryorder deliveryorder) {
		String sql = "insert into fivecrowdsourcing.deliveryorder(merchantId,delMethodId,estimatedTime,estimatedTotalPrice,distance,orderTime,"
				+ "cusName,cusPhone,cusAddress,things,status,cusLat,cusLog) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, deliveryorder.getMerchantid());
			pstmt.setLong(2, deliveryorder.getDelmethodid());
			pstmt.setLong(3, deliveryorder.getEstimatedtime());
			pstmt.setDouble(4, deliveryorder.getEstimatedtotalprice());
			pstmt.setInt(5, deliveryorder.getDistance());
			pstmt.setString(6, deliveryorder.getOrdertime());
			pstmt.setString(7, deliveryorder.getCusName());
			pstmt.setString(8, deliveryorder.getCusPhone());
			pstmt.setString(9, deliveryorder.getCusAddress());
			pstmt.setString(10, deliveryorder.getThings());
			pstmt.setInt(11, deliveryorder.getStatus());
			pstmt.setDouble(12, deliveryorder.getCuslat());
			pstmt.setDouble(13, deliveryorder.getCuslog());
			int rs = pstmt.executeUpdate();

			return rs;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}

	public long getDeliveryorderId(Deliveryorder deliveryorder) {
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
			while (rs.next())
				delorderid = rs.getLong("delOrderId");
			return delorderid;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}

	// 跑腿人获取待取货订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllPendingOrder(Long runnerid) {
		String sql = "select delOrderId,estimatedTime,estimatedTotalPrice,orderTime,cusname,cusphone,cusaddress,things"
				+ ",delMethodId,cusLat,cusLog,storeName,address"
				+ " from fivecrowdsourcing.deliveryorder,fivecrowdsourcing.merchant"
				+ " where runnerId = ? and deliveryorder.status='3' and deliveryorder.merchantId=merchant.merchantId;";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, runnerid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				deliveryorder.setStoreAddress(rs.getString("address"));
				deliveryorder.setStoreName(rs.getString("storeName"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 跑腿人获取配送中订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllRunnerSendingOrder(Long runnerid) {
		String sql = "select delOrderId,estimatedTime,estimatedTotalPrice,orderTime,cusname,cusphone,cusaddress,things"
				+ ",delMethodId,cusLat,cusLog,storeName,address"
				+ " from fivecrowdsourcing.deliveryorder,fivecrowdsourcing.merchant"
				+ " where runnerId = ? and deliveryorder.status='4' and deliveryorder.merchantId=merchant.merchantId;";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, runnerid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				deliveryorder.setStoreAddress(rs.getString("address"));
				deliveryorder.setStoreName(rs.getString("storeName"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 跑腿人获取已完成订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllRunnerCompletedOrder(Long runnerid) {
		String sql = "select delOrderId,estimatedTime,estimatedTotalPrice,orderTime,cusname,cusphone,cusaddress,things"
				+ ",delMethodId,cusLat,cusLog,storeName,address"
				+ " from fivecrowdsourcing.deliveryorder,fivecrowdsourcing.merchant"
				+ " where runnerId = ? and deliveryorder.status='5' and deliveryorder.merchantId=merchant.merchantId;";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, runnerid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				deliveryorder.setStoreAddress(rs.getString("address"));
				deliveryorder.setStoreName(rs.getString("storeName"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 获得附近商店的配送单
	@SuppressWarnings("null")
	public List<Deliveryorder> getNearByDelOrder(double lat1, double lng1) throws SQLException {
		String sql = "SELECT * FROM fivecrowdsourcing.deliveryorder where deliveryorder.status='2';";
		String sql2 = "select * from fivecrowdsourcing.merchant where merchantId = ?;";
		double lat2 = 0, lng2 = 0;
		Connection conn = null;
		String url = "http://api.map.baidu.com/routematrix/v2/riding";// 骑行接口

		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try {
			conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			while (rs.next()) {
				pstmt2.setDouble(1, rs.getDouble("merchantId"));
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					lat2 = rs2.getDouble("latitude");
					lng2 = rs2.getDouble("longitude");
					deliveryorder.setStoreName(rs2.getString("storeName"));
					deliveryorder.setStoreAddress(rs2.getString("address"));
				}
				// 接口参数
				String param = "output=json&origins=" + lat1 + "," + lng1 + "&destinations=" + lat2 + "," + lng2
						+ "&ak=Gsj9D1Ih7RV00jypSLk8osnircS4NRPA";
				if (EstimateUtils.getEstimatedDistance(url, param) <= 3000) {
					deliveryorder.setDelorderid(rs.getLong("delOrderId"));
					deliveryorder.setMerchantid(rs.getLong("merchantId"));
					deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
					deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
					deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
					deliveryorder.setOrdertime(rs.getString("orderTime"));
					deliveryorder.setCusName(rs.getString("cusname"));
					deliveryorder.setCusPhone(rs.getString("cusphone"));
					deliveryorder.setCusAddress(rs.getString("cusaddress"));
					deliveryorder.setThings(rs.getString("things"));
					list.add(deliveryorder);
				}
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			conn.close();
		}
	}

	// 获得所有未接受的订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllInactiveDelOrder(Long merchantid) {
		String sql = "select * from fivecrowdsourcing.deliveryorder where merchantId = ? and deliveryorder.status='2';";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, merchantid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setMerchantid(rs.getLong("merchantId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 获得所有未取货的订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllInactiveGood(Long merchantid) {
		String sql = "select * from fivecrowdsourcing.deliveryorder where merchantId = ? and deliveryorder.status='3';";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, merchantid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setMerchantid(rs.getLong("merchantId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setRunnerid(rs.getLong("runnerId"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 获得所有配送中的订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllSendingOrder(Long merchantid) {
		String sql = "select * from fivecrowdsourcing.deliveryorder where merchantId = ? and deliveryorder.status='4';";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, merchantid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setMerchantid(rs.getLong("merchantId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setRunnerid(rs.getLong("runnerId"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 获得所有完成的订单
	@SuppressWarnings("null")
	public List<Deliveryorder> getAllCompletedOrder(Long merchantid) {
		String sql = "select * from fivecrowdsourcing.deliveryorder where merchantId = ? and deliveryorder.status='5';";
		List<Deliveryorder> list = new ArrayList<Deliveryorder>();
		Deliveryorder deliveryorder = new Deliveryorder();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, merchantid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setMerchantid(rs.getLong("merchantId"));
				deliveryorder.setDelmethodid(rs.getLong("delMethodId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedTime"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedTotalPrice"));
				deliveryorder.setOrdertime(rs.getString("orderTime"));
				deliveryorder.setCusName(rs.getString("cusname"));
				deliveryorder.setCusPhone(rs.getString("cusphone"));
				deliveryorder.setCusAddress(rs.getString("cusaddress"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setRunnerid(rs.getLong("runnerId"));
				deliveryorder.setCuslat(rs.getDouble("cusLat"));
				deliveryorder.setCuslog(rs.getDouble("cusLog"));
				list.add(deliveryorder);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	public Long getDelmethodid(Long merchantid) {
		String sql = "select delMethodId from fivecrowdsourcing.typeofgoods where tofgId = "
				+ "(select tofgId from fivecrowdsourcing.merchant where merchantId = " + merchantid + ")";
		long result = 0;
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getLong("delMethodId");
				System.out.println(result);
			}
			return result;
		} catch (SQLException se) {
			se.printStackTrace();
			return (long) -1;
		}
	}

	@SuppressWarnings("null")
	public Double[] getMerchantLocation(Long merchantid) {
		String sql = "select * from fivecrowdsourcing.merchant where merchantId = " + merchantid;
		Double[] location = new Double[2];
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				location[0] = rs.getDouble("latitude");
				location[1] = rs.getDouble("longitude");
			}
			return location;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	// 用于根据id查找相应的单子,没有到达时间,没有额外价格
	public Deliveryorder getOrderById(Long delorderid) {
		String query = "SELECT * FROM fivecrowdsourcing.deliveryorder where delOrderId=?;";
		ArrayList<String> params = new ArrayList<>();
		params.add(delorderid.toString());
		ResultSet rs = this.executeQuery(query, params);
		Deliveryorder deliveryorder = new Deliveryorder();
		try {
			while (rs.next()) {
				deliveryorder.setDelorderid(delorderid);
				deliveryorder.setEstimatedtime(rs.getLong("estimatedtime"));
				deliveryorder.setMerchantid(rs.getLong("merchantid"));
				deliveryorder.setRunnerid(rs.getLong("runnerid"));
				deliveryorder.setDelmethodid(rs.getLong("delmethodid"));
				deliveryorder.setIntime(rs.getString("intime"));
				deliveryorder.setCusName(rs.getString("cusName"));
				deliveryorder.setCusAddress(rs.getString("cusAddress"));
				deliveryorder.setCusPhone(rs.getString("cusPhone"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedtotalprice"));
				deliveryorder.setStatus(rs.getInt("status"));
				deliveryorder.setIntegral(rs.getInt("integral"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setTrueweight(rs.getDouble("trueweight"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return deliveryorder;

	}

	// 用于更新订单信息(配送到达时)
	public Integer updateOrder(Deliveryorder deliveryorder) throws SQLException {
		String query = "UPDATE deliveryorder SET deliveryorder.outTime = ?, deliveryorder.extraPrice = ?,deliveryorder.status=? WHERE deliveryorder.merchantId =?";
		ArrayList<String> params = new ArrayList<>();
		params.add(deliveryorder.getOuttime());
		params.add(deliveryorder.getExtraprice().toString());
		params.add(((Integer) deliveryorder.getStatus()).toString());
		params.add(deliveryorder.getDelorderid().toString());
		int rs = this.executeUpdate(query, params);
		return rs;
	}

	// 查询相应状态的订单(根据商家)
	public List<Deliveryorder> getOrdersByStatus(Integer status, Long merchantid) {
		String query = "SELECT * FROM fivecrowdsourcing.deliveryorder where merchantId=? and status=?;";
		ArrayList<String> params = new ArrayList<>();
		params.add(merchantid.toString());
		params.add(status.toString());
		ResultSet rs = this.executeQuery(query, params);
		List<Deliveryorder> deliveryorders = new ArrayList<>();
		try {
			while (rs.next()) {
				Deliveryorder deliveryorder = new Deliveryorder();
				deliveryorder.setDelorderid(rs.getLong("delOrderId"));
				deliveryorder.setEstimatedtime(rs.getLong("estimatedtime"));
				deliveryorder.setMerchantid(rs.getLong("merchantid"));
				deliveryorder.setRunnerid(rs.getLong("runnerid"));
				deliveryorder.setDelmethodid(rs.getLong("delmethodid"));
				deliveryorder.setIntime(rs.getString("intime"));
				deliveryorder.setCusName(rs.getString("cusName"));
				deliveryorder.setCusAddress(rs.getString("cusAddress"));
				deliveryorder.setCusPhone(rs.getString("cusPhone"));
				deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedtotalprice"));
				deliveryorder.setDistance(rs.getInt("distance"));
				deliveryorder.setStatus(rs.getInt("status"));
				deliveryorder.setIntegral(rs.getInt("integral"));
				deliveryorder.setThings(rs.getString("things"));
				deliveryorder.setTrueweight(rs.getDouble("trueweight"));
				deliveryorder.setOuttime(rs.getString("outtime"));
				deliveryorder.setExtraprice(rs.getDouble("extraprice"));
				deliveryorders.add(deliveryorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return deliveryorders;

	}

	// 查询相应状态的订单(根据跑腿人)
		public List<Deliveryorder> getOrdersByStatusRunner(Integer status, Long runnerid) {
			String query = "SELECT * FROM fivecrowdsourcing.deliveryorder where runnerId=? and status=?;";
			ArrayList<String> params = new ArrayList<>();
			params.add(runnerid.toString());
			params.add(status.toString());
			ResultSet rs = this.executeQuery(query, params);
			List<Deliveryorder> deliveryorders = new ArrayList<>();
			try {
				while (rs.next()) {
					Deliveryorder deliveryorder = new Deliveryorder();
					deliveryorder.setDelorderid(rs.getLong("delOrderId"));
					deliveryorder.setEstimatedtime(rs.getLong("estimatedtime"));
					deliveryorder.setMerchantid(rs.getLong("merchantid"));
					deliveryorder.setRunnerid(rs.getLong("runnerid"));
					deliveryorder.setDelmethodid(rs.getLong("delmethodid"));
					deliveryorder.setIntime(rs.getString("intime"));
					deliveryorder.setCusName(rs.getString("cusName"));
					deliveryorder.setCusAddress(rs.getString("cusAddress"));
					deliveryorder.setCusPhone(rs.getString("cusPhone"));
					deliveryorder.setEstimatedtotalprice(rs.getDouble("estimatedtotalprice"));
					deliveryorder.setDistance(rs.getInt("distance"));
					deliveryorder.setStatus(rs.getInt("status"));
					deliveryorder.setIntegral(rs.getInt("integral"));
					deliveryorder.setThings(rs.getString("things"));
					deliveryorder.setTrueweight(rs.getDouble("trueweight"));
					deliveryorder.setOuttime(rs.getString("outtime"));
					deliveryorder.setExtraprice(rs.getDouble("extraprice"));
					deliveryorders.add(deliveryorder);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.close();
			}
			return deliveryorders;

		}
	
	
	// 用于更新订单信息(付款成功时)
	public Integer updateOrderAfterPay(Deliveryorder deliveryorder) throws SQLException {
		String query = "UPDATE deliveryorder SET deliveryorder.status=? WHERE deliveryorder.merchantId =?";
		ArrayList<String> params = new ArrayList<>();
		params.add(((Integer) deliveryorder.getStatus()).toString());
		params.add(deliveryorder.getDelorderid().toString());
		int rs = this.executeUpdate(query, params);
		return rs;
	}

	// 用于更新订单信息(商家确认订单后)
	public Integer updateOrderStatus(Long delorderid) throws SQLException {
		String query = "UPDATE deliveryorder SET deliveryorder.status=2 WHERE deliveryorder.delOrderId =?";
		ArrayList<String> params = new ArrayList<>();
		params.add(delorderid.toString());
		int rs = this.executeUpdate(query, params);
		return rs;
	}

	// 用于更新订单信息(跑腿人抢单后)
	public Integer updateOrderAfterOrderGrabbed(Long delorderid, Long runnerid) {
		String sql = "select * from fivecrowdsourcing.deliveryorder where delOrderId = ? ;";
		ArrayList<String> params1 = new ArrayList<>();
		params1.add(delorderid.toString());
		ResultSet rs = this.executeQuery(sql, params1);
		try {
			while (rs.next()) {
				int status = rs.getInt("status");
				if (status == 3)
					return -1;// 该单已被抢走
				else {
					String query = "UPDATE deliveryorder SET deliveryorder.status=3, deliveryorder.runnerid = ? WHERE deliveryorder.delOrderId =?";
					ArrayList<String> params = new ArrayList<>();
					params.add(runnerid.toString());
					params.add(delorderid.toString());
					int result = this.executeUpdate(query, params);
					return result;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
		}
		return 0;
	}
	
	// 用于更新订单信息(跑腿人取货后)
	public void updateOrderAfterGoodGetted(Long delorderid, Long runnerid) {
		String sql = "UPDATE `fivecrowdsourcing`.`deliveryorder` SET `status`=4 "
				+ "WHERE `delOrderId`=?;";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1,delorderid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
