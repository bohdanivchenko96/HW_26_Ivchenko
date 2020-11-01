import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestByFilter extends BaseUITest{
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;

    @BeforeMethod
    private void executeClasses(){
        homePage = new HomePage();

        productListPage = new ProductListPage();
        productPage = new ProductPage();
    }
    @Test
    private void priceFilter(){
        homePage.waitForPageToLoad()
                .closePopUp()
                .openHiddenMenu("Смартфоны")
                .selectGroup("Samsung");
        productListPage.waitForPageToLoad()
                .closePopUp()
                .setPriceValue(5000, 15000);

        assertTrue(productListPage.checkThatNameContainsWord("Samsung"));
        System.out.println("All names include word Sumsung");
        assertTrue(productListPage.checkAllPricesCorespondToTheFilter(5000, 15000));
        System.out.println("All prices corresponds filters");

    }

    @Test
    private void memoryFilter(){
        homePage.waitForPageToLoad()
                .closePopUp()
                .openHiddenMenu("Смартфоны")
                .selectGroup("Xiaomi");
        productListPage.waitForPageToLoad()
                .closePopUp()
                .filerByMemory("32 Гб")
                .closePopUp()
                .filerByMemory("64 Гб");
        System.out.println("Filter added");
        assertTrue(productListPage.checkThatNameContainsWord("32Gb", "64Gb"));
        System.out.println("First test true");

    }

    /*@Test
    private void byMaterialGroup(){
        homePage.waitForPageToLoad()
                .closePopUp()
                .openHiddenMenu("Смартфоны")
                .selectGroup("Samsung");
        productListPage.waitForPageToLoad()
                .closePopUp()
                .filerByMemory("Металл");
        assertTrue(productListPage.checkThatNameContainsWord("Samsung"));
        System.out.println("All names include word Sumsung");
        productListPage.openHoverDetails();
    }*/
}
