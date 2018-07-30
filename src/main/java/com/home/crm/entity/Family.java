package com.home.crm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: xu.dm
 * @Date: 2018/7/17 22:29
 * @Description:
 */
@Entity
public class Family {
    @Id
    @GenericGenerator(name = "myGenerator",strategy = "native")
    @GeneratedValue(generator = "myGenerator")
    private Long familyId;

    @Column(nullable = false)
    private Long customerId;//对应Customer表id

    private String relation1; //家属1姓名
    private String relation1Be;//家属1关系
    private String relation1Phone;//联系电话

    private String relation2;
    private String relation2Be;
    private String relation2Phone;

    private String relation3;
    private String relation3Be;
    private String relation3Phone;

    private String relation4;
    private String relation4Be;
    private String relation4Phone;

    private String relation5;
    private String relation5Be;
    private String relation5Phone;

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1;
    }

    public String getRelation1Be() {
        return relation1Be;
    }

    public void setRelation1Be(String relation1Be) {
        this.relation1Be = relation1Be;
    }

    public String getRelation1Phone() {
        return relation1Phone;
    }

    public void setRelation1Phone(String relation1Phone) {
        this.relation1Phone = relation1Phone;
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2;
    }

    public String getRelation2Be() {
        return relation2Be;
    }

    public void setRelation2Be(String relation2Be) {
        this.relation2Be = relation2Be;
    }

    public String getRelation2Phone() {
        return relation2Phone;
    }

    public void setRelation2Phone(String relation2Phone) {
        this.relation2Phone = relation2Phone;
    }

    public String getRelation3() {
        return relation3;
    }

    public void setRelation3(String relation3) {
        this.relation3 = relation3;
    }

    public String getRelation3Be() {
        return relation3Be;
    }

    public void setRelation3Be(String relation3Be) {
        this.relation3Be = relation3Be;
    }

    public String getRelation3Phone() {
        return relation3Phone;
    }

    public void setRelation3Phone(String relation3Phone) {
        this.relation3Phone = relation3Phone;
    }

    public String getRelation4() {
        return relation4;
    }

    public void setRelation4(String relation4) {
        this.relation4 = relation4;
    }

    public String getRelation4Be() {
        return relation4Be;
    }

    public void setRelation4Be(String relation4Be) {
        this.relation4Be = relation4Be;
    }

    public String getRelation4Phone() {
        return relation4Phone;
    }

    public void setRelation4Phone(String relation4Phone) {
        this.relation4Phone = relation4Phone;
    }

    public String getRelation5() {
        return relation5;
    }

    public void setRelation5(String relation5) {
        this.relation5 = relation5;
    }

    public String getRelation5Be() {
        return relation5Be;
    }

    public void setRelation5Be(String relation5Be) {
        this.relation5Be = relation5Be;
    }

    public String getRelation5Phone() {
        return relation5Phone;
    }

    public void setRelation5Phone(String relation5Phone) {
        this.relation5Phone = relation5Phone;
    }
}
