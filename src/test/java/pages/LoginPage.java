package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]")
    private WebElement loginButton;
    @FindBy(id = "email")
    private WebElement emailInputField;
    @FindBy(id = "password")
    private WebElement passwordInputField;
    @FindBy(css = "button[type='submit']")
    private WebElement loginPageLoginButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li")
    private WebElement userDoesNotExistsPopupMessage;
    @FindBy(className = "bmc-button")
    private WebElement buyMeACoffeButton;
    @FindBy(className = "btnLogout")
    private WebElement logoutButton;

    private final String VALIDEMAIL = "admin@admin.com";

    private final String VALIDPASSWORD = "12345";

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getEmailInputField() {
        return emailInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getUserDoesNotExistsPopupMessage() {
        return userDoesNotExistsPopupMessage;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getBuyMeACoffeButton() {
        return buyMeACoffeButton;
    }

    public String getVALIDEMAIL() {
        return VALIDEMAIL;
    }

    public String getVALIDPASSWORD() {
        return VALIDPASSWORD;
    }

    public void login(String email, String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        loginPageLoginButton.click();
    }
}
