package com.entity;

public class Deliveryorder {
  private Long delorderid;//���͵�id
  private Long taskid;//����id
  private Long runnerid;//������id
  private String intime;//�ӵ�ʱ��
  private String outtime;//�ʹ�ʱ��
  private Double extraprice;//�Ӽ۷�
  private Double trueweight;//��ʵ����
  private int creditpoints;//���÷�
  private int integral;//����

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
