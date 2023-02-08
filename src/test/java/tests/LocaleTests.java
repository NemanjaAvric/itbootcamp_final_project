package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LocalePage;

public class LocaleTests extends BaseTest {

    private LocalePage localePage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        localePage = new LocalePage(driver, webDriverWait);
    }

    @Test
    public void setLocalToSpanish() {
        localePage.changeLocal(localePage.getShapnishLocal());
        Assert.assertEquals(localePage.getWebElementText(localePage.getPageTitle()), "Página de aterrizaje");
    }

    @Test
    public void setLocalToEnglish() {
        localePage.changeLocal(localePage.getEnsglishLocal());
        Assert.assertEquals(localePage.getWebElementText(localePage.getPageTitle()), "Landing");
    }

    @Test
    public void setLocaleToFrench() {
        localePage.changeLocal(localePage.getFrenchLocal());
        Assert.assertEquals(localePage.getWebElementText(localePage.getPageTitle()), "Page d'atterrissage");
    }
}
