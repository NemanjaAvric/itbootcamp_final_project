package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utility.Utility;

import java.time.Duration;
import java.util.List;

public abstract class BaseTest {
    protected WebDriver driver;

    protected WebDriverWait webDriverWait;
    protected final String BASEURL = "https://vue-demo.daniel-avellaneda.com";
    protected final String HOME_PAGE_URL_ENDING = "/home";
    protected Utility utility;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        utility = new Utility();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASEURL);
        driver.manage().window().fullscreen();
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driver.findElements(By.className("btnLogout"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
