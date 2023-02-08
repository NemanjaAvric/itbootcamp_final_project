package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminCitiesPage;
import pages.LoginPage;


public class AdminCitiesTests extends BaseTest {

    private LoginPage loginPage;
    private AdminCitiesPage adminCitiesPage;
    private String cityName;

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
        loginPage.getLoginButton().click();
        loginPage.login(loginPage.VALID_EMAIL, loginPage.VALID_PASSWORD);
        adminCitiesPage.visitAdminCitiesPageListCitiesPt2();
        cityName = utility.fakeCity();
    }

    @Test
    public void visitAdminCitiesPageListCities() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith(adminCitiesPage.ADMIN_CITIES_PAGE_URL_ENDING));
        Assert.assertTrue(loginPage.webelementIsDisplayed(loginPage.getLogoutButton()));
    }

    @Test
    public void createNewCity() {
        adminCitiesPage.addNewItem(cityName);
        Assert.assertTrue(adminCitiesPage.getWebElementText(adminCitiesPage.getItemSavedClosePopupMessage()).contains("Saved successfully"));
    }

    @Test
    public void editCity() {
        adminCitiesPage.editNewCity(cityName);
        Assert.assertTrue(adminCitiesPage.getWebElementText(adminCitiesPage.getItemSavedClosePopupMessage()).contains("Saved successfully"));
    }

    @Test
    public void searchCity() {
        adminCitiesPage.editNewCity(cityName);
        adminCitiesPage.searchCity(cityName + " edited");
        Assert.assertEquals(adminCitiesPage.getWebElementText(adminCitiesPage.getSearchResult()), cityName + " edited");
    }

    @Test
    public void deleteCity() {
        adminCitiesPage.editNewCity(cityName);
        adminCitiesPage.searchCityWait(cityName);
        Assert.assertTrue(adminCitiesPage.getWebElementText(adminCitiesPage.getNameOfCitySearchResult()).contains(cityName));
        adminCitiesPage.deleteCity();
        Assert.assertTrue(adminCitiesPage.getWebElementText(adminCitiesPage.getItemSavedClosePopupMessage()).contains("Deleted successfully"));
    }
}
