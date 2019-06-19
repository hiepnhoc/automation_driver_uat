
package com.tpf.automation.tpf_automation.entity.vin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address1",
    "address2",
    "agree1",
    "agree2",
    "agree3",
    "agree4",
    "amount",
    "city",
    "createDate",
    "dateOfBirth",
    "district",
    "dueDate",
    "email",
    "fee",
    "firstName",
    "gender",
    "insurrance",
    "issueDate",
    "issuePlace",
    "lastName",
    "loanTime",
    "maritalStatus",
    "middleName",
    "momoLoanId",
    "personalId",
    "phoneNumber",
    "photos",
    "postCode",
    "productCode",
    "references",
    "salary",
    "ward"
})
public class MomoData {

    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("agree1")
    private Boolean agree1;
    @JsonProperty("agree2")
    private Boolean agree2;
    @JsonProperty("agree3")
    private Boolean agree3;
    @JsonProperty("agree4")
    private Boolean agree4;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("city")
    private String city;
    @JsonProperty("createDate")
    private Double createDate;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("district")
    private String district;
    @JsonProperty("dueDate")
    private Integer dueDate;
    @JsonProperty("email")
    private String email;
    @JsonProperty("fee")
    private Integer fee;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("insurrance")
    private Boolean insurrance;
    @JsonProperty("issueDate")
    private String issueDate;
    @JsonProperty("issuePlace")
    private String issuePlace;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("loanTime")
    private Integer loanTime;
    @JsonProperty("maritalStatus")
    private String maritalStatus;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("momoLoanId")
    private Double momoLoanId;
    @JsonProperty("personalId")
    private String personalId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("photos")
    private List<MomoPhoto> photos = null;
    @JsonProperty("postCode")
    private Integer postCode;
    @JsonProperty("productCode")
    private String productCode;
    @JsonProperty("references")
    private List<MomoReference> references = null;
    @JsonProperty("salary")
    private Integer salary;
    @JsonProperty("ward")
    private String ward;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("address2")
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @JsonProperty("agree1")
    public Boolean getAgree1() {
        return agree1;
    }

    @JsonProperty("agree1")
    public void setAgree1(Boolean agree1) {
        this.agree1 = agree1;
    }

    @JsonProperty("agree2")
    public Boolean getAgree2() {
        return agree2;
    }

    @JsonProperty("agree2")
    public void setAgree2(Boolean agree2) {
        this.agree2 = agree2;
    }

    @JsonProperty("agree3")
    public Boolean getAgree3() {
        return agree3;
    }

    @JsonProperty("agree3")
    public void setAgree3(Boolean agree3) {
        this.agree3 = agree3;
    }

    @JsonProperty("agree4")
    public Boolean getAgree4() {
        return agree4;
    }

    @JsonProperty("agree4")
    public void setAgree4(Boolean agree4) {
        this.agree4 = agree4;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("createDate")
    public Double getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(Double createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("district")
    public String getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(String district) {
        this.district = district;
    }

    @JsonProperty("dueDate")
    public Integer getDueDate() {
        return dueDate;
    }

    @JsonProperty("dueDate")
    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("fee")
    public Integer getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("insurrance")
    public Boolean getInsurrance() {
        return insurrance;
    }

    @JsonProperty("insurrance")
    public void setInsurrance(Boolean insurrance) {
        this.insurrance = insurrance;
    }

    @JsonProperty("issueDate")
    public String getIssueDate() {
        return issueDate;
    }

    @JsonProperty("issueDate")
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @JsonProperty("issuePlace")
    public String getIssuePlace() {
        return issuePlace;
    }

    @JsonProperty("issuePlace")
    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("loanTime")
    public Integer getLoanTime() {
        return loanTime;
    }

    @JsonProperty("loanTime")
    public void setLoanTime(Integer loanTime) {
        this.loanTime = loanTime;
    }

    @JsonProperty("maritalStatus")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    @JsonProperty("maritalStatus")
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("momoLoanId")
    public Double getMomoLoanId() {
        return momoLoanId;
    }

    @JsonProperty("momoLoanId")
    public void setMomoLoanId(Double momoLoanId) {
        this.momoLoanId = momoLoanId;
    }

    @JsonProperty("personalId")
    public String getPersonalId() {
        return personalId;
    }

    @JsonProperty("personalId")
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("photos")
    public List<MomoPhoto> getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(List<MomoPhoto> photos) {
        this.photos = photos;
    }

    @JsonProperty("postCode")
    public Integer getPostCode() {
        return postCode;
    }

    @JsonProperty("postCode")
    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    @JsonProperty("productCode")
    public String getProductCode() {
        return productCode;
    }

    @JsonProperty("productCode")
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("references")
    public List<MomoReference> getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(List<MomoReference> references) {
        this.references = references;
    }

    @JsonProperty("salary")
    public Integer getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @JsonProperty("ward")
    public String getWard() {
        return ward;
    }

    @JsonProperty("ward")
    public void setWard(String ward) {
        this.ward = ward;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
