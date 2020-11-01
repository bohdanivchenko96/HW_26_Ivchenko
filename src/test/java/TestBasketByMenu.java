import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestBasketByMenu extends BaseUITest{
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
    private void ExerciseOne(){
        int productPrice;
        String productName = "Apple iPhone 11 128Gb Black";
        homePage.waitForPageToLoad()
                .closePopUp()
                .openHiddenMenu("Смартфоны")
                .selectGroup("Apple");
        productListPage.waitForPageToLoad()
                .closePopUp()
                .findByName(productName);
        productPrice = Integer.parseInt(productPage.getProductPrice().replaceAll(" ", ""));
        productPage.clickBuyButton()
                .checkVisibleOfBasket();
        assertEquals(productPage.verifyCountOfProductInBasket(), 1);
        System.out.println("Count of products is verified");
        assertTrue(productPage.checkProductNameInBasket(productName));
        System.out.println("Name of product is verified");
        assertEquals(productPage.getProductPriceInBasket(), productPrice);
        System.out.println();
        assertEquals(productPage.getTotalPriceInBasket(), productPrice);
        System.out.println("total price is verified");

    }

}
