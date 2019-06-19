package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadDetailsEditViewWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement edit_customer_inDetail_0;
    WebElement Text_employerName; //company tax code


    public LeadDetailsEditViewWait(WebDriver driver) {
        this.driver = driver;
    }

    public LeadDetailsEditViewWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public WebElement getEdit_customer_inDetail_0() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        edit_customer_inDetail_0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-customer-inDetail_0")));
        return edit_customer_inDetail_0;
    }



}
