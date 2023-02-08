package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

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
        name = utility.fakeName();
        phoneNumber = utility.fakeCellPhoneNumber();
        country = utility.fakeCountry();
        twitterURL = utility.fakeURL();
        gitHubURL = utility.fakeURL();
        city = "San Leandro";

    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.getLoginButton().click();
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
    }

    @Test
    public void editsProfile() {
        profilePage.editProfile(name, phoneNumber, city, country, twitterURL, gitHubURL);
        Assert.assertTrue(profilePage.getWebElementText(profilePage.getProfileSavedSuccessfullyMessage()).contains("Profile saved successfuly"));
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getNameInputFieldMyProfile()), name);
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getPhoneInputFieldMyProfile()), phoneNumber);
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getCityInputFieldMyProfile()), city);
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getCountryInputFieldMyProfile()), country);
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getTwitterURLInputFieldMyProfile()), "https://" + twitterURL);
        Assert.assertEquals(profilePage.getWebElementAttributeValue(profilePage.getGitHubURLInputFieldMyProfile()), "https://" + gitHubURL);
    }
}
