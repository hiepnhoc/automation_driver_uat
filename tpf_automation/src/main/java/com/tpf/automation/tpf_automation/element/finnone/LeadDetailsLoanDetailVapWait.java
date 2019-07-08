package com.tpf.automation.tpf_automation.element.finnone;

import com.tpf.automation.tpf_automation.entity.vin.MomoInsurance;
import com.tpf.automation.tpf_automation.utils.Utils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;

@Getter
public class LeadDetailsLoanDetailVapWait {
    private WebDriver _driver;

    @FindBy(how = How.ID, using = "vapDetailsLiId")
    @CacheLookup
    private WebElement tabVapDetailsElement;

    @FindBy(how = How.ID, using = "vapDetails")
    @CacheLookup
    private WebElement vapDetailsDivContainerElement;

    @FindBy(how = How.ID, using = "vapProduct_chzn")
    @CacheLookup
    private WebElement vapProductElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@id, 'vapProduct_chzn_o_')]")
    @CacheLookup
    private List<WebElement> vapProductOptionElement;

    @FindBy(how = How.ID, using = "treatmentType_chzn")
    @CacheLookup
    private WebElement vapTreatmentElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@id, 'treatmentType_chzn_o_')]")
    @CacheLookup
    private List<WebElement> vapTreatmentOptionElement;

    @FindBy(how = How.ID, using = "insuranceCompany_chzn")
    @CacheLookup
    private WebElement insuranceCompanyElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@id, 'insuranceCompany_chzn_o_')]")
    @CacheLookup
    private List<WebElement> insuranceCompanyOptionElement;

    @FindBy(how = How.ID, using = "doneButton")
    @CacheLookup
    private WebElement doneBtnElement;

    @FindBy(how = How.ID, using = "employmentsaveAndNextButton1")
    @CacheLookup
    private WebElement btnSaveAndNextElement;

    public LeadDetailsLoanDetailVapWait(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public void setData(MomoInsurance momoInsurance) {
        //vap product
//        await("vapProductElement loading timeout").atMost(30, TimeUnit.SECONDS)
//                .until(() -> vapProductElement.isDisplayed());
        vapProductElement.click();
        await("vapProductOptionElement loading timeout").atMost(30, TimeUnit.SECONDS)
                .until(() -> vapProductOptionElement.size() > 0);
        for (WebElement element : vapProductOptionElement) {
            if (element.getText().equals(momoInsurance.getVapProduct())) {
                element.click();
                break;
            }
        }
        Utils.captureScreenShot(_driver);
        //vap treatment
        await("vapTreatmentElement loading timeout").atMost(30, TimeUnit.SECONDS)
                .until(() -> vapTreatmentElement.isDisplayed());
        vapTreatmentElement.click();
        Utils.captureScreenShot(_driver);
        await("vapTreatmentElementOptionElement loading timeout").atMost(30, TimeUnit.SECONDS)
                .until(() -> vapTreatmentOptionElement.size() > 0);
        for (WebElement element : vapTreatmentOptionElement) {
            if (element.getText().equals(momoInsurance.getVapTreatment())) {
                element.click();
                break;
            }
        }
        Utils.captureScreenShot(_driver);

        //insurance company
        await("insuranceCompanyElement loading timeout").atMost(30, TimeUnit.SECONDS)
                .until(() -> insuranceCompanyElement.isDisplayed());
        insuranceCompanyElement.click();
        await("insuranceCompanyElementOptionElement loading timeout").atMost(30, TimeUnit.SECONDS)
                .until(() -> insuranceCompanyOptionElement.size() > 0);
        for (WebElement element : insuranceCompanyOptionElement) {
            if (element.getText().equals(momoInsurance.getInsuranceCompany())) {
                element.click();
                break;
            }
        }

        doneBtnElement.click();
    }
}
