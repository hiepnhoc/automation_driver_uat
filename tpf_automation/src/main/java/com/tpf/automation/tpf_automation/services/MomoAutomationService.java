package com.tpf.automation.tpf_automation.services;

import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.element.finnone.*;
import com.tpf.automation.tpf_automation.entity.*;
import com.tpf.automation.tpf_automation.entity.vin.*;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import com.tpf.automation.tpf_automation.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.tpf.automation.tpf_automation.AutomationConstant.*;
import static org.awaitility.Awaitility.await;

public class MomoAutomationService {
    private WebDriver driver;
    private String browser;
    private String baseUrl;
    private String os;
    private String hub;

    public void runAutomation(MomoData momoDTO, String username, String password) throws InterruptedException, IOException {

        // FptLoanDetail fptLoanDetail = fptCustomer.getLoanDetail();
        //List<FptAddress> fptAddresses = fptCustomer.getAddresses();
        //List<FptProductDetail> fptProductDetails = fptCustomer.getProductDetails();
        //List<FptReference> fptReferences = fptCustomer.getReferences();

//        //region OPEN WEB BROWSER
//        System.setProperty(driverProperty, driverPath);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//
//
//        //options.addArguments("window-size=700x1000");
//        options.addArguments(driverWindowSizeFpt);
//        WebDriver driver = new ChromeDriver(options);
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setuserNameRunning(username);
        customerErrorResponse.setField0(momoDTO.getMomoLoanId());
        customerErrorResponse.setField1("UNKNOWN");
        customerErrorResponse.setField3(username);
//
//        driver.get(finnOneUAT);

        //register hub
        //String host = "10.10.10.10";
        String host = "localhost";
        //String host = "172.18.0.2";

        this.browser = "chrome";
        this.os = os;
        this.baseUrl = baseUrl;
        this.hub = hub;

//        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("--incognito");
//            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("window-size=1800x3000");
//            chromeOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL("http://" + host + ":4545/wd/hub"), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), firefoxOptions);
        } else {
            InternetExplorerOptions ieOption = new InternetExplorerOptions();
//            ieOption.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), ieOption);
        }
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        this.driver.manage().window().maximize();

//        this.driver.manage().window().setSize(new Dimension(2560, 1440));
//        this.driver.manage().window().maximize();
        this.driver.get(finnOneUAT);

        //end register hub


        //driver.manage().window().maximize();
        //endregion

        //region LOGIN PAGE
        LoginFptWait loginFptWait = new LoginFptWait(driver, customerErrorResponse);

        loginFptWait.getUsername().sendKeys(username);
        loginFptWait.getPassword().sendKeys(password);
        loginFptWait.getBtnLogin().click();
        //endregion

        //region LOAN CREATION
        //region CHOOSE PERSONAL LOAN
        MainFptWait mainFptWait = new MainFptWait(driver, customerErrorResponse);
        mainFptWait.getMenuApplications("MAIN MENU", "Click Open Main Menu").click();
        mainFptWait.getSubmenuPersonalLoan("MAIN MENU", "Click Choose Personal Loan").click();
        //endregion

        //region CREATE NEW APPLICATION
        CreatePersonalLoanWait createPersonalLoanWait = new CreatePersonalLoanWait(driver, customerErrorResponse);
        createPersonalLoanWait.getBtnCreateNewCustomer("CREATE_NEW_PERSONAL_LOAN", "CLICK TO CREATE PERSONAL LOAN").click();
        //endregion

        /**
         * @detail [check inside region]
         * @default: Citizenship: Vietnamese
         */
        //region LEAD DETAILS -> APPLICANT INFORMATION -> PERSONAL INFORMATION

