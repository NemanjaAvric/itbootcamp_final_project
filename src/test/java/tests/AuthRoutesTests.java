package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {
    @Test
    public void homeVisitForbidIfNotAuthenticated() {
        driver.navigate().to(BASEURL + "/home");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void profileVisitForbidIfNotAuthenticated() {
        driver.navigate().to(BASEURL + "/profile");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void citiesAdminVisitForbidIfNotAuthenticated() {
        driver.navigate().to(BASEURL + "/admin/cities");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void usersAdminVisitForbidIfNotAuthenticated() {
        driver.navigate().to(BASEURL + "/admin/users");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

}
