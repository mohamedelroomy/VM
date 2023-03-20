package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class Login extends PageBase {
    public Login(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement usernameTextField;

    @FindBy(id = "password")
    WebElement passTextField;

    @FindBy(xpath = "/html/body/div/div[3]/div[1]/div/div[2]/div[1]/div/span/div[3]/button")
    WebElement loginBTN;



    public void insertUserNameAndPass (String username,String pass)  {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(600));
        insertText(usernameTextField,username);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).build().perform();
        action.release().perform();
        insertText(passTextField,pass);
        clickOnBTN(loginBTN);
    }
}
