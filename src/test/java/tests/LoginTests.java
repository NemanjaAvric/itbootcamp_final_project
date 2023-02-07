package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.util.List;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    private String invalidPassword;

    private String invalidEmail;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        Faker faker = new Faker();
        invalidEmail = faker.internet().emailAddress();
        invalidPassword = faker.internet().password();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.getLoginButton().click();
    }

    @Test
    public void visitTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void checksInputAttributeTypes() {
        Assert.assertEquals(loginPage.getEmailInputField().getAttribute("type"), "email");
        Assert.assertEquals(loginPage.getPasswordInputField().getAttribute("type"), "password");
    }

    @Test
    public void loginInvalidEmailInvalidPassword() {
        loginPage.login(invalidEmail, invalidPassword);
        Assert.assertEquals(loginPage.getUserDoesNotExistsPopupMessage().getText(), "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void loginValidEmailInvalidPassword() {
        loginPage.login(loginPage.getVALIDEMAIL(), invalidPassword);
        Assert.assertEquals(loginPage.getUserDoesNotExistsPopupMessage().getText(), "Wrong password");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void loginValidEmailValidPassword() {
        loginPage.login(loginPage.getVALIDEMAIL(), loginPage.getVALIDPASSWORD());
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPage.getBuyMeACoffeButton()));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }

    @Test
    public void logout() {
        loginPage.login(loginPage.getVALIDEMAIL(), loginPage.getVALIDPASSWORD());
        Assert.assertTrue(loginPage.getLogoutButton().isDisplayed());
        loginPage.getLogoutButton().click();
        webDriverWait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        driver.manage().window().fullscreen();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driver.findElements(By.className("btnLogout"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }
}
