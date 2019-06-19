package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadDetailsMiscFptWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement occupationType_chzn;
    WebElement Text_employerName; //company tax code

    public LeadDetailsMiscFptWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }



    public void inputFPT(String stage, List<List> test, String downPayment, String amounCreditLimit, String employeeCard) {
        //test = Arrays.asList("SAMSUNG NOTE 9 128G","00484677","Portable","1","17200000","0","0","10004647");
        //WebDriverWait wait = new WebDriverWait(driver,10);
        for(int i= 0; i < test.size(); i++) {
            int j=i+1;
            //SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_MODEL_"+j+"_FPT_"+i,stage ,test.get(i).get(0).toString()).sendKeys(test.get(i).get(0).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_MODEL_"+j+"_FPT_"+i,stage ,test.get(i).get(0).toString()),
                    test.get(i).get(0).toString().trim());
            //SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_GOOD_CODE_"+j+"_FPT_"+i,stage ,test.get(i).get(1).toString()).sendKeys(test.get(i).get(1).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_GOOD_CODE_"+j+"_FPT_"+i,stage ,test.get(i).get(1).toString()),
                    test.get(i).get(1).toString().trim());


            SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_GOOD_TYPE_"+j+"_FPT_"+i+"_chzn",stage ,"click to choose good type").click();
            SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='FPT_GOOD_TYPE_"+j+"_FPT_"+i+"_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(2).toString() + "']",stage ,test.get(i).get(2).toString()).click();

            //SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_PRODUCT_QUANTITY_"+j+"_FPT_"+i,stage ,test.get(i).get(3).toString()).sendKeys(test.get(i).get(3).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_PRODUCT_QUANTITY_"+j+"_FPT_"+i,stage ,test.get(i).get(3).toString()),
                    test.get(i).get(3).toString().trim());

            //SeleniumUtils.findByID(driver,customerErrorResponse,"amount_FPT_GOOD_PRICE_"+j+"_FPT_"+i,stage ,test.get(i).get(4).toString()).sendKeys(test.get(i).get(4).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"amount_FPT_GOOD_PRICE_"+j+"_FPT_"+i,stage ,test.get(i).get(4).toString()),
                    test.get(i).get(4).toString());
            /*System.out.println("amount_FPT_GOOD_PRICE_"+j+"_FPT_"+i+": " +
                    SeleniumUtils.findByID(driver,customerErrorResponse,"amount_FPT_GOOD_PRICE_"+j+"_FPT_"+i,stage ,test.get(i).get(4).toString()).getAttribute("value"));*/
            /*if(i < test.size()-1) {
                SeleniumUtils.clickableByXpath(driver,customerErrorResponse,"//*[@id=\"familyDetailForm\"]/input[1]",stage,"Click to add");
            }*/
        }



        //SeleniumUtils.findByID(driver,customerErrorResponse,"amount_FPT_SAMSUNG_CREDIT_LIMIT_3_FPT_3",stage ,amounCreditLimit).sendKeys(amounCreditLimit);
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"amount_FPT_SAMSUNG_CREDIT_LIMIT_3_FPT_3",stage ,amounCreditLimit),
                amounCreditLimit);

        //SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_Down_Payment_3_FPT_3",stage ,downPayment).sendKeys(downPayment);
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"FPT_Down_Payment_3_FPT_3",stage ,downPayment),
                downPayment);

        //SeleniumUtils.findByID(driver,customerErrorResponse,"Employee_card_no_FPT_3",stage ,employeeCard).sendKeys(employeeCard);
        SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"Employee_card_no_FPT_3",stage ,employeeCard),
                employeeCard);

        SeleniumUtils.findByID(driver,customerErrorResponse,"dynSave",stage ,"Click Save").click();

    }
}
