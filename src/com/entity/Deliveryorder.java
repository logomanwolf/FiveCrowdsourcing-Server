package com.entity;

public class Deliveryorder {
  private Long delorderid;//配送单id
  private Long merchantid;//商户id
  private Long delmethodid;//配送方式id
  private Long estimatedtime;//预估时间
  private Double estimatedtotalprice;//预估总价格
  private String ordertime;//下单时间
  private Long runnerid;//跑腿人id
  private String intime;//接单时间
  private String outtime;//送达时间
  private Double extraprice;//加价费
  private Double trueweight;//真实质量
  private int creditpoints;//信用分
  private int integral;//积分

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
}
