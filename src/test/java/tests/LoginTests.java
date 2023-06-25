package tests;

import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    private String invalidPassword;

    private String invalidEmail;

    private final String EXCPECTED_EMAIL_INPUT_FIELD_ATTRIBUTE_TYPE = "email";

    private final String EXCPECTED_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE = "password";

    private final String EXCPECTED_USER_DOES_NOT_EXIST_POP_UP_MESSAGE_TEXT = "User does not exists";

    private final String EXCPECTED_WRONG_PASSWORD_MESSAGE = "Wrong password";

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
        utility.clickOnWebElement(loginPage.getLoginButton());
    }

    @Test
    public void visitTheLoginPage() {
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void checksInputAttributeTypes() {
        assertEquals(loginPage.getWebElementAttributeType(loginPage.getEmailInputField()), EXCPECTED_EMAIL_INPUT_FIELD_ATTRIBUTE_TYPE);
        assertEquals(loginPage.getWebElementAttributeType(loginPage.getPasswordInputField()), EXCPECTED_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE);
    }

    @Test
    public void loginInvalidEmailInvalidPassword() {
        loginPage.login(invalidEmail, invalidPassword);
        assertEquals(loginPage.getWebElementText(loginPage.getUserDoesNotExistsPopupMessage()), EXCPECTED_USER_DOES_NOT_EXIST_POP_UP_MESSAGE_TEXT);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void loginValidEmailInvalidPassword() {
        loginPage.login(loginPage.VALID_EMAIL, invalidPassword);
        assertEquals(loginPage.getWebElementText(loginPage.getUserDoesNotExistsPopupMessage()), EXCPECTED_WRONG_PASSWORD_MESSAGE);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void loginValidEmailValidPassword() {
        loginPage.loginWait(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        assertTrue(HOME_PAGE_URL_ENDING);
    }

    @Test
    public void logout() {
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        assertTrue(loginPage.webelementIsDisplayed(loginPage.getLogoutButton()));
        loginPage.logout();
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
        driverGet(BASEURL + HOME_PAGE_URL_ENDING);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }
}
