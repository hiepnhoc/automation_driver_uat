package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

@Getter
public class LeadDetailsAppInfoWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    //region LEFT TAB CONTROL
    WebElement customerMainChildTabs_employment_tab;
    //endregion


    String selectBoxGender = "genderType_new_chzn";
    String selectOneGender = "dropbox";
    String firstName = "firstName";
    String middleName = "middleName";
    String lastName = "lastName";
    String dob = "dob";
    String placeOfIssue = "placeOfBirth";
    WebElement maritalStatus;
    WebElement citizenship;
    WebElement education;
    WebElement btnProceed;
    boolean checkBtnProceedHide;

    WebElement expandIdentification;
    WebElement expandAddress;
    WebElement expandFamily;
    WebElement loadCust_comm;
    WebElement btnPriAdd;
    WebElement btnCheckDupde;
    WebElement btnSavePI;
    WebElement appInfo;

    @FindBy(how = How.ID, using = "emailAddress0")
    @CacheLookup
    private WebElement primaryEmailElement;

    public LeadDetailsAppInfoWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    //region SELECT GENDER
    public WebElement getSelectBoxGender(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,selectBoxGender,stage,value);
    }
    public WebElement getSelectOneGender(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='genderType_new_chzn']//li[contains(@class, 'active-result') and text() = '" + value + "']",stage,value);
    }
    //endregion


    public WebElement getFirstName(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,firstName,stage,value);
    }

    public WebElement getMiddleName(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,middleName,stage,value);
    }

    public WebElement getLastName(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,lastName,stage,value);
    }

    //format dd/MM/yyyy
    public WebElement getDob(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,dob,stage,value);
    }

    public WebElement getPlaceOfIssue(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,placeOfIssue,stage,value);
    }

    public WebElement getMaritalStatus(String stage, String MaritalVal) {
        Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "maritalStatus_chzn", stage, MaritalVal)).click();
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='maritalStatus_chzn']//li[contains(@class, 'active-result') and text() = '" + MaritalVal + "']",stage,MaritalVal);
    }

    public WebElement getCitizenship(String stage, String value) {
        Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "nationality_chzn", stage, value)).click();
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='nationality_chzn']//li[contains(@class, 'active-result') and text() = 'Vietnamese']",stage,value);
    }

    public WebElement getEducation(String stage, String educationVal) {
        Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "category_chzn", stage, educationVal)).click();
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='category_chzn']//li[contains(@class, 'active-result') and text() = '" + educationVal + "']",stage,educationVal);
    }

    public WebElement getBtnProceed(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,"personInfo_proceed",stage,value);
    }

    public boolean getCheckBtnProceedHide(String stage, String value) {
        return SeleniumUtils.booleanCheckById(driver,customerErrorResponse,"personInfo_proceed",stage,value);
    }

    public WebElement getExpandIdentification(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,"loadIdentification",stage,value);
    }

    public void inputIdentification(String stage, List<List> test) {

        WebDriverWait wait = new WebDriverWait(driver,10);

        for(int i= 0; i < test.size(); i++) {
            Objects.requireNonNull(SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id=\"idDetail_identificationType" + i + "\"]/option[text() = '" + test.get(i).get(0).toString() + "']",
                    stage, test.get(i).get(0).toString().trim())).click();

            Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "idDetail_identificationNumber" + i, stage, test.get(i).get(1).toString())).sendKeys(test.get(i).get(1).toString());

            Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "idDetail_issueDate" + i, stage, test.get(i).get(2).toString())).sendKeys(test.get(i).get(2).toString());

            Objects.requireNonNull(SeleniumUtils.findByID(driver, customerErrorResponse, "idDetail_country" + i, stage, "dropbox")).click();

            Objects.requireNonNull(SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id=\"idDetail_country" + i + "\"]/option[text() = '" + test.get(i).get(3).toString() + "']",
                    stage, test.get(i).get(3).toString())).click();


            if(i < test.size()-1) {
                Objects.requireNonNull(SeleniumUtils.clickableByXpath(driver, customerErrorResponse, "//*[@id=\"identificationDetailsForm\"]/input[1]",
                        stage, "btnAddIdentification")).click();

            }
        }


    }

    public WebElement getExpandAddress(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"loadaddress",stage,value);
    }

    public void inputAddress(String stage, List<List> test) {

        for(int i=0;i<test.size();i++) {
            WebDriverWait wait = new WebDriverWait(driver,10);

            SeleniumUtils.findByID(driver,customerErrorResponse,"phoneNumberList1_phoneNumber",stage,test.get(i).get(13).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"addressType_chzn",stage, "click address").click();
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id='addressType_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(0) + "']", stage, test.get(i).get(0).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"text_country_chzn",stage, "click text country").click();
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id='text_country_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(1) + "']", stage, test.get(i).get(1).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"state_country_chzn",stage, "click state country").click();
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id='state_country_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(2) + "']", stage, test.get(i).get(2).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"city_country_chzn",stage, "click city country").click();
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id=\"city_country_chzn\"]/div/div/input", stage, test.get(i).get(3).toString()).sendKeys(test.get(i).get(3).toString());
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id=\"city_country_chzn\"]/div/div/input", stage, test.get(i).get(3).toString()).sendKeys(Keys.ENTER);
//            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id='city_country_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(3) + "']", stage, test.get(i).get(3).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"Text_postalCode_country",stage, "postalCode").sendKeys("%%%");
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id=\"listitem_postalCode_country0a\"]/div/div", stage, "default value").click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"area_country_chzn",stage, "click area country").click();
            SeleniumUtils.findByXpath(driver, customerErrorResponse, "//*[@id='area_country_chzn']//li[contains(@class, 'active-result') and text() = \"" + test.get(i).get(4).toString() + "\"]", stage, test.get(i).get(4).toString()).click();

            SeleniumUtils.findByID(driver,customerErrorResponse,"address1ToBeAddedInput_country",stage,test.get(i).get(5).toString()).sendKeys(test.get(i).get(5).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"address2ToBeAdded_country",stage,test.get(i).get(6).toString()).sendKeys(test.get(i).get(6).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"address3ToBeAdded_country",stage,test.get(i).get(7).toString()).sendKeys(test.get(i).get(7).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"address_landmark",stage,test.get(i).get(8).toString()).sendKeys(test.get(i).get(8).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"address_noOfYearsAtCurrentAdress",stage,test.get(i).get(9).toString()).sendKeys(test.get(i).get(9).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"address_noOfMonthsAtCurrentAdress",stage,test.get(i).get(10).toString()).sendKeys(test.get(i).get(10).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"stdCode_phoneNumberList_new1",stage,test.get(i).get(11).toString()).sendKeys(test.get(i).get(11).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"phoneNumber_phoneNumberList_new1",stage,test.get(i).get(12).toString()).sendKeys(test.get(i).get(12).toString());
            SeleniumUtils.findByID(driver,customerErrorResponse,"phoneNumberList1_phoneNumber",stage,test.get(i).get(13).toString()).sendKeys(test.get(i).get(13).toString());

            //SeleniumUtils.captureScreenshot(driver,customerErrorResponse);
            SeleniumUtils.findByID(driver,customerErrorResponse,"save_cust_address",stage,"save_address").click();

            if(i<test.size()-1) {

                //System.out.println("Test");
                //System.out.println(i);
                try {
                    Thread.sleep(500);
                    SeleniumUtils.clickableById(driver,customerErrorResponse,"create_new",stage,"create_new").click();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }




    }

    public WebElement getExpandFamily(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"loadFamily",stage,value);
    }

    public WebElement getLoadCust_comm(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"loadCust_comm",stage,value);
    }

    public WebElement getBtnPriAdd(String stage, String value) {
        return SeleniumUtils.clickableByXpath(driver,customerErrorResponse,"//tr[td[4]=\"Current Address\"]/td[7]/input",stage,value);
    }

    public void inputFamily(List<List> test, String noOfDependents, String noOfChildren, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,10);

        //SeleniumUtils.findByID(driver,customerErrorResponse,"noOfDependents",stage,value).clear();
        //System.out.println("Before Clicked: " + SeleniumUtils.clickableById(driver,customerErrorResponse,"loadFamily",stage,value));
        SeleniumUtils.findByID(driver,customerErrorResponse,"loadFamily",stage,value).click();

