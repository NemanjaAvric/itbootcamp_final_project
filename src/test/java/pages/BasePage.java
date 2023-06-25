package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utility;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Utility utility = new Utility();

    public BasePage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(this.driver, this);

    }

    public void waitTextToBePresentInElement(WebElement element, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForVisibilityOfElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForURLToContain(String partOfURL) {
        webDriverWait.until(ExpectedConditions.urlContains(partOfURL));
    }

    public void waitForNumberOfElementsToBe(String xpath, int numberOfElements) {
        webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpath), numberOfElements));
    }

    public String getWebElementAttributeValue(WebElement element) {
        return element.getAttribute("value");
    }

    public String getWebElementAttributeType(WebElement element) {
        return element.getAttribute("type");
    }

    public String getWebElementText(WebElement element) {
        return element.getText();
    }

    public boolean webelementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void sendKeysToElement(WebElement element, Keys keyToSend) {
        element.sendKeys(keyToSend);
    }

    public void sendKeysToElement(WebElement element, String keysToSend) {
        element.sendKeys(keysToSend);
    }

    public void sendKeysToElement(WebElement element, Keys keyToSend1, String keysToSend, Keys keyToSend2) {
        element.sendKeys(keyToSend1, keysToSend, keyToSend2);
    }

    public void clearInputField(WebElement element) {
        utility.clickOnWebElement(element);
        sendKeysToElement(element, Keys.CONTROL + "a");
        sendKeysToElement(element, Keys.DELETE);
    }
}
