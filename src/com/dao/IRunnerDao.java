package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Runner;

public interface IRunnerDao {
    public List<Runner> findRunners();
    public Integer insertValidatedRunners(List<Runner> validatedRunners) throws SQLException;
    public Integer insertARunner(Runner runner);
}
