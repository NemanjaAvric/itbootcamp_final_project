package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignupPage;

public class SignupTests extends BaseTest {
    private SignupPage signupPage;

    private final String EXCPECTED_EMAIL_INPUT_FIELD_ATTRIBUTE_TYPE = "email";
    private final String EXCPECTED_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE = "password";
    private final String EXCPECTED_CONFIRM_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE = "password";

    private final String EXCPECTED_EMAIL_ALREADY_EXISTS_MESSAGE = "E-mail already exists";

    private final String EMAIL_THAT_ALREADY_EXISTS = "admin@admin.com";


    private String name;

    private String password;

    private String email;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signupPage = new SignupPage(driver, webDriverWait);
        name = utility.fakeName();
        password = utility.fakePassword();
        email = utility.fakeEmail();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        utility.clickOnWebElement(signupPage.getSignupButton());
    }

    @Test
    public void visitTheLoginPage() {
        assertTrue(signupPage.SIGN_UP_PAGE_URL_ENDING);
    }

    @Test
    public void checksInputAttributeTypes() {
        assertEquals(signupPage.getWebElementAttributeType(signupPage.getEmailInputField()), EXCPECTED_EMAIL_INPUT_FIELD_ATTRIBUTE_TYPE);
        assertEquals(signupPage.getWebElementAttributeType(signupPage.getPasswordInputField()), EXCPECTED_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE);
        assertEquals(signupPage.getWebElementAttributeType(signupPage.getConfirmPasswordField()), EXCPECTED_CONFIRM_PASSWORD_INPUT_FIELD_ATTRIBUTE_TYPE);

    }

    @Test
    public void displaysErrorsWhenUserAlreadyExists() {
        signupPage.signUp(name, EMAIL_THAT_ALREADY_EXISTS, password);
        assertEquals(signupPage.getWebElementText(signupPage.getEmailAlreadyExistsMessage()), EXCPECTED_EMAIL_ALREADY_EXISTS_MESSAGE);
        assertTrue(signupPage.SIGN_UP_PAGE_URL_ENDING);
    }

    @Test
    public void signUp() {
        signupPage.signUpWait(name, email, password);
        assertEquals(signupPage.getWebElementText(signupPage.getVerifyYourAccountMessage()), signupPage.getEXCPECTED_VERIFY_YOUR_ACCOUNT_MESSAGE());
        utility.clickOnWebElement(signupPage.getCloseButton());
    }
}
