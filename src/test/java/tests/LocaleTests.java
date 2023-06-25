package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LocalePage;

public class LocaleTests extends BaseTest {

    private LocalePage localePage;

    private final String EXCPECTED_PAGE_TITLE_SPANISH = "Página de aterrizaje";

    private final String EXCPECTED_PAGE_TITLE_FRENCH = "Page d'atterrissage";

    private final String EXCPECTED_PAGE_TITLE_ENGLISH = "Landing";

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        localePage = new LocalePage(driver, webDriverWait);
    }

    @Test
    public void setLocalToSpanish() {
        localePage.changeLocal(localePage.getShapnishLocal());
        assertEquals(localePage.getWebElementText(localePage.getPageTitle()), EXCPECTED_PAGE_TITLE_SPANISH);
    }

    @Test
    public void setLocalToEnglish() {
        localePage.changeLocal(localePage.getEnsglishLocal());
        assertEquals(localePage.getWebElementText(localePage.getPageTitle()), EXCPECTED_PAGE_TITLE_ENGLISH);
    }

    @Test
    public void setLocaleToFrench() {
        localePage.changeLocal(localePage.getFrenchLocal());
        assertEquals(localePage.getWebElementText(localePage.getPageTitle()), EXCPECTED_PAGE_TITLE_FRENCH);
    }
}
