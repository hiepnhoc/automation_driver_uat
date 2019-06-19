package com.tpf.automation.tpf_automation.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.omg.CORBA.UNKNOWN;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FptProductDetail {

    //region VARIABLE DECLARATION
    private String model;

    private String goodCode;

    private String goodType;

    private String quantity;

    private String goodPrice;
    //endregion

    //region CONSTRUCTION
    public FptProductDetail() {
    }
    //endregion

    //region GETTER AND SETTER
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    //endregion
}
