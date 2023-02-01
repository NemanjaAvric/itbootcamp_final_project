package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]")
    //*[@id="app"]/div[1]/div/header/div/div[3]/a[3]
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

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getBuyMeACoffeButton() {
        return buyMeACoffeButton;
    }

    public WebElement getLoginPageLoginButton() {
        return loginPageLoginButton;
    }

    public void loginInvalidUsernameInvalidPassword() {
        Faker faker = new Faker();
        emailInputField.sendKeys(faker.internet().emailAddress());
        passwordInputField.sendKeys(faker.internet().password());
        loginPageLoginButton.click();
        Assert.assertEquals(userDoesNotExistsPopupMessage.getText(), "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginValidUsernameInvalidPassword() {
        Faker faker = new Faker();
        emailInputField.sendKeys("admin@admin.com");
        passwordInputField.sendKeys(faker.internet().password());
        loginPageLoginButton.click();
        //   webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/button/span"), "Close"));
        Assert.assertEquals(userDoesNotExistsPopupMessage.getText(), "Wrong password");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginValidUsernameValidPassword() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        emailInputField.sendKeys("admin@admin.com");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        passwordInputField.sendKeys("12345");
        loginPageLoginButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(buyMeACoffeButton));
        //  Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }

    public void logout() {
        loginValidUsernameValidPassword();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //   webDriverWait.until(ExpectedConditions.visibilityOf(buyMeACoffeButton));
        Assert.assertTrue(logoutButton.isDisplayed());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logoutButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPageLoginButton));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        driver.manage().window().fullscreen();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
