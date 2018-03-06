package com.entity;

public class Runner {
  private Long runnerid;//跑腿人id
  private String phone;//电话
  private String name;//姓名
  private String idcardnumber;//身份证号
  private String photoofidcartinhand;//手持身份证照存储地址
  private String photoofidcardoppo;//身份证反面照存储地址
  private String photoofidcardposi;//身份证正面照存储地址
  private String photoofhealcert;//健康证照片存储地址
  private Long margin;//保证金提交状态：1：提交；2：未提交

  public Long getRunnerid() {
    return runnerid;
  }

  public void setRunnerid(Long runnerid) {
    this.runnerid = runnerid;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public String getPhotoofidcartinhand() {
    return photoofidcartinhand;
  }

  public void setPhotoofidcartinhand(String photoofidcartinhand) {
    this.photoofidcartinhand = photoofidcartinhand;
  }

  public String getPhotoofidcardoppo() {
    return photoofidcardoppo;
  }

  public void setPhotoofidcardoppo(String photoofidcardoppo) {
    this.photoofidcardoppo = photoofidcardoppo;
  }

  public String getPhotoofidcardposi() {
    return photoofidcardposi;
  }

  public void setPhotoofidcardposi(String photoofidcardposi) {
    this.photoofidcardposi = photoofidcardposi;
  }

  public String getPhotoofhealcert() {
    return photoofhealcert;
  }

  public void setPhotoofhealcert(String photoofhealcert) {
    this.photoofhealcert = photoofhealcert;
  }

  public Long getMargin() {
    return margin;
  }

  public void setMargin(Long margin) {
    this.margin = margin;
  }
}
