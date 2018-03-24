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
				// runner.setTofgid(rs.getLong("tofgId"));
				runner.setPhone(rs.getString("phone"));
				runner.setName(rs.getString("name"));
				runner.setPassword(rs.getString("password"));
				runner.setIdcardnumber(rs.getString("idCardNumber"));
				// runner.setAddress(rs.getString("address"));
				runner.setPhotoofidcardinhand(rs.getString("photoOfIdCardInhand"));
				runner.setPhotoofidcardoppo(rs.getString("photoOfIdCardOppo"));
				runner.setPhotoofidcardposi(rs.getString("photoOfIdCardPosi"));
				runner.setPhotoofhealcert(rs.getString("photoOfHealCert"));
				runner.setBalance(rs.getDouble("balance"));
				runner.setIntegral(rs.getInt("integral"));
				runner.setMargin(rs.getLong("margin"));
				runner.setStatus(rs.getString("status"));

				// runner.setStorename(rs.getString("storeName"));

				// runner.setBuslicensephoto(rs.getString("busLicensePhoto"));
				// runner.setFoodbuslicensephoto(rs
				// .getString("foodBusLicensePhoto"));
				// runner.setIdcardphoto(rs.getString("idCardPhoto"));

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
	public Integer insertValidatedRunners(List<Runner> validatedRunners) {
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
		String sql = "UPDATE `fivecrowdsourcing`.`validatedrunner` SET `balance`=?, "
				+ "`integral`=? WHERE `runnerId`=?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDouble(1, runner.getBalance());
			pstmt.setInt(2, runner.getIntegral());
			pstmt.setLong(3, runner.getRunnerid());
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
		String sql = "insert into runner(phone,password) values(?,?);";

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
}
