package com.entity;

public class Deliveryorder {
  private Long delorderid;//���͵�id
  private Long merchantid;//�̻�id
  private Long delmethodid;//���ͷ�ʽid
  private Long estimatedtime;//Ԥ��ʱ��
  private Double estimatedtotalprice;//Ԥ���ܼ۸�
  private int distance;//����
  private String ordertime;//�µ�ʱ��
  private Long runnerid;//������id
  private String intime;//�ӵ�ʱ��
  private String outtime;//�ʹ�ʱ��
  private Double extraprice;//�Ӽ۷�
  private Double trueweight;//��ʵ����
  private int creditpoints;//���÷�
  private int integral;//����
  private String cusName;//�ͻ���
  private String cusPhone;//�ͻ��绰
  private String cusAddress;//�ͻ���ַ
  private String things;//����
  private int status;//״̬��1���½���2����������3����ȡ����4�������У�5���������

  public Long getDelorderid() {
    return delorderid;
  }

  public void setDelorderid(Long delorderid) {
    this.delorderid = delorderid;
  }

  public Long getMerchantid(){
	    return merchantid;
  }

  public void setMerchantid(Long merchantid) {
    this.merchantid = merchantid;
  }

  public Long getDelmethodid() {
    return delmethodid;
  }

  public void setDelmethodid(Long delmethodid) {
    this.delmethodid = delmethodid;
  }

  public Long getEstimatedtime() {
    return estimatedtime;
  }

  public void setEstimatedtime(Long estimatedtime) {
    this.estimatedtime = estimatedtime;
  }

  public Double getEstimatedtotalprice() {
    return estimatedtotalprice;
  }

  public void setEstimatedtotalprice(Double estimatedtotalprice) {
    this.estimatedtotalprice = estimatedtotalprice;
  }

  public int getDistance() {
	return distance;
}

public void setDistance(int distance) {
	this.distance = distance;
}

public String getOrdertime() {
    return ordertime;
  }

  public void setOrdertime(String ordertime) {
    this.ordertime = ordertime;
  }

  public Long getRunnerid() {
    return runnerid;
  }

  public void setRunnerid(Long runnerid) {
    this.runnerid = runnerid;
  }

  public String getIntime() {
    return intime;
  }

  public void setIntime(String intime) {
    this.intime = intime;
  }

  public String getOuttime() {
    return outtime;
  }

  public void setOuttime(String outtime) {
    this.outtime = outtime;
  }

  public Double getExtraprice() {
    return extraprice;
  }

  public void setExtraprice(Double extraprice) {
    this.extraprice = extraprice;
  }

  public Double getTrueweight() {
    return trueweight;
  }

  public void setTrueweight(Double trueweight) {
    this.trueweight = trueweight;
  }

  public int getCreditpoints() {
    return creditpoints;
  }

  public void setCreditpoints(int creditpoints) {
    this.creditpoints = creditpoints;
  }

  public int getIntegral() {
    return integral;
  }

  public void setIntegral(int integral) {
    this.integral = integral;
  }

  public String getCusName() {
	  return cusName;
  }

  public void setCusName(String cusName) {
  	  this.cusName = cusName;
  }

  public String getCusPhone() {
	  return cusPhone;
  }

  public void setCusPhone(String cusPhone) {
	  this.cusPhone = cusPhone;
  }

  public String getCusAddress() {
	  return cusAddress;
  }

  public void setCusAddress(String cusAddress) {
	  this.cusAddress = cusAddress;
  }

  public String getThings() {
	  return things;
  }

  public void setThings(String things) {
	  this.things = things;
  }

  public int getStatus() {
	  return status;
  }

  public void setStatus(int status) {
	  this.status = status;
  }
}
