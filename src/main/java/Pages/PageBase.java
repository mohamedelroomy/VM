package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
    protected static WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void insertText (WebElement element, String txt){

        element.sendKeys(txt);
    }

    public void clickOnBTN (WebElement element){
        element.click();
    }
}
