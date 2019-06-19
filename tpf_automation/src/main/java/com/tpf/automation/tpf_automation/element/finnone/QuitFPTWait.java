package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import com.tpf.automation.tpf_automation.rest.FptController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuitFPTWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    WebElement loggedInUserPhoto;
    WebElement logout;
    WebElement redirectToLoginButton;

    public QuitFPTWait(WebDriver driver) {
        this.driver = driver;
    }

    public QuitFPTWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public WebElement getLoggedInUserPhoto(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,"loggedInUserPhoto",stage ,value);
    }
    public WebElement getLogout(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,"logout",stage ,value);
    }

    public WebElement getRedirectToLoginButton(String stage, String value) {
        return SeleniumUtils.findByID(driver,customerErrorResponse,"redirectToLoginButton",stage ,value);
    }

    public void QuitFinnOne(String stage, String value) {
        SeleniumUtils.findByIDLogout(driver,customerErrorResponse,"loggedInUserPhoto",stage ,value).click();
        SeleniumUtils.findByIDLogout(driver,customerErrorResponse,"logout",stage ,value).click();

        try {
            driver.switchTo().alert().accept();
        } catch (Exception ex) {
            System.out.println("No alert");
        }

        //SeleniumUtils.findByID(driver,customerErrorResponse,"redirectToLoginButton",stage ,value);
        for(int i = 0; i< AutomationConstant.userId.size(); i++) {
            if(AutomationConstant.userId.get(i).get(0).equals(customerErrorResponse.getuserNameRunning())) {
                AutomationConstant.userId.get(i).set(2,"false");
            }
        }
        System.out.println("End thread " + customerErrorResponse.getuserNameRunning());
        //driver.close();
        driver.quit();
        System.out.println("Quit");
        if(AutomationConstant.customerQueue.size() >= 1) {
            FptController fptController = new FptController();
            fptController.addData(AutomationConstant.customerQueue.get(0));
            AutomationConstant.customerQueue.remove(0);
        }
    }

}
