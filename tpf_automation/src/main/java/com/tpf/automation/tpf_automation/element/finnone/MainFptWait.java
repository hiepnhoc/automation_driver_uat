package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainFptWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    String menuApplications = "//*[@id=\"neutrino-mega-menu\"]/li[2]";
    String submenuApplications = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[2]/ul/li/a/span";
    String submenuPersonalLoan = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[2]/ul/li/ul/li/a";
    String submenuCancelLoan = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[3]/ul/li/a";


    public MainFptWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public WebElement getMenuApplications(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,menuApplications,stage,value);
    }


    public WebElement getSubmenuPersonalLoan(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,submenuPersonalLoan,stage,value);
    }

    public WebElement getSubmenuApplications(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,submenuApplications,stage,value);
    }

    public WebElement getSubmenuCancelLoan(String stage, String value) {
        return SeleniumUtils.findByXpath(driver,customerErrorResponse,submenuCancelLoan,stage,value);
    }

}
