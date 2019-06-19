package com.tpf.automation.tpf_automation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class FptLoanDetail {

    //region VARIABLE DECLARATION
    private String product;
    private String loanAmount;
    private String downPayment;
    private String annualr;
    private String dueDate;
    private String tenor;
    //private String EMI;
    //private String loandId;
    //endregion

    //region CONSTRUCTION
    public FptLoanDetail() {
    }
    //endregion

    //region GETTER AND SETTER
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getAnnualr() {
        return annualr;
    }

    public void setAnnualr(String annualr) {
        this.annualr = annualr;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }
}
