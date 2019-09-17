package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.entity.FptPhoto;
import com.tpf.automation.tpf_automation.utils.Utils;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Getter
public class DocumentsPage {
    private WebDriver _driver;
    @FindBy(how = How.ID, using = "applicationChildTabs_document")
    @CacheLookup
    private WebElement tabDocumentsElement;

    @FindBy(how = How.ID, using = "document")
    @CacheLookup
    private WebElement documentsContainerElement;

    @FindBy(how = How.ID, using = "executeDocumentIntegrationSet")
    @CacheLookup
    private WebElement btnGetDocumentElement;

    @FindBy(how = How.ID, using = "lendingDocumentList")
    @CacheLookup
    private WebElement lendingDocumentElement;

    @FindBy(how = How.XPATH, using = "//tr[contains(@id, 'row')]")
    @CacheLookup
    private List<WebElement> lendingTrElement;

    @FindBy(how = How.XPATH, using = "//select[contains(@id, 'applicationDocument_receiveState')]")
    @CacheLookup
    private List<WebElement> lendingStatusElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@id, 'receivedapplicationDocument_receiveState')]")
    @CacheLookup
    private List<WebElement> lendingPhotoContainerElement;

    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'photoimg')]")
//    @CacheLookup
    private List<WebElement> lendingPhotoElement;

    @FindBy(how = How.ID, using = "submitDocuments")
    @CacheLookup
    private WebElement btnSubmitElement;


    public DocumentsPage(WebDriver driver) {
        this._driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setData(String photoUrl) throws IOException {

        //dang ki local file
        ((RemoteWebDriver) _driver).setFileDetector(new LocalFileDetector());
        //download file
        String fromFile = "https://storage.googleapis.com/stage-idvay-exports/users/ULOMVOLMNDEF4T3PJU2HIMVZRDA/NATIONAL_ID.pdf?GoogleAccessId=idvay-agent@idvay-stage.iam.gserviceaccount.com&Expires=1563182900&Signature=a0cAUvXHb%2F2cXqfmkzbbHJLq4ZGd8QZMvffn%2Fw6Ta%2BRLdSkIZapjuWWCmDMkbs1gJQHj6mFQidYGAadtePr89PUtIm%2Bw33Y1kOsNA6KK8E1MtSrREaBpKbI1e%2Fn6Ew57upZv4sQl29%2BeIqVGtQhnPKPEcIdFHPV8jyXaTnN86yVdBeYosYt3KB6EkC1urFdM9FSYkc59CiVLByaZARc9%2BsRgKi4F%2FT0DFtGREJ3yB9MUP77a1tzWs%2B2EYKsdcG2RHGSRsHK2cRVpYEs61UBXtrEJocwHpVBiih6TBsvpU%2BgqojYEGDjlCHxBzG59oAXi38aDe0ExCxMIe2I9eaao6g%3D%3D";
        String toFile = Utils.SCREENSHOT_PRE_PATH + "finnone.png" ;
        FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
//        byte[] fileContent = FileUtils.readFileToByteArray(new File(toFile));
//        String encodedString = Base64.getEncoder().encodeToString(fileContent);
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        FileUtils.writeByteArrayToFile(new File(Utils.SCREENSHOT_PRE_PATH_DOCKER +"/donwload_vinid.png"), decodedBytes);

        File file =new File(toFile);
        photoUrl=file.getAbsolutePath();
        System.out.println("Exist:" + file.exists());
        System.out.println("Path:" + file.getAbsolutePath());

        int index = 0;
        List<String> requiredFiled = Arrays.asList("TPF_ID Card", "TPF_Customer Photograph", "TPF_Employee_Card", "TPF_Family Book"); //
        // TPF_Family Book is not required
//        List<String> requiredFiled = Arrays.asList("TPF_Application cum Credit Contract (ACCA)", "TPF_ID Card", "TPF_Customer Photograph",
//                "TPF_Customer Signature", "TPF_Income Proof");
        for(WebElement element : lendingTrElement) {
            final int _tempIndex = index;
            //get image
            if(element.getAttribute("data-documentcode").equals("TPF_ID Card"))
            {
                //String fromFile
            }

            if (requiredFiled.contains(element.getAttribute("data-documentcode"))) {
                new Select(lendingStatusElement.get(index)).selectByVisibleText("Received");
                await("Load lendingPhotoContainerElement Timeout!").atMost(30, TimeUnit.SECONDS)
                        .until(() ->  lendingPhotoContainerElement.get(_tempIndex).isDisplayed());
                lendingPhotoElement.get(_tempIndex).sendKeys(photoUrl);
                Utils.captureScreenShot(_driver);
            }
            index++;
        }

        System.out.println(file.delete());

    }

    public void setData(List<FptPhoto> photoList,String custID) throws IOException {

        //dang ki local file
        ((RemoteWebDriver) _driver).setFileDetector(new LocalFileDetector());
        //download file

//        byte[] fileContent = FileUtils.readFileToByteArray(new File(toFile));
//        String encodedString = Base64.getEncoder().encodeToString(fileContent);
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        FileUtils.writeByteArrayToFile(new File(Utils.SCREENSHOT_PRE_PATH_DOCKER +"/donwload_vinid.png"), decodedBytes);

        String fileID="";
        String fileSelfie="";
        String fileEmployeeCard="";

        for (FptPhoto f : photoList) {
            if(f.getDocumentType().equals("selfie")){
                String fromFile = f.getLink().replace("https://calllogoutsidebeta.fptshop.com.vn","https://outsidepaymenttest.tpb.vn/fpttpbank");
                String toFile = Utils.SCREENSHOT_PRE_PATH_DOCKER + "/" +  custID + "_selfie.png" ;
                System.out.println("FromFile:" + fromFile);
                System.out.println("ToFile:" + fromFile);

                FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
                File file =new File(toFile);
                fileSelfie=file.getAbsolutePath();
                System.out.println("Exist fileSelfie:" + file.exists());
                System.out.println("Exist fileSelfie:" + file.getAbsolutePath());
            }

            if(f.getDocumentType().equals("employeecard")){
                String fromFile = f.getLink().replace("https://calllogoutsidebeta.fptshop.com.vn","https://outsidepaymenttest.tpb.vn/fpttpbank");
                String toFile = Utils.SCREENSHOT_PRE_PATH_DOCKER + "/" +  custID + "_employeecard.png" ;
                FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
                File file =new File(toFile);
                fileEmployeeCard=file.getAbsolutePath();
                System.out.println("Exist fileEmployeeCard:" + file.exists());
                System.out.println("Exist fileEmployeeCard:" + file.getAbsolutePath());
            }

            if(f.getDocumentType().equals("National_ID" +
                    "" +
                    "")){
                String fromFile = f.getLink().replace("https://calllogoutsidebeta.fptshop.com.vn","https://outsidepaymenttest.tpb.vn/fpttpbank");
                String toFile = Utils.SCREENSHOT_PRE_PATH_DOCKER + "/" +  custID + "_National_ID.png" ;
                FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
                File file =new File(toFile);
                fileID=file.getAbsolutePath();
                System.out.println("Exist fileID:" + file.exists());
                System.out.println("Exist fileID:" + file.getAbsolutePath());
            }
        }

        int index = 0;
        List<String> requiredFiled = Arrays.asList("TPF_ID Card", "TPF_Customer Photograph", "TPF_Employee_Card", "TPF_Family Book"); //
        // TPF_Family Book is not required
//        List<String> requiredFiled = Arrays.asList("TPF_Application cum Credit Contract (ACCA)", "TPF_ID Card", "TPF_Customer Photograph",
//                "TPF_Customer Signature", "TPF_Income Proof");
        for(WebElement element : lendingTrElement) {
            final int _tempIndex = index;

            if (requiredFiled.contains(element.getAttribute("data-documentcode"))) {
                //get image
                 new Select(lendingStatusElement.get(index)).selectByVisibleText("Received");
                await("Load lendingPhotoContainerElement Timeout!").atMost(30, TimeUnit.SECONDS)
                        .until(() ->  lendingPhotoContainerElement.get(_tempIndex).isDisplayed());

                if(element.getAttribute("data-documentcode").equals("TPF_ID Card"))
                {
                    lendingPhotoElement.get(_tempIndex).sendKeys(fileID);
                }

                if(element.getAttribute("data-documentcode").equals("TPF_Customer Photograph"))
                {
                    lendingPhotoElement.get(_tempIndex).sendKeys(fileSelfie);
                }

                if(element.getAttribute("data-documentcode").equals("TPF_Employee_Card"))
                {
                    lendingPhotoElement.get(_tempIndex).sendKeys(fileEmployeeCard);
                }

                Utils.captureScreenShot(_driver);
            }
            index++;
        }

        //System.out.println(file.delete());
    }
}
