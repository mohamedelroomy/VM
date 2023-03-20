package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dashboard extends PageBase{
    public Dashboard(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "/html/body/div[1]/div[3]/header/div[1]/div[2]/div/a")
    WebElement menuBTN;

    @FindBy (xpath = "/html/body/div[1]/div[3]/header/div[1]/div[4]/ul/li[20]/a")
    WebElement VM;

    @FindBy (id = "endDirBtn")
    WebElement endBTN;

    @FindBy(linkText = "Manage Patient Visits")
    WebElement managePatientVisits;


    public  String getDashboardURL(){
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div/div/div/div/h4")));
        return driver.getCurrentUrl();
    }

    public void navigateToVM () throws InterruptedException {
        clickOnBTN(menuBTN);
        while (!VM.isDisplayed()){
            endBTN.click();
            Thread.sleep(100);
        }
        VM.click();
        managePatientVisits.click();
    }
}
