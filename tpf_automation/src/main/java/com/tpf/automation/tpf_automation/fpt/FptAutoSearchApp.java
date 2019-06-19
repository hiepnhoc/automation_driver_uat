package com.tpf.automation.tpf_automation.fpt;

import com.tpf.automation.tpf_automation.element.finnone.*;
import com.tpf.automation.tpf_automation.entity.*;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FptAutoSearchApp {
    public void FptSearch(FptCustomer fptCustomer) throws InterruptedException {

        FptLoanDetail fptLoanDetail = fptCustomer.getLoanDetail();
        List<FptAddress> fptAddresses = fptCustomer.getAddresses();
        List<FptProductDetail> fptProductDetails = fptCustomer.getProductDetails();
        List<FptReference> fptReferences = fptCustomer.getReferences();

        //region OPEN WEB BROWSER
        System.setProperty("webdriver.chrome.driver", "d://test//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.addArguments("window-size=2560x1440");
        WebDriver driver = new ChromeDriver(options);
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();


        driver.get("http://10.1.66.20:4141/finnone-webapp/app/auth/login");


        driver.manage().window().maximize();



        //endregion

        //region LOGIN PAGE
        LoginFptWait loginFptWait = new LoginFptWait(driver,customerErrorResponse);
        loginFptWait.getUsername().sendKeys("phuongnt");
        loginFptWait.getPassword().sendKeys("Hcm@12345");
        loginFptWait.getBtnLogin().click();
        //endregion

        //region CHOOSE APPLICATIONS TO SEARCH
        MainFptWait mainFptWait = new MainFptWait(driver,customerErrorResponse);
        mainFptWait.getMenuApplications("CLICK MAIN MENU", "CLICK MAIN MENU").click();
        mainFptWait.getSubmenuApplications("CLICK SUB APP MENU", "CLICK SUB APP MENU").click();
        //endregion


        //region SEARCH APPLICATION ID
        SearchApplicationWait searchApplicationWait = new SearchApplicationWait(driver,customerErrorResponse);
        WebDriverWait wait11 = new WebDriverWait(driver,10);
        searchApplicationWait.getSearchTextBox().sendKeys("APPL00051122");
        searchApplicationWait.getClickSearchResult("APPL00051122").click();

        System.out.println(searchApplicationWait.getAppInfo().getText().trim() + " Quang la day");
        /*WebDriverWait wait1111 = new WebDriverWait(driver,10);
        WebElement test = wait1111.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applicantIdHeader\"]/span")));
        System.out.println(test.getText().trim());*/
        //endregion


        //region CLICK TO VIEW AND EDIT LEAD DETAILS
        LeadDetailsEditViewWait leadDetailsEditViewWait = new LeadDetailsEditViewWait(driver,customerErrorResponse);
        leadDetailsEditViewWait.getEdit_customer_inDetail_0().click();
        //endregion


        //region CLICK LEFT VERTICAL TAB EMPLOYMENT DETAILS
        LeadDetailsAppInfoWait leadDetailsAppInfoWait = new LeadDetailsAppInfoWait(driver,customerErrorResponse);
        leadDetailsAppInfoWait.getCustomerMainChildTabs_employment_tab("EMPOLYMENT DETAILS","DETAILS").click();
        System.out.println("Test Quang");
        //System.out.println(leadDetailsAppInfoWait.getAppInfo().getText().trim());
        //endregion

        /**
         * @param List<String> empDetails
         * @size 8 [0-7]
         * @detail [0: Product Detail , 1: Good Code, 2: Good Type,
         * 3: Quantity, 4: Good Price, 5: Samsung Credit Limit, 6: Down Payment, 7: Employee card number]
         * @default: click [Employer Address, Select Address: Working Address, Done, Save and Next]
         * @fpt_exp_data: ("model", "goodCode", "goodType", "quantity", "goodPrice","0","downPayment", "employeeCard")
         * Samsung Credit Limit: 0
         */


        /**
         * @param List<String> testLeadDetailsAppInfo
         * @size 8 [0-7]
         * @detail [0: Branch , 1: Channel, 2: Application Form Number, 3: Loan Application Type,
         * 4: Product Name, 5: Scheme, 6: Loan Amount Requested, 7: Sales Agent Code]
         * @fpt_exp_data: ("FPT", "FPT", "2135", "New Application", "CDL_CASH", "product", "loanAmount", "tenure", "OTHER VALUE")
         * @default: Interest Rate: default based on scheme, Loan Term(months): 12 (should be modified)
         * Branch: FPT, Channel: FPT, Application Form Number: 2135, Product Name: CDL_CASH, Sales Agent Code: OTHER VALUE
         */
        //region LEAD DETAILS -> LOAN DETAILS
        List<String> testLeadDetailsAppInfo = new ArrayList<>();
        //testLeadDetailsAppInfo = Arrays.asList("FPT","FPT","2135","New Application","CDL_CASH","CD02_SAMSUNG","17242500","12","OTHER VALUE");

        testLeadDetailsAppInfo = Arrays.asList("FPT","FPT","2135","New Application","CDL_CASH",fptLoanDetail.getProduct(),fptLoanDetail.getLoanAmount(),fptLoanDetail.getTenor(),"OTHER VALUE");
        leadDetailsAppInfoWait.btnLoanDetails("LEAD DETAILS", "LOAN DETAILS");
        LeadDetailsLoanDetailsWait leadDetailsLoanDetailsWait = new LeadDetailsLoanDetailsWait(driver,customerErrorResponse);
        leadDetailsLoanDetailsWait.inputSourcing("LEAD DETAILS -> LOAN DETAILS",testLeadDetailsAppInfo);
        //endregion
        System.out.println("LEAD DETAILS -> LOAN DETAILS DONE");

        /*com.tpf.automation.tpf_automation.element.fptNew.MoveToNextStageFptWait moveToNextStageFptWait = new com.tpf.automation.tpf_automation.element.fptNew.MoveToNextStageFptWait(driver, customerErrorResponse);
        moveToNextStageFptWait.moveToNextStage("END OF LEAD DETAILS", "MOVE TO NEXT STAGE", customerErrorResponse);*/
        //endregion





    }
}
