package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class TestBase {
    public static WebDriver driver;
    protected static WebDriver vmFrame;
    protected static WebDriver mpiFrame;

    @BeforeTest
    public void start (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://dev-testing.andalusiagroup.net:7090/");
    }

    public WebDriver vmFrame(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
        vmFrame = this.driver.switchTo().frame(this.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div/div/div/iframe")));
        return vmFrame;
    }

    public WebDriver mpiFrame(){
        vmFrame.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        mpiFrame = vmFrame.switchTo().frame(this.driver.findElement(By.xpath("/html/body/div[10]/div[2]/iframe")));
        return mpiFrame;
    }

//    @AfterTest
//    public void Exit(){
//        driver.close();
//    }

}
