package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Runner;

public class RunnerDao extends BaseDao implements IRunnerDao {


	@Override
	public List<Runner> findRunners() {
		// TODO Auto-generated method stub
		List<Runner> result = new ArrayList<Runner>();
		String query = "select * from runner";
		ResultSet rs = this.executeQuery(query, null);
		try {
			while (rs.next()) {
				Runner runner = new Runner();
				runner = new Runner();
				runner.setRunnerid(rs.getLong("runnerId"));
				runner.setName(rs.getString("name"));
				runner.setIdcardnumber(rs.getString("idCardNumber"));
				runner.setPhone(rs.getString("phone"));
				runner.setPhotoofidcartinhand(rs.getString("photoofidcartinhand"));
				runner.setPhotoofidcardposi(rs.getString("photoofidcardposi"));
				runner.setPhotoofidcardoppo(rs.getString("photoofidcardoppo"));
				runner.setPhotoofhealcert(rs.getString("photoofhealcert"));
				runner.setMargin(rs.getLong("margin"));
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
					params.add(runner.getPhone());
					params.add(runner.getName());
					params.add(runner.getIdcardnumber());
					params.add(runner.getPhotoofidcartinhand());
					params.add(runner.getPhotoofidcardoppo());
					params.add(runner.getPhotoofidcardposi());
					params.add(runner.getMargin().toString());
					params.add(runner.getPhotoofhealcert());
					query = query
							+ " INSERT INTO validatedrunner(phone,name,idCardNumber,photoOfIdCartInhand,photoOfIdCardOppo,photoOfIdCardPosi,photoOfHealCert,margin) VALUES(?,?,?,?,?,?,?,?);";
					// +" delete from runner where runnerId=?;";
				}

				int rs = this.executeUpdate(query, params);
				return rs;
	}

	@Override
	public Integer deleteRunnersfromTemp(List<Runner> validatedRunners) {
		// TODO Auto-generated method stub
		// TODO 从暂时的列表里删除runner信息
				String query = "";
				ArrayList<String> params = new ArrayList<>();
				for (Runner runner : validatedRunners) {
					params.add(runner.getRunnerid().toString());
					query = query + "  delete from runner where runnerId=?;";
				}

				int rs = this.executeUpdate(query, params);
				return rs;
			}
	}


