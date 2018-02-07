package com.entity;

public class Task {
  private Long taskid;//任务单id
  private Long merchantid;//商户id
  private Long delmethodid;//配送方式id
  private Long estimatedtime;//预估时间
  private Double estimatedtotalprice;//预估总价格
  private String ordertime;//下单时间

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