        /**
         * @param List<String> basicInformation
         * @size 8 [0-7]
         * @detail [0: Gender , 1: FirstName, 2: LastName, 3: MiddleName, 4: DoB, 5: PlaceOfIssue, 6: MaritalStatus, 7: Education]
         * @default: Citizenship: Vietnamese, Education: Highschool
         * @fpt_exp_data (" gender ", " firstName ", " lastName ", " middleName ", " dateOfBirth ", " issuePlace ", " maritalStatus ", " Highschool ")
         */
        //region BASIC INFORMATION
        //List<String> basicInformation = Arrays.asList("Female","MAI","THÀNH","NAM","22/05/1995","HÀ NỘI","Married","Highschool");
        //System.out.println("Quang: " + fptCustomer.getIssuePlace());
        List<String> basicInformation = Arrays.asList(momoDTO.getGender(),
                momoDTO.getFirstName(),
                momoDTO.getLastName(),
                momoDTO.getMiddleName(),
                momoDTO.getDateOfBirth(),
                momoDTO.getIssuePlace(),
                momoDTO.getMaritalStatus(), "Highschool");
        Utils.captureScreenShot(driver);
        LeadDetailsAppInfoWait leadDetailsAppInfoWait = new LeadDetailsAppInfoWait(driver, customerErrorResponse);
        leadDetailsAppInfoWait.getSelectBoxGender("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", "Click drop box").click();
        leadDetailsAppInfoWait.getSelectOneGender("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL INFORMATION", basicInformation.get(0).trim()).click();
        Utils.captureScreenShot(driver);
        leadDetailsAppInfoWait.getFirstName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(1).trim()).sendKeys(basicInformation.get(1).trim());
        leadDetailsAppInfoWait.getLastName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(2).trim()).sendKeys(basicInformation.get(2).trim());
        leadDetailsAppInfoWait.getMiddleName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(3).trim()).sendKeys(basicInformation.get(3).trim());
        leadDetailsAppInfoWait.getDob("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(4).trim()).sendKeys(basicInformation.get(4).trim());
        leadDetailsAppInfoWait.getPlaceOfIssue("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(5).trim()).sendKeys(basicInformation.get(5).trim());
        leadDetailsAppInfoWait.getMaritalStatus("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(6).trim()).click();
        leadDetailsAppInfoWait.getCitizenship("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", "Vietnamese").click();
        leadDetailsAppInfoWait.getEducation("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", basicInformation.get(7).trim()).click();
        leadDetailsAppInfoWait.getBtnProceed("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION", "Click Button Proceed").click();

        //endregion

        System.out.println(username + " BASIC INFORMATION DONE");
        Utils.captureScreenShot(driver);
        /**
         * @param List<List> identification
         * @x-size 5 [0-4]
         * @y-size no define
         * @detail [0: Document Type , 1: Document Number, 2: Issue Date, 3: Country Of Issue]
         * @default: Expiration Date: null, Country of Issue: Vietnam
         * @fpt_exp_data:
         * (" Current National ID ", " nationalId ", " issuedDate ", " Vietnam ")
         * ("Insurance Number", "employeeCard", "","Vietnam")
         * ("Family Book Number", "0", "", "Vietnam")
         * ("Spouse Current National ID","personalId"(references),"","Vietnam")
         * @note: "Current National ID" - nationalId, "Insurance Number" - employeeCard
         */
        //region IDENTIFICATION INFORMATION
        List<List> identification = new ArrayList<>();
        /*List<String> a = Arrays.asList("Current National ID","111002090","28/02/2012","Vietnam");
        List<String> b = Arrays.asList("Insurance Number","10004647","","Vietnam");
        List<String> c = Arrays.asList("Family Book Number","0","","Vietnam");
        List<String> d = Arrays.asList("Spouse Current National ID","123456789","","Vietnam");*/

        List<String> a = Arrays.asList("Current National ID", momoDTO.getPersonalId(), momoDTO.getIssueDate(), "Vietnam");
        //List<String> b = Arrays.asList("Insurance Number",momoDTO.getData().getEmployeeCard(),"","Vietnam");
        //List<String> c = Arrays.asList("Family Book Number","0","","Vietnam");
        List<String> d = new ArrayList<>();


        identification.add(a);
        //identification.add(b);
        //identification.add(c);
        if (momoDTO.getMaritalStatus().equals("Married")) {
            for (MomoReference momoReference : momoDTO.getReferences()
            ) {
                if (momoReference.getRelation().equals("Spouse")) {
                    d = Arrays.asList("Spouse Current National ID", momoReference.getPersonalId(), "", "Vietnam");
                    identification.add(d);
                    break;
                }
            }
        }

        if (leadDetailsAppInfoWait.getCheckBtnProceedHide("IDENTIFICATION INFORMATION", "Check hide/unhide proceed btn")) {
            leadDetailsAppInfoWait.getExpandIdentification("IDENTIFICATION INFORMATION", "Click to expand Identification Info").click();
            leadDetailsAppInfoWait.inputIdentification("IDENTIFICATION INFORMATION", identification);
        }
        //endregion
        System.out.println(username + " IDENTIFICATION INFORMATION DONE");
        Utils.captureScreenShot(driver);
        /**
         * @param List<List> address
         * @x-size 14 [0-13]
         * @y-size no define
         * @detail [0: Address Type , 1: Country, 2: Region, 3: City/Province, 4: Area/District, 5: Building/Apartment (Address line 1),
         * 6: House&Street (Address line 2), 7: Ward (Address Line 3), 8: Direction,
         * 9: Duration of Residence - Years, 10: Duration of Residence - Months
         * 11: Primary Phone - STD, 12: Primary Phone - NUMBER, 13: Mobile Phone]
         * @default: EXTN: null, Country: Vietnam, PINCODE: default only one value
         * @fpt_exp_data: (" addressType ", " Vietnam ", " region ", " province ", " district ", " address1 ", " address2 ", " ward ", " map ", " durationYear ", " durationMonth ", " ", " ", " mobilePhone ")
         * "Current Address": as above
         * "Family Book Address": no "map","durationYear","durationMonth","","","mobilePhone"
         * "Working Address": no "map","durationYear","durationMonth","","","mobilePhone"
         * "Spouse Address": no "map","durationYear","durationMonth","","","mobilePhone"
         * @note: months (UI) = years, years (UI) = months
         */
        //region ADDRESS INFORMATION
        List<List> address = new ArrayList<>();
        List<String> a1 = new ArrayList<>();
        List<String> b1 = new ArrayList<>();
        List<String> c1 = new ArrayList<>();
        List<String> d1 = new ArrayList<>();

        /*List<String> a1 = Arrays.asList("Current Address","Vietnam","NORTH","BẮC GIANG","HIỆP HÒA","*","273/46 BẦU CÁT","PHƯỜNG 12","ĐỊA CHỈ THEO BẢN ĐỒ","4","0","","","339023865");
        List<String> b1 = Arrays.asList("Family Book Address","Vietnam","NORTH","BẮC GIANG","HIỆP HÒA","*","273/46 BẦU CÁT","PHƯỜNG 12","ĐỊA CHỈ THEO BẢN ĐỒ","","","","","");
        List<String> c1 = Arrays.asList("Working Address","Vietnam","NORTH","BẮC NINH","YÊN PHONG","*","CỔNG 2 SAMSUNG","YÊN TRUNG","","","","222","369687","");
        List<String> d1 = Arrays.asList("Spouse Address","Vietnam","NORTH","BẮC GIANG","HIỆP HÒA","*","273/46 BẦU CÁT","PHƯỜNG 12","","","","","","");*/

        MomoAddress momoAddress = MomoAddress.builder().addressType("Current Address")
                .country("Vietnam")
                .region("ALL")
                .province(momoDTO.getCity())
                .district(momoDTO.getDistrict())
                .address1(momoDTO.getAddress1())
                .address2(momoDTO.getAddress2())
                .ward(momoDTO.getWard())
                .build();


        a1 = Arrays.asList(momoAddress.getAddressType(), "Vietnam", "ALL"//fptaddress.getRegion()
                , momoAddress.getProvince(), momoAddress.getDistrict(),
                momoAddress.getAddress1(), momoAddress.getAddress2(), momoAddress.getWard(), "", "1",
                "1", "", "", momoDTO.getPhoneNumber().replace("+84", ""));
        address.add(a1);


        b1 = Arrays.asList("Family Book Address", "Vietnam", "ALL"//fptaddress.getRegion()
                , momoAddress.getProvince(), momoAddress.getDistrict(),
                momoAddress.getAddress1(), momoAddress.getAddress2(), momoAddress.getWard(), "", "",
                "", "", "", "");
        address.add(b1);

        if (momoDTO.getMaritalStatus().equals("Married")) {
            c1 = Arrays.asList("Spouse Address", "Vietnam", "ALL"//fptaddress.getRegion()
                    , momoAddress.getProvince(), momoAddress.getDistrict(),
                    momoAddress.getAddress1(), momoAddress.getAddress2(), momoAddress.getWard(), "", "",
                    "", "", "", "");
            address.add(c1);
        }

        d1 = Arrays.asList("Working Address", "Vietnam", "ALL"//fptaddress.getRegion()
                , momoAddress.getProvince(), momoAddress.getDistrict(),
                momoAddress.getAddress1(), momoAddress.getAddress2(), momoAddress.getWard(), "", "",
                "", "", "","");
        address.add(d1);

        leadDetailsAppInfoWait.getExpandAddress("ADDRESS INFORMATION", "CLICK TO INPUT ADDRESS").click();
        leadDetailsAppInfoWait.inputAddress("ADDRESS INFORMATION", address);
        leadDetailsAppInfoWait.getExpandAddress("ADDRESS INFORMATION", "CLICK TO INPUT ADDRESS").click();
        //endregion
        System.out.println(username + " ADDRESS INFORMATION DONE");
        Utils.captureScreenShot(driver);
        /**
         * @param List<List> family
         * @param String noOfDependents
         * @param String noOfChildren
         * @x-size 14 [0-13]
         * @y-size no define
         * @detail [0: Member Name , 1: Relationship, 2: Phone number, 3: Education Status, 4: Company Name ]
         * @default: Is Dependent: uncheck, Number of Dependents: 0, Number of Children: 0
         * @fpt_exp_data: (" fullName ", " relation ", " phoneNumber ", " ", " ")
         * @note: Education drop box => "" ignore do activity
         */
        //region FAMILY INFORMATION
        //check status marial moi insert
        if (momoDTO.getMaritalStatus().equals("Married")) {
            List<List> family = new ArrayList<>();
            //List<String> a3 = Arrays.asList("NGUYỄN VĂN QUÂN","Spouse","0829403521","","");
            List<String> a3 = new ArrayList<>();
            for (MomoReference momoReference : momoDTO.getReferences()
            ) {
                if (momoReference.getRelation().equals("Spouse")) {
                    //System.out.println(fptReference.getRelation().equals("Spouse"));
                    a3 = Arrays.asList(momoReference.getFullName(), "Spouse", momoReference.getPhoneNumber(), "", "");
                    family.add(a3);
                    //leadDetailsAppInfoWait.getExpandFamily("FAMILY INFORMATION","Click Family").click();
                    leadDetailsAppInfoWait.inputFamily(family, "0", "0", "FAMILY INFORMATION", "click to add");
                }
            }
            /*family.add(a3);
            //leadDetailsAppInfoWait.getExpandFamily("FAMILY INFORMATION","Click Family").click();
            leadDetailsAppInfoWait.inputFamily(family,"0","0","FAMILY INFORMATION", "click to add");*/
            //endregion
            System.out.println(username + " FAMILY INFORMATION DONE");
            Utils.captureScreenShot(driver);
        }
        /**
         * @detail [Choose primary address for communication ]
         * @default: choose "Current Address"
         */
        //region COMMUNICATION INFORMATION
        leadDetailsAppInfoWait.getLoadCust_comm("COMMUNICATION INFORMATION", "Load Communication").click();
        leadDetailsAppInfoWait.getBtnPriAdd("COMMUNICATION INFORMATION", "Choose Primary Communication").click();
        leadDetailsAppInfoWait.getPrimaryEmailElement().sendKeys(momoDTO.getEmail());
        leadDetailsAppInfoWait.getLoadCust_comm("COMMUNICATION INFORMATION", "Load Communication").click();
        //endregion
        System.out.println(username + " COMMUNICATION INFORMATION DONE");
        /**
         * @detail [Mandatory: for moving next stage ]
         * @default: No value
         */
        //region CHECK DEDUPE
        leadDetailsAppInfoWait.getBtnCheckDupde("CHECK DEDUPE", "check dedupe").click();
        leadDetailsAppInfoWait.getBtnSavePI("SAVE AND NEXT", "SAVE AND NEXT").click();
        //endregion

        //endregion LEAD DETAILS -> APPLICANT INFORMATION -> PERSONAL INFORMATION
        System.out.println(username + " PERSONAL INFORMATION DONE");

        /**
         * @param List<String> empDetails
         * @size 9 [0-8]
         * @detail [0: Company Tax Code , 1: Industry, 2: Employment Type, 3: Department, 4: Level, 5: Employment Status,
         * 6: Duration of Current Employment - Years, 7: Duration of Current Employment - Months, 8: Employer Name]
         * @default: click [Employer Address, Select Address: Working Address, Done, Save and Next]
         * @note: Sub-Industry: Other (not yet and should be index 2)
         */
        //region LEAD DETATILS -> APPLICANT INFORMATION -> EMPLOYMENT DETAILS

        List<String> empDetails = new ArrayList<>();
        empDetails = Arrays.asList("Others", "T-Manufacturing", "Regular Employment", "SẢN XUẤT", "1", "Present", "0", "1", "SAMSUNG");
        LeadDetailsEmpDetailsWait leadDetailsEmpDetailsWait = new LeadDetailsEmpDetailsWait(driver, customerErrorResponse);
        leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS", "Get app info").getText();
        System.out.println(username + " " + leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS", "Get app info").getText().trim());
        customerErrorResponse.setField1(leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS", "Get app info").getText());
        leadDetailsEmpDetailsWait.getOccupationType_chzn("EMPLOYMENT DETAILS", "Others");
        leadDetailsEmpDetailsWait.inputStaffMember("EMPLOYMENT DETAILS", empDetails);

        //endregion
        System.out.println(username + " EMPLOYMENT DETAILS DONE");
        Utils.captureScreenShot(driver);

        //region FINANCE DETAIL
        List<MomoIncomeDto> incomeDetailDTOList = new ArrayList<>();
        MomoIncomeDto incomeDetailDTO = MomoIncomeDto.builder().incomeHead("Main Personal Income")
                .frequency("Monthly")
                .amount(String.valueOf(momoDTO.getSalary()))
                .percentage("100").build();
        incomeDetailDTOList.add(incomeDetailDTO);

        LeadDetailsEmpDetailsFinanceWait financialDetailsTab = new LeadDetailsEmpDetailsFinanceWait(driver);
        financialDetailsTab.openIncomeDetailSection();
        await("Load financial details - income details Section Timeout!").atMost(30, TimeUnit.SECONDS)
                .until(() -> financialDetailsTab.getIncomeDetailDivElement().isDisplayed());
        financialDetailsTab.setIncomeDetailsData(incomeDetailDTOList);
        Utils.captureScreenShot(driver);
//        financialDetailsTab.validInOutData(dataControl, applicationInfoValue.get("incomeDetails"));
        financialDetailsTab.saveAndNext();

        System.out.println(username + " EMPLOYMENT DETAILS : FINANCE DONE");
        Utils.captureScreenShot(driver);
        //end region

        /**
         * @param List<String> empDetails
         * @size 8 [0-7]
         * @detail [0: Product Detail , 1: Good Code, 2: Good Type,
         * 3: Quantity, 4: Good Price, 5: Samsung Credit Limit, 6: Down Payment, 7: Employee card number]
         * @default: click [Employer Address, Select Address: Working Address, Done, Save and Next]
         * @fpt_exp_data: (" model ", " goodCode ", " goodType ", " quantity ", " goodPrice ", " 0 ", " downPayment ", " employeeCard ")
         * Samsung Credit Limit: 0
         */
//        //region LEAD DETAILS -> MISC-FPT
//        List<String> testLeadDetailsMisc = new ArrayList<>();
//        List<List> inputProducts = new ArrayList<>();
//        testLeadDetailsMisc = Arrays.asList("SAMSUNG NOTE 9 128G","00484677","Portable","1","17242500","0","0","10004647");
//        for (FptProductDetail fptProductDetail : fptProductDetails
//        ) {
//            inputProducts.add(Arrays.asList(fptProductDetail.getModel(),fptProductDetail.getGoodCode(),
//                    fptProductDetail.getGoodType(),fptProductDetail.getQuantity(), fptProductDetail.getGoodPrice()));
//
//        }
//        leadDetailsAppInfoWait.btnMiscFpt("LEAD DETAILS", "MISC-FPT");
//        LeadDetailsMiscFptWait leadDetailsMiscFptWait = new LeadDetailsMiscFptWait(driver,customerErrorResponse);
//        leadDetailsMiscFptWait.inputFPT("LEAD DETAILS -> MISC-FPT",inputProducts,"0",fptLoanDetail.getDownPayment(),fptCustomer.getEmployeeCard());
//        //endregion
//        System.out.println(username + " LEAD DETAILS -> MISC-FPT DONE");

        /**
         * @param List<String> testLeadDetailsAppInfo
         * @size 8 [0-7]
         * @detail [0: Branch , 1: Channel, 2: Application Form Number, 3: Loan Application Type,
         * 4: Product Name, 5: Scheme, 6: Loan Amount Requested, 7: Sales Agent Code]
         * @fpt_exp_data: (" FPT ", " FPT ", " 2135 ", " New Application ", " CDL_CASH ", " product ", " loanAmount ", " tenure ", " OTHER VALUE ")
         * @default: Interest Rate: default based on scheme, Loan Term(months): 12 (should be modified)
         * Branch: FPT, Channel: FPT, Application Form Number: 2135, Product Name: CDL_CASH, Sales Agent Code: OTHER VALUE
         */
        //region LEAD DETAILS -> LOAN DETAILS
        List<String> testLeadDetailsAppInfo = new ArrayList<>();
        //testLeadDetailsAppInfo = Arrays.asList("FPT","FPT","2135","New Application","CDL_CASH","CD02_SAMSUNG","17242500","12","OTHER VALUE");
        String appFormNumber = momoDTO.getMomoLoanId().toString();
        System.out.println("Loan Amount: " + momoDTO.getAmount());
        System.out.println("Tenor : " + momoDTO.getLoanTime());

        //check productcode
        if (momoDTO.getProductCode().equals("DG02")) {
            momoDTO.setProductCode("DG02_MOMO ENTRY");
        } else if (momoDTO.getProductCode().equals("DG01")) {
            momoDTO.setProductCode("DG01_MOMO TRIAL");
        }

        testLeadDetailsAppInfo = Arrays.asList("MOMO", "MOMO", appFormNumber, "New Application", "DGL_CASH", momoDTO.getProductCode(), momoDTO.getAmount().toString(), momoDTO.getLoanTime().toString(), "OTHER VALUE");
        leadDetailsAppInfoWait.btnLoanDetails("LEAD DETAILS", "LOAN DETAILS");
        Utils.captureScreenShot(driver);
        LeadDetailsLoanDetailsWait leadDetailsLoanDetailsWait = new LeadDetailsLoanDetailsWait(driver, customerErrorResponse);
        await("Load loan details - sourcing details tab Timeout!").atMost(30, TimeUnit.SECONDS)
                .until(() -> leadDetailsLoanDetailsWait.getTabSourcingDetailsElement().getAttribute("class").contains("active"));
        await("Load loan details - sourcing details container Timeout!").atMost(30, TimeUnit.SECONDS)
                .until(() -> leadDetailsLoanDetailsWait.getSourcingDetailsDivContainerElement().isDisplayed());
        Utils.captureScreenShot(driver);
        leadDetailsLoanDetailsWait.inputSourcing("LEAD DETAILS -> LOAN DETAILS", testLeadDetailsAppInfo);
        //endregion
        System.out.println(username + " LEAD DETAILS -> LOAN DETAILS DONE");
        Utils.captureScreenShot(driver);

        //regipn LEAD DETAILS -> VAP
        if (momoDTO.getInsurrance() == true) {
            MomoInsurance momoInsurance=MomoInsurance.builder()
                    .vapProduct("INSP01_InsParameter") //set branch default la FPT
                    .vapTreatment("Financed")
                    .insuranceCompany("TPF_GIC-Global Insurance Company")
                    .build();

            LeadDetailsLoanDetailVapWait loanDetailsVapDetailsTab = new LeadDetailsLoanDetailVapWait(driver);

            await("Load loan details - vap details container Timeout!").atMost(30, TimeUnit.SECONDS)
                    .until(() -> loanDetailsVapDetailsTab.getTabVapDetailsElement().getAttribute("class").contains("active"));
            //loanDetailsVapDetailsTab.getTabVapDetailsElement().click();
            await("Load loan details - sourcing details container Timeout!").atMost(30, TimeUnit.SECONDS)
                    .until(() -> loanDetailsVapDetailsTab.getVapDetailsDivContainerElement().isDisplayed());
            loanDetailsVapDetailsTab.setData(momoInsurance);
            Utils.captureScreenShot(driver);
            loanDetailsVapDetailsTab.getBtnSaveAndNextElement().click();
        }

        /**
         * @param List<String> test_ref
         * @size 3 [0-2]
         * @detail [0: Full Name , 1: Relationship with Borrower, 2: Mobile Phone Number]
         * @fpt_exp_data: (" fullName ", " relation ", " phoneNumber ")
         * @default: no default
         */
        //region LEAD DETAILS -> REFERENCES
        List<List> test_ref = new ArrayList<>();
        //List<String> a6 = Arrays.asList("BÙI VĂN VỆ","Relative","373298133");
        //List<String> b6 = Arrays.asList("HOÀNG THỊ TỐ NGA","Colleague","335990151");
        for (MomoReference momoReference : momoDTO.getReferences()
        ) {
            if (!momoReference.getRelation().equals("Spouse")) {
                test_ref.add(Arrays.asList(momoReference.getFullName(), momoReference.getRelation(), momoReference.getPhoneNumber()));
            }
            //test_ref.add(Arrays.asList(fptReference.getFullName(),fptReference.getRelation(),fptReference.getPhoneNumber()));
        }
        //test_ref.add(a6);
        //test_ref.add(b6);
        leadDetailsAppInfoWait.btnReferences("LEAD DETAILS", "REFERENCES");
        LeadDetailsReferencesWait leadDetailsReferencesWait = new LeadDetailsReferencesWait(driver, customerErrorResponse);
        leadDetailsReferencesWait.inputReferences("LEAD DETAILS -> REFERENCES", test_ref);
        //endregion
        System.out.println(username + " LEAD DETAILS -> REFERENCES DONE");

        /**
         * @param List<String> test_dtl
         * @size 7 [0-6]
         * @detail [0: Loan Purpose , 1: Number of dependents, 2: Monthly Rental/Mortgage Payment Cost, 3: House Ownership
         * 4: New bank Card Number, 5: Sales Agent Code, 6: Max requested interest rate]
         * @fpt_exp_data: (" Education, sports ", " 0 ", " 0 ", " Rented ", " 1111111111111111 ", " 62 ")
         * @default: Max requested interest rate: 62
         * Number of dependents: 0, Monthly Rental/Mortgage Payment Cost: 0
         * Loan Purpose: Education, sports, New bank Card Number: 1111111111111111
         */
        //region LEAD DETAILS -> MISC - FRMPDAPTL
        List<String> test_dtl = new ArrayList<>();
        test_dtl = Arrays.asList("Education, sports", "0", "0", "Rented", "2222222222222222", "FPT000001", "62");
        test_dtl = Arrays.asList("Education, sports", "0", "0", "Rented", "2222222222222222", "DS0001100", "62");
        leadDetailsAppInfoWait.btnMiscAppDtl("LEAD DETAILS", "MISC - FRMAPPDTL");
        LeadDetailsAppDtlWait leadDetailsAppDtlWait = new LeadDetailsAppDtlWait(driver, customerErrorResponse);
        leadDetailsAppDtlWait.inputFrmAppDtl("LEAD DETAILS -> MISC - FRMAPPDTL", test_dtl);
        System.out.println(username + " LEAD DETAILS -> MISC - FRMPDAPTL DONE");

        MoveToNextStageFptWait moveToNextStageFptWait = new MoveToNextStageFptWait(driver, customerErrorResponse);
        moveToNextStageFptWait.moveToNextStage(username, "END OF LEAD DETAILS", "MOVE TO NEXT STAGE", customerErrorResponse);
        Utils.captureScreenShot(driver);

        //endregion


        //endregion LOAN CREATION


//        QuitFPTWait quitFPTWait = new QuitFPTWait(driver,customerErrorResponse);
//        quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");


        //region FLAG END THREAD
        for (int i = 0; i < AutomationConstant.userIdMomo.size(); i++) {
            if (AutomationConstant.userIdMomo.get(i).get(0).equals(username)) {
                AutomationConstant.userIdMomo.get(i).set(2, "false");
            }
        }
        System.out.println("End thread " + AutomationConstant.userIdMomo);
        //endregion


    }
}
