package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignupPage;

public class SignupTests extends BaseTest {
    private SignupPage signupPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signupPage = new SignupPage(driver, webDriverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        signupPage.getSignupButton().click();
    }

    @Test
    public void visitTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith(signupPage.SIGN_UP_PAGE_URL_ENDING));
    }

    @Test
    public void checksInputAttributeTypes() {
        Assert.assertEquals(signupPage.getWebElementAttributeType(signupPage.getEmailInputField()), "email");
        Assert.assertEquals(signupPage.getWebElementAttributeType(signupPage.getPasswordInputField()), "password");
        Assert.assertEquals(signupPage.getWebElementAttributeType(signupPage.getConfirmPasswordField()), "password");
    }

    @Test
    public void displaysErrorsWhenUserAlreadyExists() {
        signupPage.signUp("Test Test", "admin@admin.com", "123654");
        Assert.assertEquals(signupPage.getWebElementText(signupPage.getEmailAlreadyExistsMessage()), "E-mail already exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith(signupPage.SIGN_UP_PAGE_URL_ENDING));
    }

    @Test
    public void signUp() {
        signupPage.signUpWait("Nemanja Avrić", "avricnemanja97@gmail.com", "fearIsTheMindKiller");
        Assert.assertEquals(signupPage.getWebElementText(signupPage.getVerifyYourAccountMessage()), "IMPORTANT: Verify your account");
        signupPage.getCloseButton().click();
    }
}
