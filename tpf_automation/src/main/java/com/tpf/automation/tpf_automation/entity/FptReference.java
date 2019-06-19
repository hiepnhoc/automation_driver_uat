package com.tpf.automation.tpf_automation.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class FptReference {

    //region VARIABLE DECLARATION
    private String fullName;

    private String phoneNumber;

    private String relation;

    private String personalId;
    //endregion

    //region CONSTRUCTOR
    public FptReference() {
    }
    //endregion

    //region GETTER AND SETTER
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
    //endregion
}
