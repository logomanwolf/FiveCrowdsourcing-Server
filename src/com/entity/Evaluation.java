package com.entity;

public class Evaluation {
  private Long delorderid;//��Ӧ�����͵�id
  private Long deltime;//����ʱ������
  private Long delquality;//������������
  private Long delattitude;//����̬������
  private String content;//��������

  public Long getDelorderid() {
    return delorderid;
  }

  public void setDelorderid(Long delorderid) {
    this.delorderid = delorderid;
  }

  public Long getDeltime() {
    return deltime;
  }

  public void setDeltime(Long deltime) {
    this.deltime = deltime;
  }

  public Long getDelquality() {
    return delquality;
  }

  public void setDelquality(Long delquality) {
    this.delquality = delquality;
  }

  public Long getDelattitude() {
    return delattitude;
  }

  public void setDelattitude(Long delattitude) {
    this.delattitude = delattitude;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
