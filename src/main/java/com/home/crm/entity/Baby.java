package com.home.crm.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @Author: xu.dm
 * @Date: 2018/7/17 21:56
 * @Description:
 */

@Entity
public class Baby {
    @Id
    @GenericGenerator(name = "generator",strategy = "native")
    @GeneratedValue(generator = "generator")
    private Long babyId;

    @Column(nullable = false)
    private Long customerId;//对应Customer表

    @Column(nullable = false)
    private String babyName;//宝宝姓名

    private String babyBornName;//乳名

    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate babyBirthday;//宝宝生日

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime babyBornTime;//出生时间

    private Integer babyCount;//胞胎数

    private Integer bornWeight;//出生体重g

    private Integer bornHeight;//出生身高cm

    private String babyBloodType;//血型

    private Integer inRoomWeight;//入室体重

    private Integer outRoomWeight;//离室体重

    private String eatStatus;//饮食情况

    private String inRoomStatus;//入室异常情况

    public Long getBabyId() {
        return babyId;
    }

    public void setBabyId(Long babyId) {
        this.babyId = babyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabyBornName() {
        return babyBornName;
    }

    public void setBabyBornName(String babyBornName) {
        this.babyBornName = babyBornName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(LocalDate babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public LocalDateTime getBabyBornTime() {
        return babyBornTime;
    }

    public void setBabyBornTime(LocalDateTime babyBornTime) {
        this.babyBornTime = babyBornTime;
    }

    public Integer getBabyCount() {
        return babyCount;
    }

    public void setBabyCount(Integer babyCount) {
        this.babyCount = babyCount;
    }

    public Integer getBornWeight() {
        return bornWeight;
    }

    public void setBornWeight(Integer bornWeight) {
        this.bornWeight = bornWeight;
    }

    public Integer getBornHeight() {
        return bornHeight;
    }

    public void setBornHeight(Integer bornHeight) {
        this.bornHeight = bornHeight;
    }

    public String getBabyBloodType() {
        return babyBloodType;
    }

    public void setBabyBloodType(String babyBloodType) {
        this.babyBloodType = babyBloodType;
    }

    public Integer getInRoomWeight() {
        return inRoomWeight;
    }

    public void setInRoomWeight(Integer inRoomWeight) {
        this.inRoomWeight = inRoomWeight;
    }

    public Integer getOutRoomWeight() {
        return outRoomWeight;
    }

    public void setOutRoomWeight(Integer outRoomWeight) {
        this.outRoomWeight = outRoomWeight;
    }

    public String getEatStatus() {
        return eatStatus;
    }

    public void setEatStatus(String eatStatus) {
        this.eatStatus = eatStatus;
    }

    public String getInRoomStatus() {
        return inRoomStatus;
    }

    public void setInRoomStatus(String inRoomStatus) {
        this.inRoomStatus = inRoomStatus;
    }
}
