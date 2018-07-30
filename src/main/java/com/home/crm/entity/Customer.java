package com.home.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @Author: xu.dm
 * @Date: 2018/6/10 17:14
 * @Description:
 */
@Entity
public class Customer {
    @Id
    @GenericGenerator(name="generator",strategy = "native")
    @GeneratedValue(generator = "generator")
    private Long customerId;

    @Column(nullable = false)
    private String filesNo; //档案号

    @Column(nullable = false)
    @NotBlank(message ="姓名不能为空")
    private String customerName;//客户姓名

    @Column(nullable = false)
    @NotBlank(message ="入住房号不能为空")
    private String inRoomNum;//入住房号

    @Column(nullable = false)
    private String agreementNum;//入住协议编号

    @Column(nullable = false)
    private Double agreementMoney;//签单金额

    private String roomType;//房型

    private Integer agreementInDays;//协议入住天数


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime inDate;//入室时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime outDate; //离室时间

    private Integer actualInDays;//实际入住天数

    private String PhoneNum;//手机号

    @Email
    private String email;//电子邮箱

    private String qqNum;//qq号或者微信号

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday; //生日

    private String idCard;//身份证

    private String idCardAddr;//身份证上的地址

    private String nativePlace;//籍贯

    private String addr;//住址

    private String workUnit;//工作单位

    private String profession;//职业

    private String marriage;//婚姻状况

    private String nation;//民族

    private String religion;//宗教

    private String education;//文化程度


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInRoomNum() {
        return inRoomNum;
    }

    public void setInRoomNum(String inRoomNum) {
        this.inRoomNum = inRoomNum;
    }

    public String getAgreementNum() {
        return agreementNum;
    }

    public void setAgreementNum(String agreementNum) {
        this.agreementNum = agreementNum;
    }

    public Double getAgreementMoney() {
        return agreementMoney;
    }

    public void setAgreementMoney(Double agreementMoney) {
        this.agreementMoney = agreementMoney;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getAgreementInDays() {
        return agreementInDays;
    }

    public void setAgreementInDays(Integer agreementInDays) {
        this.agreementInDays = agreementInDays;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateTime inDate) {
        this.inDate = inDate;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public Integer getActualInDays() {
        return actualInDays;
    }

    public void setActualInDays(Integer actualInDays) {
        this.actualInDays = actualInDays;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardAddr() {
        return idCardAddr;
    }

    public void setIdCardAddr(String idCardAddr) {
        this.idCardAddr = idCardAddr;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
