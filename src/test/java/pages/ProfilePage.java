package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {
    @FindBy(className = "btnProfile")
    private WebElement myProfileButton;
    @FindBy(id = "name")
    private WebElement nameInputFieldMyProfile;
    @FindBy(id = "phone")
    private WebElement phoneInputFieldMyProfile;
    @FindBy(id = "city")
    private WebElement cityInputFieldMyProfile;
    @FindBy(id = "country")
    private WebElement countryInputFieldMyProfile;
    @FindBy(id = "urlTwitter")
    private WebElement twitterURLInputFieldMyProfile;
    @FindBy(id = "urlGitHub")
    private WebElement gitHubURLInputFieldMyProfile;
    @FindBy(className = "btnSave")
    private WebElement saveButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement profileSavedSuccessfullyMessage;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div/div[4]/span/div/div/div[1]/div[1]/div[1]/div/button")
    private WebElement deleteCityButton;

    public final String PROFILE_PAGE_URL_ENDING = "/profile";

    public ProfilePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getNameInputFieldMyProfile() {
        return nameInputFieldMyProfile;
    }

    public WebElement getPhoneInputFieldMyProfile() {
        return phoneInputFieldMyProfile;
    }

    public WebElement getCityInputFieldMyProfile() {
        return cityInputFieldMyProfile;
    }

    public WebElement getProfileSavedSuccessfullyMessage() {
        return profileSavedSuccessfullyMessage;
    }

    public WebElement getCountryInputFieldMyProfile() {
        return countryInputFieldMyProfile;
    }

    public WebElement getTwitterURLInputFieldMyProfile() {
        return twitterURLInputFieldMyProfile;
    }

    public WebElement getGitHubURLInputFieldMyProfile() {
        return gitHubURLInputFieldMyProfile;
    }

    public void clearAllInputFields() {
        clearInputField(nameInputFieldMyProfile);
        clearInputField(phoneInputFieldMyProfile);
        deleteCityButton.click();
        clearInputField(countryInputFieldMyProfile);
        clearInputField(twitterURLInputFieldMyProfile);
        clearInputField(gitHubURLInputFieldMyProfile);
    }

    private void clearInputField(WebElement element) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void chooseCity(String city) {
        cityInputFieldMyProfile.click();
        cityInputFieldMyProfile.sendKeys(Keys.ENTER, city, Keys.ENTER);
    }

    public void editProfile(String name, String phoneNumber, String city, String country, String twitterURL, String gitHubURL) {
        myProfileButton.click();
        clearAllInputFields();
        nameInputFieldMyProfile.sendKeys(name);
        phoneInputFieldMyProfile.sendKeys(phoneNumber);
        chooseCity(city);
        countryInputFieldMyProfile.sendKeys(country);
        twitterURLInputFieldMyProfile.sendKeys("https://" + twitterURL);
        gitHubURLInputFieldMyProfile.sendKeys("https://" + gitHubURL);
        saveButton.click();
    }
}

