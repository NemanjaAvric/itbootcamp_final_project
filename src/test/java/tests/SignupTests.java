package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignupPage;

import java.util.List;

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
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signupPage.getSignupButton()));
        signupPage.getSignupButton().click();
    }

    @Test
    public void visitTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void checksInputAttributeTypes() {
        Assert.assertEquals(signupPage.getEmailInputField().getAttribute("type"), "email");
        Assert.assertEquals(signupPage.getPasswordInputField().getAttribute("type"), "password");
        Assert.assertEquals(signupPage.getConfirmPasswordField().getAttribute("type"), "password");
    }

    @Test
    public void displaysErrorsWhenUserAlreadyExists() {
        signupPage.displaysErrorsWhenUserAlreadyExists();
        Assert.assertEquals(signupPage.getEmailAlreadyExistsMessage().getText(), "E-mail already exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void signUp() {
        signupPage.signUp("Nemanja Avrić", "avricnemanja94@gmail.com", "fearIsTheMindKiller");
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(signupPage.getVerifyYourAccountMessage(), "IMPORTANT: Verify your account"));
        Assert.assertEquals(signupPage.getVerifyYourAccountMessage().getText(), "IMPORTANT: Verify your account");
        signupPage.getCloseButton().click();
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driver.findElements(By.className("btnLogout"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }


}
