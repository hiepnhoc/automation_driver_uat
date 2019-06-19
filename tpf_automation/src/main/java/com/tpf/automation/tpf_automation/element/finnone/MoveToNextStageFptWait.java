package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import com.tpf.automation.tpf_automation.restTemplate.AutomationStatusUpdate;
import org.openqa.selenium.WebDriver;

public class MoveToNextStageFptWait {
    WebDriver driver;
    CustomerErrorResponse customerErrorResponse;

    String moveToNextStageBtn = "//*[@id=\"neutrino-mega-menu\"]/li[2]";
    String submenuApplications = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[2]/ul/li/a/span";
    String submenuPersonalLoan = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[2]/ul/li/ul/li/a";
    String submenuCancelLoan = "//*[@id=\"neutrino-mega-menu\"]/li[2]/div/div[3]/ul/li/a";


    public MoveToNextStageFptWait(WebDriver driver, CustomerErrorResponse customerErrorResponse) {
        this.driver = driver;
        this.customerErrorResponse = customerErrorResponse;
    }

    public void moveToNextStage(String username, String stage, String value, CustomerErrorResponse response) {
        SeleniumUtils.findByID(driver,customerErrorResponse,"move_to_next_stage",stage ,"Move to next stage").click();
        try {
            String pass1 = SeleniumUtils.findByXpath(driver,customerErrorResponse,".//*[@id='heading']/h1",stage ,"get Title next step").getText().trim();
            if(pass1.equals("Applications")) {
                response.setField6("Pass");
            }
            else {
                SeleniumUtils.findByXpath(driver,customerErrorResponse,".//*[@id='accordion2']/div/div[1]/a/u/b",stage ,"get Title next step");
                String errorMessage = SeleniumUtils.findByXpath(driver,customerErrorResponse,".//*[@id='error-message']",stage ,"Not move to next stage").getText();
                System.out.println(errorMessage);
                response.setField6("Failed at " + stage + ": " + errorMessage);

            }
            AutomationStatusUpdate.UpdateStatus(customerErrorResponse,driver);
            /*QuitFPTWait quitFPTWait = new QuitFPTWait(driver,customerErrorResponse);
            quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");*/
        }
        catch (Exception e) {
            response.setField6(e.getMessage());
            AutomationStatusUpdate.UpdateStatus(customerErrorResponse, driver);
            /*QuitFPTWait quitFPTWait = new QuitFPTWait(driver,customerErrorResponse);
            quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");*/
        }
    }

}
