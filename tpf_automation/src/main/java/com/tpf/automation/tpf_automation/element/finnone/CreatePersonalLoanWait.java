package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePersonalLoanWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;
    WebDriverWait wait;

    String btnCreateNewCustomer = "create_new_applicant";


    public CreatePersonalLoanWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public WebElement getBtnCreateNewCustomer(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,btnCreateNewCustomer,stage,value);
    }



}
