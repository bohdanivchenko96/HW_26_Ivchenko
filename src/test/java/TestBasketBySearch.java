import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestBasketBySearch extends BaseUITest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;

    @BeforeMethod
    private void executeClasses() {
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
    }

    @Test
    private void ExercizeTwo(){
        int productPriceValue;
        String productName = "Apple iPhone 11 128Gb Black";
        homePage.waitForPageToLoad()
                .closePopUp()
                .postValueIntoSearchField("Apple iPhone 11")
                .acceptSearch();
        productListPage.waitForPageToLoad()
                .closePopUp()
                .findProductByName(productName);

        productPriceValue = productListPage.findPrice(productName);
        System.out.println(productPriceValue);
        productListPage.clickOnBuyButton();
        productPage.checkVisibleOfBasket()
                .verifyCountOfProductInBasket();
        assertEquals(productPage.verifyCountOfProductInBasket(), 1);
        System.out.println("Count of products is verified");
        assertTrue(productPage.checkProductNameInBasketOnList(productName));
        System.out.println("Name of product is verified");
        assertEquals(productPage.getProductPriceInBasket(), productPriceValue);
        System.out.println("Product price in basket is verified");
        assertEquals(productPage.getTotalPriceInBasket(), productPriceValue);
        System.out.println("total price is verified");
    }
}
