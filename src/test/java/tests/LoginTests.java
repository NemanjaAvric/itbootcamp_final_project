package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    private String invalidPassword;

    private String invalidEmail;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        invalidEmail = utility.fakeEmail();
        invalidPassword = utility.fakePassword();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.getLoginButton().click();
    }

    @Test
    public void visitTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith(loginPage.LOGIN_PAGE_URL_ENDING));
    }

    @Test
    public void checksInputAttributeTypes() {
        Assert.assertEquals(loginPage.getWebElementAttributeType(loginPage.getEmailInputField()), "email");
        Assert.assertEquals(loginPage.getWebElementAttributeType(loginPage.getPasswordInputField()), "password");
    }

    @Test
    public void loginInvalidEmailInvalidPassword() {
        loginPage.login(invalidEmail, invalidPassword);
        Assert.assertEquals(loginPage.getWebElementText(loginPage.getUserDoesNotExistsPopupMessage()), "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith(loginPage.LOGIN_PAGE_URL_ENDING));
    }

    @Test
    public void loginValidEmailInvalidPassword() {
        loginPage.login(loginPage.VALID_EMAIL, invalidPassword);
        Assert.assertEquals(loginPage.getWebElementText(loginPage.getUserDoesNotExistsPopupMessage()), "Wrong password");
        Assert.assertTrue(driver.getCurrentUrl().endsWith(loginPage.LOGIN_PAGE_URL_ENDING));
    }

    @Test
    public void loginValidEmailValidPassword() {
        loginPage.loginWait(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().endsWith(HOME_PAGE_URL_ENDING));
    }

    @Test
    public void logout() {
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        Assert.assertTrue(loginPage.webelementIsDisplayed(loginPage.getLogoutButton()));
        loginPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().endsWith(loginPage.LOGIN_PAGE_URL_ENDING));
        driver.get(BASEURL + HOME_PAGE_URL_ENDING);
        Assert.assertTrue(driver.getCurrentUrl().endsWith(loginPage.LOGIN_PAGE_URL_ENDING));
    }
}
