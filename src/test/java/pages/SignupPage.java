package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]")
    private WebElement signupButton;
    @FindBy(id = "name")
    private WebElement nameInputField;
    @FindBy(id = "email")
    private WebElement emailInputField;
    @FindBy(id = "password")
    private WebElement passwordInputField;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")
    private WebElement signMeUpButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]/ul/li")
    private WebElement emailAlreadyExistsMessage;
    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[1]")
    private WebElement verifyYourAccountMessage;
    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[3]/button/span")
    private WebElement closeButton;
    public final String SIGN_UP_PAGE_URL_ENDING = "/signup";

    private final String EXCPECTED_VERIFY_YOUR_ACCOUNT_MESSAGE = "IMPORTANT: Verify your account";

    public String getEXCPECTED_VERIFY_YOUR_ACCOUNT_MESSAGE() {
        return EXCPECTED_VERIFY_YOUR_ACCOUNT_MESSAGE;
    }

    public SignupPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getEmailInputField() {
        return emailInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public WebElement getEmailAlreadyExistsMessage() {
        return emailAlreadyExistsMessage;
    }

    public WebElement getVerifyYourAccountMessage() {
        return verifyYourAccountMessage;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public void signUp(String firstNameLastName, String email, String password) {
        sendKeysToElement(nameInputField, firstNameLastName);
        sendKeysToElement(emailInputField, email);
        sendKeysToElement(passwordInputField, password);
        sendKeysToElement(confirmPasswordField, password);
        utility.clickOnWebElement(signMeUpButton);
    }

    public void signUpWait(String firstNameLastName, String email, String password) {
        signUp(firstNameLastName, email, password);
        waitTextToBePresentInElement(verifyYourAccountMessage, EXCPECTED_VERIFY_YOUR_ACCOUNT_MESSAGE);
    }
}
