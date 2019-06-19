package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadDetailsAppDtlWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement occupationType_chzn;
    WebElement Text_employerName; //company tax code


    public LeadDetailsAppDtlWait(WebDriver driver) {
        this.driver = driver;
    }

    public LeadDetailsAppDtlWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }



    public void inputFrmAppDtl(String stage, List<String> test) {
        //test = Arrays.asList("Education, sports","0","0","Rented","1111111111111111","FPT000001","62");

        SeleniumUtils.findByID(driver,customerErrorResponse,"Loan_purpose_1_frmAppDtl_0_chzn",stage ,"click loan purpose").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='Loan_purpose_1_frmAppDtl_0_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(0) + "']",stage ,test.get(0)).click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"householdmembers_frmAppDtl_1",stage ,test.get(1)).sendKeys(test.get(1));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"householdmembers_frmAppDtl_1",stage ,test.get(1)),
                test.get(1).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"amount_mortgage_payment_cost_frmAppDtl_1",stage ,test.get(2)).sendKeys(test.get(2));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"amount_mortgage_payment_cost_frmAppDtl_1",stage ,test.get(2)),
                test.get(2).trim());

        SeleniumUtils.findByID(driver,customerErrorResponse,"house_ownership_frmAppDtl_1_chzn",stage ,"click house ownership").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='house_ownership_frmAppDtl_1_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(3) + "']",stage ,test.get(3)).click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"newbankcardnumber_frmAppDtl_4",stage ,test.get(4)).sendKeys(test.get(4));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"newbankcardnumber_frmAppDtl_4",stage ,test.get(4)),
                test.get(4).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"salesagentcode_frmAppDtl_4",stage ,test.get(5)).sendKeys(test.get(5));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"salesagentcode_frmAppDtl_4",stage ,test.get(5)),
                test.get(5).trim());

        //SeleniumUtils.findByID(driver,customerErrorResponse,"Max_req_EIR_frmAppDtl_5",stage ,test.get(6)).sendKeys(test.get(6));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"Max_req_EIR_frmAppDtl_5",stage ,test.get(6)),
                test.get(6).trim());

        SeleniumUtils.findByID(driver,customerErrorResponse,"dynSave",stage ,"Click Save").click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"move_to_next_stage",stage ,"Move to next stage").click();


    }
}
