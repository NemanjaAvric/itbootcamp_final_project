package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminCitiesPage;
import pages.LoginPage;

import java.util.List;

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
        Faker faker = new Faker();
        cityName = faker.address().cityName();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.getLoginButton().click();
        loginPage.loginValidUsernameValidPassword();
        adminCitiesPage.visitAdminCitiesPageListCitiesPt2();
    }

    @Test
    public void visitAdminCitiesPageListCities() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(loginPage.getLogoutButton().isDisplayed());
    }

    @Test
    public void createNewCity() {
        adminCitiesPage.addNewItem(cityName);
        Assert.assertTrue(adminCitiesPage.getItemSavedClosePopupMessage().getText().contains("Saved successfully"));
    }

    @Test(dependsOnMethods = "createNewCity")
    public void editCity() {
        adminCitiesPage.editCity(cityName);
        Assert.assertTrue(adminCitiesPage.getItemSavedClosePopupMessage().getText().contains("Saved successfully"));
    }

    @Test(dependsOnMethods = "editCity")
    public void searchCity() {
        adminCitiesPage.searchCity(cityName + " edited");
        Assert.assertEquals(adminCitiesPage.getSearchResult().getText(), cityName + " edited");
    }

    @Test(dependsOnMethods = "searchCity")
    public void deleteCity() {
        adminCitiesPage.searchCity(cityName + " edited");
        Assert.assertEquals(adminCitiesPage.getNameOfCitySearchResult().getText(), cityName + " edited");
        adminCitiesPage.deleteCity();
        Assert.assertTrue(adminCitiesPage.getItemSavedClosePopupMessage().getText().contains("Deleted successfully"));
    }

    @AfterMethod
    public void afterMethod() {
        List<WebElement> elements = driver.findElements(By.className("btnLogout"));
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }
}
