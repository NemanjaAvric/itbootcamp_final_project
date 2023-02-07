package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    protected WebDriverWait webDriverWait;
    protected final String BASEURL = "https://vue-demo.daniel-avellaneda.com";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webriver.driver.chromedriver", "C:\\Nova fascikla\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASEURL);
        driver.manage().window().fullscreen();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
