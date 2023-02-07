package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

import java.util.List;

public class ProfileTests extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String name;
    private String phoneNumber;

    private String city;
    private String country;

    private String twitterURL;

    private String gitHubURL;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        profilePage = new ProfilePage(driver, webDriverWait);
        Faker faker = new Faker();
        name = faker.name().fullName();
        phoneNumber = faker.phoneNumber().cellPhone();
        country = faker.country().name();
        twitterURL = faker.internet().url();
        gitHubURL = faker.internet().url();
        city = "New York";

    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.getLoginButton().click();
        loginPage.login(loginPage.getVALIDEMAIL(), loginPage.getVALIDPASSWORD());
    }

    @Test
    public void editsProfile() {
        profilePage.editProfile(name, phoneNumber, city, country, twitterURL, gitHubURL);
        Assert.assertTrue(profilePage.getProfileSavedSuccessfullyMessage().getText().contains("Profile saved successfuly"));
        Assert.assertEquals(profilePage.getNameInputFieldMyProfile().getAttribute("value"), name);
        Assert.assertEquals(profilePage.getPhoneInputFieldMyProfile().getAttribute("value"), phoneNumber);
        Assert.assertEquals(profilePage.getCityInputFieldMyProfile().getAttribute("value"), city);
        Assert.assertEquals(profilePage.getCountryInputFieldMyProfile().getAttribute("value"), country);
        Assert.assertEquals(profilePage.getTwitterURLInputFieldMyProfile().getAttribute("value"), "https://" + twitterURL);
        Assert.assertEquals(profilePage.getGitHubURLInputFieldMyProfile().getAttribute("value"), "https://" + gitHubURL);
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driver.findElements(By.className("btnLogout"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }
}
