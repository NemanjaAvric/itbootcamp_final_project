package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(css = " #app > div.v-dialog__content.v-dialog__content--active > div > div > div.v-card__actions > button.v-btn.v-btn--text.theme--light.v-size--default.red--text.text--lighten3")
    private WebElement confirmDeleteCityButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement nameOfCitySearchResult;
    public final String ADMIN_CITIES_PAGE_URL_ENDING = "/admin/cities";

    private final String EDIT_CITY_TEXT = " edited";

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
        utility.clickOnWebElement(adminButton);
        utility.clickOnWebElement(citiesButton);
    }

    public void addNewItem(String cityName) {
        utility.clickOnWebElement(newItemButton);
        sendKeysToElement(newItemNameInputField, cityName);
        utility.clickOnWebElement(saveButton);
    }

    public void searchCity(String cityName) {
        clearInputField(searchBar);
        sendKeysToElement(searchBar, cityName);
    }

    public void searchCityWait(String cityName) {
        searchCity(cityName);
        waitForNumberOfElementsToBe("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr", 1);
    }

    public String getEDIT_CITY_TEXT() {
        return EDIT_CITY_TEXT;
    }

    public void editCity() {
        utility.clickOnWebElement(citySearchResultEditPen);
        sendKeysToElement(editItemPopupMessageInputField, EDIT_CITY_TEXT);
        utility.clickOnWebElement(saveButtonPopupMessageInputField);
    }

    public void deleteCity() {
        utility.clickOnWebElement(deleteCityButton);
        utility.clickOnWebElement(confirmDeleteCityButton);
        waitForVisibilityOfElement(itemSavedClosePopupMessage);
    }

    public void editNewCity(String city) {
        addNewItem(city);
        searchCity(city);
        editCity();
    }
}
