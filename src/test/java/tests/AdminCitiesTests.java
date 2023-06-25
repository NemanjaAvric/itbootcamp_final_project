package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminCitiesPage;
import pages.LoginPage;


public class AdminCitiesTests extends BaseTest {

    private LoginPage loginPage;
    private AdminCitiesPage adminCitiesPage;
    private String cityName;

    private final String SAVED_SUCCESSFULLY_MESSAGE = "Saved successfully";

    private final String DELETED_SUCCESSFULLY_MESSAGE = "Deleted successfully";


    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, webDriverWait);
        adminCitiesPage = new AdminCitiesPage(driver, webDriverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        utility.clickOnWebElement(loginPage.getLoginButton());
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        adminCitiesPage.visitAdminCitiesPageListCitiesPt2();
        cityName = utility.fakeCity();
    }

    @Test
    public void visitAdminCitiesPageListCities() {
        assertTrue(adminCitiesPage.ADMIN_CITIES_PAGE_URL_ENDING);
        assertTrue(loginPage.webelementIsDisplayed(loginPage.getLogoutButton()));
    }

    @Test
    public void createNewCity() {
        adminCitiesPage.addNewItem(cityName);
        assertTrue(adminCitiesPage.getItemSavedClosePopupMessage(), SAVED_SUCCESSFULLY_MESSAGE);
    }

    @Test
    public void editCity() {
        adminCitiesPage.editNewCity(cityName);
        assertTrue(adminCitiesPage.getItemSavedClosePopupMessage(), SAVED_SUCCESSFULLY_MESSAGE);
    }

    @Test
    public void searchCity() {
        adminCitiesPage.editNewCity(cityName);
        adminCitiesPage.searchCity(cityName + adminCitiesPage.getEDIT_CITY_TEXT());
        assertEquals(adminCitiesPage.getWebElementText(adminCitiesPage.getSearchResult()), cityName + adminCitiesPage.getEDIT_CITY_TEXT());
    }

    @Test
    public void deleteCity() {
        adminCitiesPage.editNewCity(cityName);
        adminCitiesPage.searchCityWait(cityName);
        assertTrue(adminCitiesPage.getNameOfCitySearchResult(), cityName);
        adminCitiesPage.deleteCity();
        assertTrue(adminCitiesPage.getItemSavedClosePopupMessage(), DELETED_SUCCESSFULLY_MESSAGE);
    }
}
