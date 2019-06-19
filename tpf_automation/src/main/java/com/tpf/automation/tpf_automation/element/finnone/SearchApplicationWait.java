package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchApplicationWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();

    public SearchApplicationWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    WebElement searchTextBox;
    WebElement clickSearchResult;
    WebElement appInfo;

    public WebElement getSearchTextBox() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LoanApplication_Assigned_filter\"]/label/input")));
        return searchTextBox;
    }

    public WebElement getClickSearchResult(String appStringVal) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        //btnPriAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[4]=\"Current Address\"]/td[7]/input")));

        //Thread.sleep(5000);

        clickSearchResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[2]='"+ appStringVal + "']/td[2]/a")));
        //clickSearchResult.click();
        //System.out.println(clickSearchResult.getText());
        //System.out.println(clickSearchResult.getAttribute("id"));
        //clickSearchResult.click();
        return clickSearchResult;
    }

    public WebElement getAppInfo() {
        WebDriverWait wait1111 = new WebDriverWait(driver,10);
        WebElement test = wait1111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applicantIdHeader\"]/span")));
        //System.out.println(test.getText().trim());
        return test;
    }
}

