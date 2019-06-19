package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadDetailsLoanDetailsWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement occupationType_chzn;
    WebElement Text_employerName; //company tax code


    public LeadDetailsLoanDetailsWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }



    public void inputSourcing(String stage, List<String> test) {
        //test = Arrays.asList("FPT","FPT","2135","New Application","CDL_CASH","CD02_SAMSUNG","17200000","OTHER VALUE");
        //WebDriverWait wait = new WebDriverWait(driver,10);

        SeleniumUtils.findByID(driver,customerErrorResponse,"channelId_chzn",stage ,"click choose channel").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='channelId_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(1) + "']",stage ,test.get(1)).click();

        //SeleniumUtils.findByID(driver,customerErrorResponse,"applicationFormNumber",stage ,test.get(2)).sendKeys(test.get(2));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"applicationFormNumber",stage ,test.get(2)),
                test.get(2).trim());

        SeleniumUtils.findByID(driver,customerErrorResponse,"loanApplication_type_chzn",stage ,"click choose loan type").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='loanApplication_type_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(3) + "']",stage ,test.get(3)).click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"loan_product_chzn",stage ,"click choose loan product").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='loan_product_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(4) + "']",stage ,test.get(4)).click();

        SeleniumUtils.findByID(driver,customerErrorResponse,"scheme_chzn",stage ,"click choose scheme").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='scheme_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(5) + "']",stage ,test.get(5)).click();
        /*int i = 0;
        do {
            SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)).click();
            SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)).sendKeys(test.get(6).trim());
            System.out.println("amount_loanAmount_requested: " +
                    SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)).getAttribute("value"));
            SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)).click();
            i++;
        }
        while (!SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)).getAttribute("value").equals(test.get(6).trim())
        && i <=5);*/

        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"amount_loanAmount_requested",stage ,test.get(6)),
                test.get(6).trim());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SeleniumUtils.findByID(driver,customerErrorResponse,"source_tenure",stage ,test.get(7)).clear();
        //SeleniumUtils.captureScreenshot(driver,customerErrorResponse);
        //SeleniumUtils.findByID(driver,customerErrorResponse,"source_tenure",stage ,test.get(7)).sendKeys(test.get(7));
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"source_tenure",stage ,test.get(7)),
                test.get(7).trim());
        //SeleniumUtils.captureScreenshot(driver,customerErrorResponse);
        SeleniumUtils.findByID(driver,customerErrorResponse,"source_rateId",stage,"rate").click();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SeleniumUtils.findByID(driver,customerErrorResponse,"sourcingRM_chzn",stage ,"click choose sourcing").click();
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='sourcingRM_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(8) + "']",stage ,test.get(8)).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"sourcing\"]/div[2]/div/button[2]",stage ,"Click Button").click();


    }
}
