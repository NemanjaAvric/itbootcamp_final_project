package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AdminCitiesPage;
import pages.LoginPage;
import pages.ProfilePage;

public class AuthRoutesTests extends BaseTest {

    private LoginPage loginPage;
    private ProfilePage profilePage;
    private AdminCitiesPage adminCitiesPage;
    private final String ADMIN_USERS_PAGE_URL_ENDING = "/admin/users";

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        profilePage = new ProfilePage(driver, webDriverWait);
        adminCitiesPage = new AdminCitiesPage(driver, webDriverWait);
    }

    @Test
    public void homeVisitForbidIfNotAuthenticated() {
        driverNavigateTo(BASEURL + HOME_PAGE_URL_ENDING);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void profileVisitForbidIfNotAuthenticated() {
        driverNavigateTo(BASEURL + profilePage.PROFILE_PAGE_URL_ENDING);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void citiesAdminVisitForbidIfNotAuthenticated() {
        driverNavigateTo(BASEURL + adminCitiesPage.ADMIN_CITIES_PAGE_URL_ENDING);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

    @Test
    public void usersAdminVisitForbidIfNotAuthenticated() {
        driverNavigateTo(BASEURL + ADMIN_USERS_PAGE_URL_ENDING);
        assertTrue(loginPage.LOGIN_PAGE_URL_ENDING);
    }

}
