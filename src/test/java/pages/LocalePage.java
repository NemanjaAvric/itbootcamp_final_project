package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalePage extends BasePage {
    @FindBy(className = "btnLocaleActivation")
    private WebElement changeLocal;
    @FindBy(className = "btnES")
    private WebElement shapnishLocal;
    @FindBy(className = "btnEN")
    private WebElement ensglishLocal;
    @FindBy(className = "btnFR")
    private WebElement frenchLocal;
    @FindBy(className = "display-2")
    private WebElement pageTitle;

    public LocalePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getShapnishLocal() {
        return shapnishLocal;
    }

    public WebElement getEnsglishLocal() {
        return ensglishLocal;
    }

    public WebElement getFrenchLocal() {
        return frenchLocal;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public void changeLocal(WebElement local) {
        utility.clickOnWebElement(changeLocal);
        utility.clickOnWebElement(local);
    }
}
