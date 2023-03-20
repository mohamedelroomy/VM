package Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CompletePatientInfo extends PageBase{
    public CompletePatientInfo(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "FirstNameA")
    WebElement arabicFirstName;
    @FindBy (id = "SecondNameA")
    WebElement arabicSecondName;
    @FindBy (id = "ThirdNameA")
    WebElement arabicThirdName;
    @FindBy (id = "FourthNameA")
    WebElement arabicFourthName;
    @FindBy (id = "FirstNameE")
    WebElement englishFirstName;
    @FindBy (id = "SecondNameE")
    WebElement englishSecondName;
    @FindBy (id = "ThirdNameE")
    WebElement englishThirdName;
    @FindBy (id = "FourthNameE")
    WebElement englishFourthName;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[3]/div[2]/div[6]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/input")
    WebElement years;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[3]/div[2]/div[6]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/input")
    WebElement months;

    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[3]/div[2]/div[8]/div/div[2]/code-filtered-ddl/div[1]/div/input")
    WebElement genderTextBox;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[3]/div[2]/div[9]/div/div[2]/code-filtered-ddl/div[1]/div/input")
    WebElement maritalStatus;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[4]/div[2]/div/div[1]/div[1]/address-ddl/div[3]/div/div[2]/div[1]/div/input")
    WebElement regionCode;
    @FindBy (id = "SaveAndClose")
    WebElement saveAndClose;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[2]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div/input")
    WebElement phoneNumber;
    @FindBy(xpath = "loader")
    WebElement loader;

    public void waitForLoader(){
        new WebDriverWait(driver,Duration.ofSeconds(4000)).until(ExpectedConditions.attributeContains(loader,"class","ng-scope ng-hide"));
    }
    public void waitLoaderHide() throws InterruptedException {
        String attributeStatus;
        boolean found = false;
        try {
            while (!found) {
                attributeStatus = loader.getAttribute("class");
                if (attributeStatus.contains("ng-scope ng-hide")) {
                    found = true;
                } else {
                    Thread.sleep(1500);
                    attributeStatus = loader.getAttribute("ng-show");
                }
            }
        }
        catch (NoSuchElementException e){
            return;
        }
    }



    public void waitLoaderAppear() throws InterruptedException {
        String attributeStatus;
        boolean found = false;
        try {
            while (!found) {

                attributeStatus = loader.getAttribute("class");
                if (attributeStatus.contains("ng-scope")) {
                    found = true;
                } else {
                    Thread.sleep(1500);
                    attributeStatus = loader.getAttribute("ng-show");
                }
            }
        }
        catch (NoSuchElementException e){
            return;
        }
    }
    public void insertArabicPatientName (String fName, String secName,String thirdName,String fourthName){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        new WebDriverWait(driver,Duration.ofSeconds(300)).until(ExpectedConditions.visibilityOf(arabicFirstName));
        insertText(arabicFirstName,fName);
        insertText(arabicSecondName,secName);
        insertText(arabicThirdName,thirdName);
        insertText(arabicFourthName,fourthName);
    }

    public void setAge (String year, String month){
        insertText(years,year);
        insertText(months,month);
    }

    public void insertEnglishPatientName (String fName, String secName,String thirdName,String fourthName){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        insertText(englishFirstName,fName);
        insertText(englishSecondName,secName);
        insertText(englishThirdName,thirdName);
        insertText(englishFourthName,fourthName);
    }

    public void selectGender (char gender){
        String stringGender =String.valueOf(gender);
        insertText(genderTextBox,stringGender);
    }

    public void selectMaritalStatus (char status){
        String stringStatus =String.valueOf(status);
        insertText(maritalStatus, stringStatus);
    }

    public void selectRegionCode (String code){
        insertText(regionCode, code);
    }

    public void insertPhoneNumber(String phone) {
        phoneNumber.clear();
        insertText(phoneNumber,phone);

    }

    public void saveAndClose () throws InterruptedException {
        clickOnBTN(saveAndClose);
        Thread.sleep(4000);
    }
}
