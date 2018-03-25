package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Merchant;
import com.entity.Runner;

public class RunnerDao extends BaseDao implements IRunnerDao {

	// ºÏ≤È≈‹Õ»»À√‹¬Î
	public Runner checkRunner(String phone, String password) {
		String sql = "SELECT * FROM fivecrowdsourcing.runner where phone=? AND password=?;";
		Runner runner = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				runner = new Runner();
				runner.setRunnerid(rs.getLong("runnerId"));
				runner.setPhone(rs.getString("phone"));
				runner.setName(rs.getString("name"));
				runner.setPassword(rs.getString("password"));
				runner.setIdcardnumber(rs.getString("idCardNumber"));
				runner.setPhotoofidcardinhand(rs.getString("photoOfIdCartInhand"));
				runner.setPhotoofidcardoppo(rs.getString("photoOfIdCardOppo"));
				runner.setPhotoofidcardposi(rs.getString("photoOfIdCardPosi"));
				runner.setPhotoofhealcert(rs.getString("photoOfHealCert"));
				runner.setBalance(rs.getDouble("balance"));
				runner.setIntegral(rs.getInt("integral"));
				runner.setMargin(rs.getLong("margin"));
				runner.setStatus(rs.getString("status"));
			}
			return runner;
		} catch (SQLException se) {
			se.printStackTrace();
			return runner;
		}
	}

	@Override
	public List<Runner> findRunners() {
		// TODO Auto-generated method stub
		List<Runner> result = new ArrayList<Runner>();
		String query = "select * from runner where status='1'";
		ResultSet rs = this.executeQuery(query, null);
		try {
			while (rs.next()) {
				Runner runner = new Runner();
				runner = new Runner();
				runner.setRunnerid(rs.getLong("runnerId"));
				runner.setName(rs.getString("name"));
				runner.setPassword(rs.getString("password"));
				runner.setIdcardnumber(rs.getString("idCardNumber"));
				runner.setPhone(rs.getString("phone"));
				runner.setPhotoofidcardinhand(rs.getString("photoofidcartinhand"));
				runner.setPhotoofidcardposi(rs.getString("photoofidcardposi"));
				runner.setPhotoofidcardoppo(rs.getString("photoofidcardoppo"));
				runner.setPhotoofhealcert(rs.getString("photoofhealcert"));
				runner.setMargin(rs.getLong("margin"));
				runner.setStatus(rs.getString("status"));
				result.add(runner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}

	@Override
	public Integer insertValidatedRunners(List<Runner> validatedRunners) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String query = "";
		ArrayList<String> params = new ArrayList<>();
		for (Runner runner : validatedRunners) {			
			params.add(runner.getRunnerid().toString());
			query = query
					+ "UPDATE `fivecrowdsourcing`.`runner` SET `status`='2' WHERE `runnerId`=?;\n";
		}

		int rs = this.executeUpdate(query, params);
		return rs;
	}

	public Runner getRunnerById(Long runnerid) {
		String query = "select * from runner where runnerId = " + runnerid;
		ResultSet rs = this.executeQuery(query, null);
		Runner runner = new Runner();
		try {
			while (rs.next()) {
				runner = new Runner();
				runner.setRunnerid(rs.getLong("runnerId"));
				runner.setName(rs.getString("name"));
				runner.setPassword(rs.getString("password"));
				runner.setIdcardnumber(rs.getString("idCardNumber"));
				runner.setPhone(rs.getString("phone"));
				runner.setPhotoofidcardinhand(rs.getString("photoofidcartinhand"));
				runner.setPhotoofidcardposi(rs.getString("photoofidcardposi"));
				runner.setPhotoofidcardoppo(rs.getString("photoofidcardoppo"));
				runner.setPhotoofhealcert(rs.getString("photoofhealcert"));
				runner.setMargin(rs.getLong("margin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return runner;
	}

	public boolean updateRunner(Runner runner) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `fivecrowdsourcing`.`runner` SET `phone`=?, "
				+ "`name`=?, `idCardNumber`=?, `photoOfIdCardOppo`=?, "
				+ "`photoOfIdCardPosi`=?, `photoOfHealCert`=?,"
				+ " `status`='1' WHERE `runnerId`=?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, runner.getPhone());
			pstmt.setString(2, runner.getName());
			pstmt.setString(3, runner.getIdcardnumber());
			pstmt.setString(4, runner.getPhotoofidcardoppo());
			pstmt.setString(5, runner.getPhotoofidcardposi());
			pstmt.setString(6, runner.getPhotoofhealcert());
			pstmt.setLong(7, runner.getRunnerid());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer insertARunner(Runner runner) {
		// TODO Auto-generated method stub
		String sql = "insert into runner(phone,password,status,balance,integral) values(?,?,0,0,100);";

		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, runner.getPhone());
			pstmt.setString(2, runner.getPassword());
			Integer rs = pstmt.executeUpdate();
			return rs;
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}

	
	public String getRunnerStatus(long runnerId) {
		// TODO Auto-generated method stub
		String status=null;
		 String sql="select status from Runner where runnerId=?";
		   try (Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setLong(1, runnerId);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					status=rs.getString("status");
				}			
			} catch (SQLException se) {
				se.printStackTrace();
			}
		return status;
 }
}
