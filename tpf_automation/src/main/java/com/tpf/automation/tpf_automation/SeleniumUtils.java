package com.tpf.automation.tpf_automation;

import com.tpf.automation.tpf_automation.element.finnone.QuitFPTWait;
import com.tpf.automation.tpf_automation.entity.FptCustomer;
import com.tpf.automation.tpf_automation.entity.vin.MomoDTO;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import com.tpf.automation.tpf_automation.fpt.FptAutoNew;
import com.tpf.automation.tpf_automation.restTemplate.AutomationStatusUpdate;
import com.tpf.automation.tpf_automation.services.MomoAutomationService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Date;

import java.io.File;
import java.io.IOException;

import static com.tpf.automation.tpf_automation.AutomationConstant.globalWait;

public class SeleniumUtils {
    /**
     *
     * @param driver
     * @param customerErrorResponse
     * @param element
     * @param stage
     * @param value : if value = "" or null then ignore Web element and return null
     * @return
     */
    public static WebElement findByID(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,globalWait);
        WebElement webElement;
        try {
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
        } catch (Exception ex) {
            webElement = null;
//            customerErrorResponse.setField6(stage + "_" + value);
            customerErrorResponse.setField6("FAILED_" + stage);

            System.out.println(customerErrorResponse.getField3() + " " + stage + ": " + element + ": " + value + " : " + ex.getMessage());
            // Take screenshot and store as a file format
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
        }
        return webElement;
    }

    public static WebElement findByXpath(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,globalWait);
        WebElement webElement;
        try {
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        } catch (Exception ex) {
            webElement = null;
            //customerErrorResponse.setField6(stage + "_" + value);
            customerErrorResponse.setField6("FAILED_" + stage);

            System.out.println(customerErrorResponse.getField3() + " " + stage + ": " + element + ": " + value + " : " + ex.getMessage());
            // Take screenshot and store as a file format
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
        }
        return webElement;
    }

    public static WebElement clickableById(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,globalWait);
        WebElement webElement;
        try {
            webElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
        } catch (Exception ex) {
            webElement = null;

            //customerErrorResponse.setField6(stage + "_" + value);
            customerErrorResponse.setField6("FAILED_" + stage);
            System.out.println(stage + ": " + element + ": " + value + " : " + ex.getMessage());
            // Take screenshot and store as a file format
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
        }
        return webElement;
    }

    public static WebElement clickableByXpath(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,globalWait);
        WebElement webElement;
        try {
            webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
        } catch (Exception ex) {
            webElement = null;
            //customerErrorResponse.setField6(stage + "_" + value);
            customerErrorResponse.setField6("FAILED_" + stage);

            System.out.println(customerErrorResponse.getField3() + " " + stage + ": " + element + ": " + value + " : " + ex.getMessage());
            // Take screenshot and store as a file format
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
        }
        return webElement;

    }

    public static boolean booleanCheckById(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(element)));
        } catch (Exception ex) {
            //customerErrorResponse.setField6(stage + ": " + element + ": " + value + " : " + ex.getMessage());
            customerErrorResponse.setField6("FAILED_" + stage);
            System.out.println(customerErrorResponse.getField3() + " " + stage + ": " + element + ": " + value + " : " + ex.getMessage());
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
            return false;
        }

    }

    public static void runAutoNew(FptCustomer fptCustomer, String username, String password) {
        FptAutoNew fptAutoNew = new FptAutoNew();
        try {
            fptAutoNew.FptAutomation(fptCustomer, username, password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void captureScreenshot(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        // Take screenshot and store as a file format
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Date date= new Date();
            long time = date.getTime();
            String timeStr = String.valueOf( time);
            String nameFile = new String(AutomationConstant.pathCapture).replace("time_", timeStr);
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File(nameFile));
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }
        //QuitFPTWait quitFPTWait = new QuitFPTWait(driver,customerErrorResponse);
        //quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");
    }

    public static void sendKeys(WebElement webElement, String value) {
        int i = 0;
        do {
            webElement.click();
            webElement.clear();
            webElement.sendKeys(value);
            i++;
        } while(!webElement.getAttribute("value").equals(value) && i<=5);
    }

    public static WebElement findByIDLogout(WebDriver driver, CustomerErrorResponse customerErrorResponse, String element, String stage, String value) {
        WebDriverWait wait = new WebDriverWait(driver,globalWait);
        WebElement webElement;
        try {
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
        } catch (Exception ex) {
            webElement = null;
            driver.close();
            driver.quit();
            System.out.println("Closed Main Menu");
            /*webElement = null;
//            customerErrorResponse.setField6(stage + "_" + value);
            customerErrorResponse.setField6("FAILED_" + stage);

            System.out.println(customerErrorResponse.getField3() + " " + stage + ": " + element + ": " + value + " : " + ex.getMessage());
            // Take screenshot and store as a file format
            captureScreenshot(driver,customerErrorResponse);

            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);*/
        }
        return webElement;
    }

    public static void runMomo(MomoDTO momoDTO, String username, String password) {
        MomoAutomationService momoAutomationService = new MomoAutomationService();
        try {
            momoAutomationService.runAutomation(momoDTO, username, password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
