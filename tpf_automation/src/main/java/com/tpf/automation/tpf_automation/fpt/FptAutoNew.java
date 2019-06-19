package com.tpf.automation.tpf_automation.fpt;

import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.element.finnone.*;
import com.tpf.automation.tpf_automation.entity.*;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tpf.automation.tpf_automation.AutomationConstant.*;

public class FptAutoNew {
    public void FptAutomation(FptCustomer fptCustomer, String username, String password) throws InterruptedException {

        FptLoanDetail fptLoanDetail = fptCustomer.getLoanDetail();
        List<FptAddress> fptAddresses = fptCustomer.getAddresses();
        List<FptProductDetail> fptProductDetails = fptCustomer.getProductDetails();
        List<FptReference> fptReferences = fptCustomer.getReferences();

        //region OPEN WEB BROWSER
        System.setProperty(driverProperty, driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");


        //options.addArguments("window-size=700x1000");
        options.addArguments(driverWindowSizeFpt);
        WebDriver driver = new ChromeDriver(options);
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setuserNameRunning(username);
        customerErrorResponse.setField0(fptCustomer.getCustId().trim());
        customerErrorResponse.setField1("UNKNOWN");
        customerErrorResponse.setField3(username);

        driver.get(finnOneUAT);


        //driver.manage().window().maximize();
        //endregion

        //region LOGIN PAGE
        LoginFptWait loginFptWait = new LoginFptWait(driver,customerErrorResponse);

        loginFptWait.getUsername().sendKeys(username);
        loginFptWait.getPassword().sendKeys(password);
        loginFptWait.getBtnLogin().click();
        //endregion

        //region LOAN CREATION
        //region CHOOSE PERSONAL LOAN
        MainFptWait mainFptWait = new MainFptWait(driver,customerErrorResponse);
        mainFptWait.getMenuApplications("MAIN MENU","Click Open Main Menu").click();
        mainFptWait.getSubmenuPersonalLoan("MAIN MENU", "Click Choose Personal Loan").click();
        //endregion

        //region CREATE NEW APPLICATION
        CreatePersonalLoanWait createPersonalLoanWait = new CreatePersonalLoanWait(driver,customerErrorResponse);
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
         * @fpt_exp_data ("gender", "firstName", "lastName", "middleName", "dateOfBirth", "issuePlace", "maritalStatus", "Highschool")
         */
        //region BASIC INFORMATION
        //List<String> basicInformation = Arrays.asList("Female","MAI","THÀNH","NAM","22/05/1995","HÀ NỘI","Married","Highschool");
        //System.out.println("Quang: " + fptCustomer.getIssuePlace());
        List<String> basicInformation = Arrays.asList(fptCustomer.getGender(),fptCustomer.getFirstName(),fptCustomer.getLastName(),fptCustomer.getMiddleName(),
                fptCustomer.getDateOfBirth(),fptCustomer.getIssuePlace(),fptCustomer.getMaritalStatus(),"Highschool");
        LeadDetailsAppInfoWait leadDetailsAppInfoWait = new LeadDetailsAppInfoWait(driver,customerErrorResponse);
        leadDetailsAppInfoWait.getSelectBoxGender("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION","Click drop box").click();
        leadDetailsAppInfoWait.getSelectOneGender("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL INFORMATION",basicInformation.get(0).trim()).click();
        leadDetailsAppInfoWait.getFirstName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(1).trim()).sendKeys(basicInformation.get(1).trim());
        leadDetailsAppInfoWait.getLastName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(2).trim()).sendKeys(basicInformation.get(2).trim());
        leadDetailsAppInfoWait.getMiddleName("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(3).trim()).sendKeys(basicInformation.get(3).trim());
        leadDetailsAppInfoWait.getDob("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(4).trim()).sendKeys(basicInformation.get(4).trim());
        leadDetailsAppInfoWait.getPlaceOfIssue("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(5).trim()).sendKeys(basicInformation.get(5).trim());
        leadDetailsAppInfoWait.getMaritalStatus("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(6).trim()).click();
        leadDetailsAppInfoWait.getCitizenship("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION","Vietnamese").click();
        leadDetailsAppInfoWait.getEducation("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION",basicInformation.get(7).trim()).click();
        leadDetailsAppInfoWait.getBtnProceed("LEAD_DETAILS__APPLICANT_INFORMATION__PERSONAL_INFORMATION","Click Button Proceed").click();

        //endregion
        System.out.println(username + " BASIC INFORMATION DONE");
        /**
         * @param List<List> identification
         * @x-size 5 [0-4]
         * @y-size no define
         * @detail [0: Document Type , 1: Document Number, 2: Issue Date, 3: Country Of Issue]
         * @default: Expiration Date: null, Country of Issue: Vietnam
         * @fpt_exp_data:
         * ("Current National ID", "nationalId", "issuedDate", "Vietnam")
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

        List<String> a = Arrays.asList("Current National ID",fptCustomer.getNationalId(),fptCustomer.getIssueDate(),"Vietnam");
        List<String> b = Arrays.asList("Insurance Number",fptCustomer.getEmployeeCard(),"","Vietnam");
        List<String> c = Arrays.asList("Family Book Number","0","","Vietnam");
        List<String> d = new ArrayList<>();


        identification.add(a);
        identification.add(b);
        identification.add(c);
        for (FptReference fptReference : fptReferences
        ) {
            if (fptReference.getRelation().equals("Spouse")) {
                d = Arrays.asList("Spouse Current National ID",fptReference.getPersonalId(),"","Vietnam");
                identification.add(d);
                break;
            }
        }

        if(leadDetailsAppInfoWait.getCheckBtnProceedHide("IDENTIFICATION INFORMATION", "Check hide/unhide proceed btn")) {
            leadDetailsAppInfoWait.getExpandIdentification("IDENTIFICATION INFORMATION","Click to expand Identification Info").click();
            leadDetailsAppInfoWait.inputIdentification("IDENTIFICATION INFORMATION",identification);
        }
        //endregion
        System.out.println(username + " IDENTIFICATION INFORMATION DONE");

        /**
         * @param List<List> address
         * @x-size 14 [0-13]
         * @y-size no define
         * @detail [0: Address Type , 1: Country, 2: Region, 3: City/Province, 4: Area/District, 5: Building/Apartment (Address line 1),
         * 6: House&Street (Address line 2), 7: Ward (Address Line 3), 8: Direction,
         * 9: Duration of Residence - Years, 10: Duration of Residence - Months
         * 11: Primary Phone - STD, 12: Primary Phone - NUMBER, 13: Mobile Phone]
         * @default: EXTN: null, Country: Vietnam, PINCODE: default only one value
         * @fpt_exp_data: ("addressType","Vietnam","region","province","district","address1","address2","ward","map","durationYear","durationMonth","","","mobilePhone")
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

        for (FptAddress fptaddress : fptAddresses
        ) {
            if(fptaddress.getAddressType().trim().equals("Current Address")) {
                a1 = Arrays.asList(fptaddress.getAddressType(),"Vietnam","ALL"//fptaddress.getRegion()
                        ,fptaddress.getProvince(),fptaddress.getDistrict(),
                        fptaddress.getAddress1(),fptaddress.getAddress2(), fptaddress.getWard(),fptCustomer.getMap(), fptCustomer.getDurationYear(),
                        fptCustomer.getDurationMonth(),"","",fptCustomer.getMobilePhone());

                address.add(a1);
            }
            if(fptaddress.getAddressType().trim().equals("Family Book Address")) {
                b1 = Arrays.asList(fptaddress.getAddressType(),"Vietnam","ALL"//fptaddress.getRegion()
                        ,fptaddress.getProvince(),fptaddress.getDistrict(),
                        fptaddress.getAddress1(),fptaddress.getAddress2(), fptaddress.getWard(),fptCustomer.getMap(), "",
                        "","","","");

                address.add(b1);
            }
            if(fptaddress.getAddressType().trim().equals("Spouse Address")) {
                c1 = Arrays.asList(fptaddress.getAddressType(),"Vietnam","ALL"//fptaddress.getRegion()
                        ,fptaddress.getProvince(),fptaddress.getDistrict(),
                        fptaddress.getAddress1(),fptaddress.getAddress2(), fptaddress.getWard(),"", "",
                        "","","","");

                address.add(c1);
            }
            if(fptaddress.getAddressType().trim().equals("Working Address")) {
                d1 = Arrays.asList(fptaddress.getAddressType(),"Vietnam","ALL"//fptaddress.getRegion()
                        ,fptaddress.getProvince(),fptaddress.getDistrict(),
                        fptaddress.getAddress1(),fptaddress.getAddress2(), fptaddress.getWard(),"", "",
                        "","","","");

                address.add(d1);
            }
        }
        leadDetailsAppInfoWait.getExpandAddress("ADDRESS INFORMATION","CLICK TO INPUT ADDRESS").click();
        leadDetailsAppInfoWait.inputAddress("ADDRESS INFORMATION",address);
        leadDetailsAppInfoWait.getExpandAddress("ADDRESS INFORMATION","CLICK TO INPUT ADDRESS").click();
        //endregion
        System.out.println(username + " ADDRESS INFORMATION DONE");

        /**
         * @param List<List> family
         * @param String noOfDependents
         * @param String noOfChildren
         * @x-size 14 [0-13]
         * @y-size no define
         * @detail [0: Member Name , 1: Relationship, 2: Phone number, 3: Education Status, 4: Company Name ]
         * @default: Is Dependent: uncheck, Number of Dependents: 0, Number of Children: 0
         * @fpt_exp_data: ("fullName","relation","phoneNumber","","")
         * @note: Education drop box => "" ignore do activity
         */
        //region FAMILY INFORMATION
        //check status marial moi insert
        if(fptCustomer.getMaritalStatus().equals("Married"))
        {
            List<List> family = new ArrayList<>();
            //List<String> a3 = Arrays.asList("NGUYỄN VĂN QUÂN","Spouse","0829403521","","");
            List<String> a3 = new ArrayList<>();
            for (FptReference fptReference : fptReferences
            ) {
                if (fptReference.getRelation().equals("Spouse")) {
                    //System.out.println(fptReference.getRelation().equals("Spouse"));
                    a3 = Arrays.asList(fptReference.getFullName(),"Spouse",fptReference.getPhoneNumber(),"","");
                    family.add(a3);
                    //leadDetailsAppInfoWait.getExpandFamily("FAMILY INFORMATION","Click Family").click();
                    leadDetailsAppInfoWait.inputFamily(family,"0","0","FAMILY INFORMATION", "click to add");
                }
            }
            /*family.add(a3);
            //leadDetailsAppInfoWait.getExpandFamily("FAMILY INFORMATION","Click Family").click();
            leadDetailsAppInfoWait.inputFamily(family,"0","0","FAMILY INFORMATION", "click to add");*/
            //endregion
            System.out.println(username + " FAMILY INFORMATION DONE");
        }
        /**
         * @detail [Choose primary address for communication ]
         * @default: choose "Current Address"
         */
        //region COMMUNICATION INFORMATION
        leadDetailsAppInfoWait.getLoadCust_comm("COMMUNICATION INFORMATION","Load Communication").click();
        leadDetailsAppInfoWait.getBtnPriAdd("COMMUNICATION INFORMATION","Choose Primary Communication").click();
        leadDetailsAppInfoWait.getLoadCust_comm("COMMUNICATION INFORMATION","Load Communication").click();
        //endregion
        System.out.println(username + " COMMUNICATION INFORMATION DONE");
        /**
         * @detail [Mandatory: for moving next stage ]
         * @default: No value
         */
        //region CHECK DEDUPE
        leadDetailsAppInfoWait.getBtnCheckDupde("CHECK DEDUPE","check dedupe").click();
        leadDetailsAppInfoWait.getBtnSavePI("SAVE AND NEXT","SAVE AND NEXT").click();
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
        empDetails = Arrays.asList("Others","T-Manufacturing","Regular Employment","SẢN XUẤT","1","Present","0","1","SAMSUNG");
        LeadDetailsEmpDetailsWait leadDetailsEmpDetailsWait = new LeadDetailsEmpDetailsWait(driver,customerErrorResponse);
        leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS","Get app info").getText();
        System.out.println(username + " " + leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS", "Get app info").getText().trim());
        customerErrorResponse.setField1(leadDetailsEmpDetailsWait.getAppInfo("EMPLOYMENT DETAILS","Get app info").getText());
        leadDetailsEmpDetailsWait.getOccupationType_chzn("EMPLOYMENT DETAILS","Salaried");
        leadDetailsEmpDetailsWait.inputStaffMember("EMPLOYMENT DETAILS", empDetails);

        //endregion
        System.out.println(username + " EMPLOYMENT DETAILS DONE");

        /**
         * @param List<String> empDetails
         * @size 8 [0-7]
         * @detail [0: Product Detail , 1: Good Code, 2: Good Type,
         * 3: Quantity, 4: Good Price, 5: Samsung Credit Limit, 6: Down Payment, 7: Employee card number]
         * @default: click [Employer Address, Select Address: Working Address, Done, Save and Next]
         * @fpt_exp_data: ("model", "goodCode", "goodType", "quantity", "goodPrice","0","downPayment", "employeeCard")
         * Samsung Credit Limit: 0
         */
        //region LEAD DETAILS -> MISC-FPT
        List<String> testLeadDetailsMisc = new ArrayList<>();
        List<List> inputProducts = new ArrayList<>();
        testLeadDetailsMisc = Arrays.asList("SAMSUNG NOTE 9 128G","00484677","Portable","1","17242500","0","0","10004647");
        for (FptProductDetail fptProductDetail : fptProductDetails
        ) {
            inputProducts.add(Arrays.asList(fptProductDetail.getModel(),fptProductDetail.getGoodCode(),
                    fptProductDetail.getGoodType(),fptProductDetail.getQuantity(), fptProductDetail.getGoodPrice()));

        }
        leadDetailsAppInfoWait.btnMiscFpt("LEAD DETAILS", "MISC-FPT");
        LeadDetailsMiscFptWait leadDetailsMiscFptWait = new LeadDetailsMiscFptWait(driver,customerErrorResponse);
        leadDetailsMiscFptWait.inputFPT("LEAD DETAILS -> MISC-FPT",inputProducts,"0",fptLoanDetail.getDownPayment(),fptCustomer.getEmployeeCard());
        //endregion
        System.out.println(username + " LEAD DETAILS -> MISC-FPT DONE");

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
        String appFormNumber = fptCustomer.getCustId().trim();
        System.out.println("Loan Amount: " + fptLoanDetail.getLoanAmount());
        System.out.println("Tenor : " + fptLoanDetail.getTenor());
        testLeadDetailsAppInfo = Arrays.asList("FPT","FPT",appFormNumber,"New Application","CDL_CASH",fptLoanDetail.getProduct(),fptLoanDetail.getLoanAmount(),fptLoanDetail.getTenor(),"OTHER VALUE");
        leadDetailsAppInfoWait.btnLoanDetails("LEAD DETAILS", "LOAN DETAILS");
        LeadDetailsLoanDetailsWait leadDetailsLoanDetailsWait = new LeadDetailsLoanDetailsWait(driver,customerErrorResponse);
        leadDetailsLoanDetailsWait.inputSourcing("LEAD DETAILS -> LOAN DETAILS",testLeadDetailsAppInfo);
        //endregion
        System.out.println(username + " LEAD DETAILS -> LOAN DETAILS DONE");

        /**
         * @param List<String> test_ref
         * @size 3 [0-2]
         * @detail [0: Full Name , 1: Relationship with Borrower, 2: Mobile Phone Number]
         * @fpt_exp_data: ("fullName", "relation", "phoneNumber")
         * @default: no default
         */
        //region LEAD DETAILS -> REFERENCES
        List<List> test_ref = new ArrayList<>();
        //List<String> a6 = Arrays.asList("BÙI VĂN VỆ","Relative","373298133");
        //List<String> b6 = Arrays.asList("HOÀNG THỊ TỐ NGA","Colleague","335990151");
        for (FptReference fptReference : fptReferences
        ) {
            if(!fptReference.getRelation().equals("Spouse")){
                test_ref.add(Arrays.asList(fptReference.getFullName(),fptReference.getRelation(),fptReference.getPhoneNumber()));
            }
            //test_ref.add(Arrays.asList(fptReference.getFullName(),fptReference.getRelation(),fptReference.getPhoneNumber()));
        }
        //test_ref.add(a6);
        //test_ref.add(b6);
        leadDetailsAppInfoWait.btnReferences("LEAD DETAILS", "REFERENCES");
        LeadDetailsReferencesWait leadDetailsReferencesWait = new LeadDetailsReferencesWait(driver,customerErrorResponse);
        leadDetailsReferencesWait.inputReferences("LEAD DETAILS -> REFERENCES",test_ref);
        //endregion
        System.out.println(username + " LEAD DETAILS -> REFERENCES DONE");

        /**
         * @param List<String> test_dtl
         * @size 7 [0-6]
         * @detail [0: Loan Purpose , 1: Number of dependents, 2: Monthly Rental/Mortgage Payment Cost, 3: House Ownership
         * 4: New bank Card Number, 5: Sales Agent Code, 6: Max requested interest rate]
         * @fpt_exp_data: ("Education, sports", "0", "0", "Rented", "1111111111111111", "62")
         * @default: Max requested interest rate: 62
         * Number of dependents: 0, Monthly Rental/Mortgage Payment Cost: 0
         * Loan Purpose: Education, sports, New bank Card Number: 1111111111111111
         */
        //region LEAD DETAILS -> MISC - FRMPDAPTL
        List<String> test_dtl = new ArrayList<>();
        test_dtl = Arrays.asList("Education, sports","0","0","Rented","1111111111111111","FPT000001","62");
        test_dtl = Arrays.asList("Education, sports","0","0","Rented","1111111111111111",fptCustomer.getDsaCode(),"62");
        leadDetailsAppInfoWait.btnMiscAppDtl("LEAD DETAILS", "MISC - FRMAPPDTL");
        LeadDetailsAppDtlWait leadDetailsAppDtlWait = new LeadDetailsAppDtlWait(driver,customerErrorResponse);
        leadDetailsAppDtlWait.inputFrmAppDtl("LEAD DETAILS -> MISC - FRMAPPDTL",test_dtl);
        System.out.println(username + " LEAD DETAILS -> MISC - FRMPDAPTL DONE");

        MoveToNextStageFptWait moveToNextStageFptWait = new MoveToNextStageFptWait(driver, customerErrorResponse);
        moveToNextStageFptWait.moveToNextStage(username,"END OF LEAD DETAILS", "MOVE TO NEXT STAGE", customerErrorResponse);
        //endregion

        //endregion LOAN CREATION


        //QuitFPTWait quitFPTWait = new QuitFPTWait(driver,customerErrorResponse);
        //quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");


        //region FLAG END THREAD
//        for(int i = 0; i< AutomationConstant.userId.size(); i++) {
//            if(AutomationConstant.userId.get(i).get(0).equals(username)) {
//                AutomationConstant.userId.get(i).set(2,"false");
//            }
//        }
//        System.out.println("End thread " + AutomationConstant.userId);
        //endregion


    }
}
