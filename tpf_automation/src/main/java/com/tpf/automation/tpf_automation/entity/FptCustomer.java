package com.tpf.automation.tpf_automation.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FptCustomer {


    //region VARIABLE DECLARATION
    private String custId;
    //private String accaNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private String dateOfBirth;
    private String nationalId;
    private String issueDate;
    private String issuePlace;
    private String employeeCard;
    private String mobilePhone;
    private String durationYear;
    private String durationMonth;
    private String map;
    private String maritalStatus;
    private String ownerNationalId;
    private String contactAddress;
    private String dsaCode;
    private String product;
    private Long salary;
    private FptLoanDetail loanDetail;
    private List<FptReference> references;
    private List<FptAddress> addresses;
    private List<FptProductDetail> productDetails;
    private List<FptPhoto> photos;
    //endregion

    //region CONSTRUCTION
    public FptCustomer() {
    }
    //endregion

    //region GETTER AND SETTER

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    /*public String getAccaNumber() {
        return accaNumber;
    }

    public void setAccaNumber(String accaNumber) {
        this.accaNumber = accaNumber;
    }*/

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issueplace) {
        this.issuePlace = issueplace;
    }

    public String getEmployeeCard() {
        return employeeCard;
    }

    public void setEmployeeCard(String employeeCard) {
        this.employeeCard = employeeCard;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDurationYear() {
        return durationYear;
    }

    public void setDurationYear(String durationYear) {
        this.durationYear = durationYear;
    }

    public String getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(String durationMonth) {
        this.durationMonth = durationMonth;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOwnerNationalId() {
        return ownerNationalId;
    }

    public void setOwnerNationalId(String ownerNationalId) {
        this.ownerNationalId = ownerNationalId;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getDsaCode() {
        return dsaCode;
    }

    public void setDsaCode(String dsaCode) {
        this.dsaCode = dsaCode;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public FptLoanDetail getLoanDetail() {
        return loanDetail;
    }

    public void setLoanDetail(FptLoanDetail loanDetail) {
        this.loanDetail = loanDetail;
    }

    public List<FptReference> getReferences() {
        return references;
    }

    public void setReferences(List<FptReference> references) {
        this.references = references;
    }

    public List<FptAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<FptAddress> addresses) {
        this.addresses = addresses;
    }

    public List<FptProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<FptProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public List<FptPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FptPhoto> photos) {
        this.photos = photos;
    }
    //endregion
}
