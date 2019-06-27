package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadDetailsEmpDetailsWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement appInfo;
    WebElement occupationType_chzn;
    WebElement Text_employerName; //company tax code



    public LeadDetailsEmpDetailsWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public void getOccupationType_chzn(String stage, String value) {
        SeleniumUtils.findByID(driver,customerErrorResponse,"occupationType_chzn",stage ,value).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='occupationType_chzn']//li[contains(@class, 'active-result') and text() = '" + value + "']",stage ,value).click();
    }

    public void getNatureOfOccupationType_chzn(String stage, String value) {
        SeleniumUtils.findByID(driver,customerErrorResponse,"natOfOccId_chzn",stage ,value).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='natOfOccId_chzn']//li[contains(@class, 'active-result') and text() = '" + value + "']",stage ,value).click();
    }

    public void getText_employerName(String stage, String value) {
        SeleniumUtils.findByID(driver,customerErrorResponse,"Text_employerName",stage ,value).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"listitem_employerName0a\"]/div/div[text='"+value+"]",stage ,value).click();
    }

    public WebElement getAppInfo(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='applicantIdHeader']/span",stage ,value);
    }

    public void inputStaffMember(String stage, List<String> test) {
        //getNatureOfOccupationType_chzn("EMPLOYMENT DETAILS", "Unemployed");
        //SeleniumUtils.findByID(driver,customerErrorResponse,"Text_employerName",stage ,test.get(0)).sendKeys(test.get(0));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"Text_employerName",stage ,test.get(0)),
                test.get(0).trim());
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"listitem_employerName0a\"]/div/div[text()='"+test.get(0)+"']",stage ,test.get(0)).click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"industrySalaried_chzn",stage ,test.get(1)).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='industrySalaried_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(1) + "']",stage ,test.get(1)).click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"EmpTypeId_chzn",stage ,test.get(2)).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='EmpTypeId_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(2) + "']",stage ,test.get(2)).click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"departmentName",stage ,test.get(3)).sendKeys(test.get(3));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"departmentName",stage ,test.get(3)),
                test.get(3).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"designation",stage ,test.get(4)).sendKeys(test.get(4));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"designation",stage ,test.get(4)),
                test.get(4).trim());

        SeleniumUtils.findByID(driver,customerErrorResponse,"EmpStatId_chzn",stage ,test.get(5)).click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='EmpStatId_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(5) + "']",stage ,test.get(5)).click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"year1",stage ,test.get(6)).sendKeys(test.get(6));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"year1",stage ,test.get(6)),
                test.get(6).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"month1",stage ,test.get(7)).sendKeys(test.get(7));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"month1",stage ,test.get(7)),
                test.get(7).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"remarks",stage ,test.get(8)).sendKeys(test.get(8));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"remarks",stage ,test.get(8)),
                test.get(8).trim());

        SeleniumUtils.findByID(driver,customerErrorResponse,"uniform-employment_detail_salaried_address_check",stage ,"click check address").click();

        SeleniumUtils.clickableByXpath(driver,customerErrorResponse,"//tr[td[2]='Working Address']/td[8]/input",stage ,"choose working address").click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"doneEmpButton",stage ,"click done button").click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"employmentsaveAndNextButton2",stage ,"click save and next").click();

    }
}
