package com.dao;
import java.util.List;

import com.entity.Merchant;
import com.entity.Runner;
import com.entity.Typeofgoods;

public interface IMerchantDao {
    public int addMerchant(Merchant merchant);
    public List<Merchant> findMerchants();
    public Merchant findMerchantById(String merchantId);
    public List<Merchant> findMerchantsByDept(String dept);
    public List<Merchant> findMerchantsByRole(String role);
    public Typeofgoods findTypeOfGoodsNameById(Long tofgId);
    public int delMerchantById(int id);
    public int updateMerchantById(int id,Merchant role);
    public boolean checkMerchant(String name);
    public Integer insertValidatedMerchants(List<Merchant> validatedMerchants);
    public Integer deleteMerchantsfromTemp(List<Merchant> validatedMerchants);
    
}