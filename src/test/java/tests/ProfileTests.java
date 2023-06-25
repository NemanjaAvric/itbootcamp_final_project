package tests;

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
    private final String EXCPECTED_PROFILE_SAVED_SUCCESSFULY_MESSAGE = "Profile saved successfuly";


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
        utility.clickOnWebElement(loginPage.getLoginButton());
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
    }

    @Test
    public void editsProfile() {
        profilePage.editProfile(name, phoneNumber, city, country, twitterURL, gitHubURL);
        assertTrue(profilePage.getProfileSavedSuccessfullyMessage(), EXCPECTED_PROFILE_SAVED_SUCCESSFULY_MESSAGE);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getNameInputFieldMyProfile()), name);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getPhoneInputFieldMyProfile()), phoneNumber);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getCityInputFieldMyProfile()), city);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getCountryInputFieldMyProfile()), country);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getTwitterURLInputFieldMyProfile()), profilePage.getHTTPS_URL_BEGINNING() + twitterURL);
        assertEquals(profilePage.getWebElementAttributeValue(profilePage.getGitHubURLInputFieldMyProfile()), profilePage.getHTTPS_URL_BEGINNING() + gitHubURL);
    }
}
