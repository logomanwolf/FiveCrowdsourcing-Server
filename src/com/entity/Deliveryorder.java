package com.entity;

public class Deliveryorder {
  private Long delorderid;//配送单id
  private Long taskid;//任务单id
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

  public Long getTaskid() {
    return taskid;
  }

  public void setTaskid(Long taskid) {
    this.taskid = taskid;
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
