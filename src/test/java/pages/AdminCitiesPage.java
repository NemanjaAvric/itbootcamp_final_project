package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminCitiesPage extends BasePage {

    @FindBy(className = "btnAdmin")
    private WebElement adminButton;
    @FindBy(className = "btnAdminCities")
    private WebElement citiesButton;
    @FindBy(className = "btnNewItem")
    private WebElement newItemButton;
    @FindBy(id = "name")
    private WebElement newItemNameInputField;
    @FindBy(className = "btnSave")
    private WebElement saveButton;
    @FindBy(xpath = " //*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement itemSavedClosePopupMessage;
    @FindBy(id = "search")
    private WebElement searchBar;
    @FindBy(id = "edit")
    private WebElement citySearchResultEditPen;
    @FindBy(id = "name")
    private WebElement editItemPopupMessageInputField;
    @FindBy(className = "btnSave")
    private WebElement saveButtonPopupMessageInputField;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement searchResult;
    @FindBy(id = "delete")
    private WebElement deleteCityButton;
    @FindBy(className = "text--lighten3")
    private WebElement confirmDeleteCityButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement nameOfCitySearchResult;

    public AdminCitiesPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getItemSavedClosePopupMessage() {
        return itemSavedClosePopupMessage;
    }

    public WebElement getSearchResult() {
        return searchResult;
    }

    public WebElement getNameOfCitySearchResult() {
        return nameOfCitySearchResult;
    }

    public void visitAdminCitiesPageListCitiesPt2() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(adminButton));
        adminButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(citiesButton));
        citiesButton.click();
    }

    public void addNewItem(String cityName) {
        Faker faker = new Faker();
        newItemButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newItemNameInputField));
        newItemNameInputField.sendKeys(cityName);
        saveButton.click();
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(itemSavedClosePopupMessage, "Saved successfully"));
    }

    public void searchCity(String cityName) {
        searchBar.sendKeys(cityName);
        webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr"), 1));
    }

    public void editCity(String cityName) {
        searchCity(cityName);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(citySearchResultEditPen));
        citySearchResultEditPen.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editItemPopupMessageInputField));
        editItemPopupMessageInputField.sendKeys(" edited");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveButtonPopupMessageInputField));
        saveButtonPopupMessageInputField.click();
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(itemSavedClosePopupMessage, "Saved successfully"));
    }

    public void deleteCity() {
        deleteCityButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(confirmDeleteCityButton));
        confirmDeleteCityButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(itemSavedClosePopupMessage));
    }
}
