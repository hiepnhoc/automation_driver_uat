package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFptWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;
    WebDriverWait wait;

    WebElement username;
    WebElement password;
    WebElement btnLogin;


    public LoginFptWait(WebDriver driver) {
        this.driver = driver;
    }

    public LoginFptWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public WebElement getUsername() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username_show")));

        } catch (Exception ex) {
            username = null;
            customerErrorResponse.setField2("login_username");
        }
        return username;
    }

    public WebElement getPassword() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password_show")));

        } catch (Exception ex) {
            password = null;
            customerErrorResponse.setField2("login_password");
        }
        return password;
    }

    public WebElement getBtnLogin() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            btnLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton")));

        } catch (Exception ex) {
            btnLogin = null;
            customerErrorResponse.setField2("loginbutton");
        }
        return btnLogin;
    }
}
