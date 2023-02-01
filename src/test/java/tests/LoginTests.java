package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
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
    public void checksInputTypes() {
        Assert.assertEquals(loginPage.getEmailInputField().getAttribute("type"), "email");
        Assert.assertEquals(loginPage.getPasswordInputField().getAttribute("type"), "password");
    }

    @Test
    public void loginInvalidUsernameInvalidPassword() {
        loginPage.loginInvalidUsernameInvalidPassword();
    }

    @Test
    public void loginValidUsernameInvalidPassword() {
        loginPage.loginValidUsernameInvalidPassword();
    }

    @Test
    public void loginValidUsernameValidPassword() {
        loginPage.loginValidUsernameValidPassword();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void logout() {
        loginPage.logout();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void afterMethod() {
        //   webDriverWait.until(ExpectedConditions.visibilityOf(loginPage.getBuyMeACoffeButton()));
        if (driver.getCurrentUrl().endsWith("/home)") && loginPage.getLogoutButton().isDisplayed() && loginPage.getLogoutButton().getText().equals(" Logout ")) {
            loginPage.getLogoutButton().click();
        }
    }
}
