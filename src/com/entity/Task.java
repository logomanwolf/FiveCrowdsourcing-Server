package com.entity;

public class Task {
  private Long taskid;//����id
  private Long merchantid;//�̻�id
  private Long delmethodid;//���ͷ�ʽid
  private Long estimatedtime;//Ԥ��ʱ��
  private Double estimatedtotalprice;//Ԥ���ܼ۸�
  private String ordertime;//�µ�ʱ��

  public Long getTaskid() {
    return taskid;
  }

  public void setTaskid(Long taskid) {
    this.taskid = taskid;
  }

  public Long getMerchantid() {
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
}
