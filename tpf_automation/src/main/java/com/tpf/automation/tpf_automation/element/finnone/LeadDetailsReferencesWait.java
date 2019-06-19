package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadDetailsReferencesWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement occupationType_chzn;
    WebElement Text_employerName; //company tax code



    public LeadDetailsReferencesWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public void inputReferences(String stage, List<List> test) {

        for(int i= 0; i < test.size(); i++) {

            //SeleniumUtils.findByID(driver,customerErrorResponse,"customer_references_name_" + i,stage ,test.get(i).get(0).toString()).sendKeys(test.get(i).get(0).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"customer_references_name_" + i,stage ,test.get(i).get(0).toString()),
                    test.get(i).get(0).toString().trim());

            SeleniumUtils.findByID(driver,customerErrorResponse,"customer_references_relationship_"+i+"_chzn",stage ,"Click choose relationship type").click();
            SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id='customer_references_relationship_"+i+"_chzn']//li[contains(@class, 'active-result') and text() = '" + test.get(i).get(1) + "']",stage ,test.get(i).get(1).toString()).click();

            //SeleniumUtils.findByID(driver,customerErrorResponse,"mobilenumber_"+i+"_phoneNumber",stage ,test.get(i).get(2).toString()).sendKeys(test.get(i).get(2).toString());
            SeleniumUtils.sendKeys(SeleniumUtils.findByID(driver,customerErrorResponse,"mobilenumber_"+i+"_phoneNumber",stage ,test.get(i).get(2).toString()),
                    test.get(i).get(2).toString().trim());

            if(i < test.size()-1) {
                SeleniumUtils.findByID(driver,customerErrorResponse,"create_new_reference_detail_row",stage ,"Click create new reference").click();
            }
        }

        SeleniumUtils.findByXpath(driver,customerErrorResponse,"//*[@id=\"container-no-tpl\"]/div/div[6]/button[1]",stage ,"Click button").click();

    }


}