//        SeleniumUtils.clickableById(driver,customerErrorResponse,"loadFamily",stage,value).click();


        boolean checkFormFamilyExpand = SeleniumUtils.booleanCheckById(driver,customerErrorResponse,"address",stage,value);
        //System.out.println(checkFormFamilyExpand);
//        if (checkFormFamilyExpand) {
            SeleniumUtils.findByID(driver,customerErrorResponse,"noOfDependents",stage,value).sendKeys(noOfDependents);
            SeleniumUtils.findByID(driver,customerErrorResponse,"noOfChildren",stage,value).sendKeys(noOfChildren);

            for(int i= 0; i < test.size(); i++) {
                SeleniumUtils.findByID(driver,customerErrorResponse,"fmDetail_memberName" + i,stage,test.get(i).get(0).toString()).sendKeys(test.get(i).get(0).toString());
                SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"fmDetail_relationshipType" + i + "\"]/option[text() = '" + test.get(i).get(1).toString() + "']",stage,test.get(i).get(1).toString()).click();
                SeleniumUtils.findByID(driver,customerErrorResponse,"fmDetail_phoneNumber" + i,stage,test.get(i).get(2).toString()).sendKeys(test.get(i).get(2).toString());
                if(test.get(i).get(3).toString() != "") {
                    SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"fmDetail_eduStatus" + i + "\"]/option[text() = '" + test.get(i).get(3).toString() + "']",stage,test.get(i).get(3).toString()).click();
                }
                SeleniumUtils.findByID(driver,customerErrorResponse,"fmDetail_occupation" + i,stage,test.get(i).get(4).toString()).sendKeys(test.get(i).get(4).toString());

                if(i < test.size()-1) {
                    SeleniumUtils.clickableByXpath(driver,customerErrorResponse,"//*[@id=\"familyDetailForm\"]/input[1]",stage,"Click to add");
                }
            }
//        }


    }

    public WebElement getBtnCheckDupde(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"dedupeCheckButton",stage,value);
    }

    public WebElement getBtnSavePI(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"saveAndNextCustFormButton1",stage,value);
    }

    public WebElement getCustomerMainChildTabs_employment_tab(String stage, String value) {
        return SeleniumUtils.clickableById(driver,customerErrorResponse,"customerMainChildTabs_employment_tab",stage,value);
    }

    public WebElement getAppInfo(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"applicantIdHeader\"]/span",stage,value);
    }

    public void btnMiscFpt(String stage, String value) {
        SeleniumUtils.clickableById(driver,customerErrorResponse,"applicationChildTabs_miscDynamicForm_1",stage,value).click();
    }

    public void btnLoanDetails(String stage, String value) {
        SeleniumUtils.clickableById(driver,customerErrorResponse,"applicationChildTabs_loanInfo",stage,value).click();
    }

    public void btnReferences(String stage, String value) {
        SeleniumUtils.clickableById(driver,customerErrorResponse,"applicationChildTabs_referencesInfo",stage,value).click();
    }

    public void btnMiscAppDtl(String stage, String value) {
        SeleniumUtils.clickableById(driver,customerErrorResponse,"applicationChildTabs_miscDynamicForm_0",stage,value).click();
    }

}
