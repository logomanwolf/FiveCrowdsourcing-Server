package com.dao;
import java.util.List;

import com.entity.Merchant;

public interface IMerchantDao {
    public int addMerchant(Merchant merchant);
    public List<Merchant> findMerchants();
    public Merchant findMerchantById(String merchantId);
    public List<Merchant> findMerchantsByDept(String dept);
    public List<Merchant> findMerchantsByRole(String role);
    public int delMerchantById(int id);
    public int updateMerchantById(int id,Merchant role);
    public boolean checkMerchant(String name);
}