package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        driverGet(BASEURL);
        driverFullscreen();
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driverFindElementsByClassName("btnLogout");
        if (!listIsEmpty(elements)) {
            utility.clickOnWebElement(elements.get(0));
        }
    }

    @AfterClass
    public void afterClass() {
        driverQuit();
    }

    public void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public void assertTrue(String urlEnding) {
        Assert.assertTrue(driver.getCurrentUrl().endsWith(urlEnding));
    }

    public void assertTrue(WebElement element, String string) {
        Assert.assertTrue(element.getText().contains(string));
    }

    public void assertEquals(String actual, String excpected) {
        Assert.assertEquals(actual, excpected);
    }

    public void driverGet(String url) {
        driver.get(url);
    }

    public void driverFullscreen() {
        driver.manage().window().fullscreen();
    }

    public void driverQuit() {
        driver.quit();
    }

    public List<WebElement> driverFindElementsByClassName(String className) {
        return driver.findElements(By.className(className));
    }

    public boolean listIsEmpty(List<WebElement> list) {
        return list.isEmpty();
    }

    public void driverNavigateTo(String url) {
        driver.navigate().to(url);
    }
}
