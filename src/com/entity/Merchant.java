package com.entity;

import java.io.Serializable;

public class Merchant implements Serializable{
  private Long merchantid;//�̻�id
  private Long tofgid;//�������������id
  private String name;//��ϵ������
  private String idcardnumber;//��ϵ�����֤��
  private String idcardphoto;//��ϵ�����֤��Ƭ�洢��ַ
  private String password;//����
  private String storename;//������
  private String phone;//�绰
  private String address;//��ַ
  private String buslicensephoto;//����Ӫҵִ�մ洢��ַ
  private String foodbuslicensephoto;//ʳƷ��Ӫ���֤�洢��ַ
  private Long margin;//��֤���ύ״̬��1���ύ��2��δ�ύ
  private Double longitude;//����
  private Double latitude;//γ��
  private Double balance;//���
  public Long getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(Long merchantid) {
    this.merchantid = merchantid;
  }

  public Long getTofgid() {
    return tofgid;
  }

  public void setTofgid(Long tofgid) {
    this.tofgid = tofgid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdcardnumber() {
    return idcardnumber;
  }

  public void setIdcardnumber(String idcardnumber) {
    this.idcardnumber = idcardnumber;
  }

  public String getIdcardphoto() {
    return idcardphoto;
  }

  public void setIdcardphoto(String idcardphoto) {
    this.idcardphoto = idcardphoto;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStorename() {
    return storename;
  }

  public void setStorename(String storename) {
    this.storename = storename;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBuslicensephoto() {
    return buslicensephoto;
  }

  public void setBuslicensephoto(String buslicensephoto) {
    this.buslicensephoto = buslicensephoto;
  }

  public String getFoodbuslicensephoto() {
    return foodbuslicensephoto;
  }

  public void setFoodbuslicensephoto(String foodbuslicensephoto) {
    this.foodbuslicensephoto = foodbuslicensephoto;
  }

  public Long getMargin() {
    return margin;
  }

  public void setMargin(Long margin) {
    this.margin = margin;
  }

public Double getLongitude() {
	return longitude;
}

public void setLongitude(Double longitude) {
	this.longitude = longitude;
}

public Double getLatitude() {
	return latitude;
}

public void setLatitude(Double latitude) {
	this.latitude = latitude;
}

public Double getBalance() {
	return balance;
}

public void setBalance(Double balance) {
	this.balance = balance;
}
  
}
