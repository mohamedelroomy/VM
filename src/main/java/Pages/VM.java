package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class VM extends PageBase{
    public VM(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/span")
    WebElement managePatientVisitsHeader;
    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/div[1]")
    WebElement loader;
    @FindBy (id = "ClassificationTooltip")
    WebElement visitDDl;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div/div[6]/div[1]/button")
    WebElement addNewBTN;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div[2]/div[2]/div[4]/div[3]/div[1]/div[1]/div[2]/div[2]/div[2]/button")
    WebElement completePatientInfo;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div[2]/div[2]/div[4]/div[3]/div[1]/div[3]/div[2]/div[2]/div[2]/button/span")
    WebElement createVisitAndConfirmArrivalBTN;
    @FindBy (xpath = "/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div[5]/div[1]/div[1]/div[2]/button[2]")
    WebElement addService;
    @FindBy (id = "Classification-DDL")
    WebElement classificationDDL;
    @FindBy (id = "medicationID")
    WebElement serviceTXT;
    @FindBy (id = "medicationID_listbox")
    WebElement serviceList;
    @FindBy (xpath = "/html/body/div[44]/div/div/div/form[1]/div[2]/div[9]/button[2]")
    WebElement addBTN_in_servicePOPUP;
    @FindBy (xpath = "/html/body/div[44]/div/div/div/form[2]/div[3]/div[1]/button[2]")
    WebElement saveBTN_in_servicePOPUP;

    public String getManagePatientVisitsHeader(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return managePatientVisitsHeader.getText();
    }
    public void waitForLoader(){
        new WebDriverWait(driver,Duration.ofSeconds(4000)).until(ExpectedConditions.attributeContains(loader,"class","k-loading-image loaderVMView ng-scope ng-hide"));
    }
    public void waitLoaderAppear() throws InterruptedException {
        String attributeStatus;
        boolean found = false;
        try {
            while (!found) {

                attributeStatus = loader.getAttribute("class");
                if (attributeStatus.equalsIgnoreCase("k-loading-image loaderVMView ng-scope")) {
                    found = true;
                } else {
                    Thread.sleep(1500);
                }
            }
        }
        catch (NoSuchElementException e){
            return;
        }
    }
    public void waitLoaderHide() throws InterruptedException {
        String attributeStatus;
        boolean found = false;
        try {
            while (!found) {
                attributeStatus = loader.getAttribute("class");
                if (attributeStatus.equalsIgnoreCase("k-loading-image loaderVMView ng-scope ng-hide")) {
                    found = true;
                } else {
                    Thread.sleep(1500);
                }
            }
        }
        catch (NoSuchElementException e){
            return;
        }
    }
    public void selectVisitClass (int classID) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", visitDDl);
        switch (classID) {
            case 1:
                driver.findElement(By.xpath("/html/body/div[43]/div/div[2]/div/div[2]/div/button")).click();
                break;
            case 2:
                driver.findElement(By.xpath("/html/body/div[43]/div/div[2]/div/div[3]/div/button")).click();
                break;
            case 3:
                driver.findElement(By.xpath("/html/body/div[43]/div/div[2]/div/div[4]/div/button")).click();
                break;
            case 4:
                driver.findElement(By.xpath("/html/body/div[43]/div/div[2]/div/div[5]/div/button")).click();
                break;
            default:
                System.out.println("you should insert value between 1 and 4");
        }
    }
    public void addNewVirtualVisit() throws InterruptedException {
        Thread.sleep(2000);
        Actions ac = new Actions(driver);
        ac.moveToElement(addNewBTN);
        ac.click().build().perform();
    }
    public void clickOnCompletePatientInfo(){
        completePatientInfo.click();
    }
    public  void createVisitAndConfirmArrival () throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/div[4]/div[3]/div[1]/div[3]/div[2]/div[2]/div[2]/button")));
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/div[4]/div[3]/div[1]/div[3]/div[2]/div[2]/div[2]/button")));
        clickOnBTN(createVisitAndConfirmArrivalBTN);
    }
    public void addLabService (String serviceName) throws InterruptedException {
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOf(addService));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", addService);
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.elementToBeClickable(classificationDDL));
        new WebDriverWait(driver,Duration.ofSeconds(700)).until(ExpectedConditions.attributeContains(classificationDDL,"aria-disabled","false"));
        clickOnBTN(serviceTXT);
        insertText(serviceTXT,serviceName);
        new WebDriverWait(driver,Duration.ofSeconds(700)).until(ExpectedConditions.attributeContains(serviceTXT,"aria-busy","false"));
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOf(serviceList));
        List<WebElement> li = serviceList.findElements(By.tagName("li"));
        Thread.sleep(500);
        clickOnBTN(li.get(0));
        new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.elementToBeClickable(addBTN_in_servicePOPUP));
        clickOnBTN(addBTN_in_servicePOPUP);
        js.executeScript("arguments[0].click()", saveBTN_in_servicePOPUP);
    }
}
