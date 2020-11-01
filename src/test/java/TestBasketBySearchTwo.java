import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestBasketBySearchTwo extends BaseUITest {
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
    private void ExercizeThree() {
        ArrayList<String> productNames = new ArrayList<>();
        ArrayList<Integer> productPrices = new ArrayList<>();
        homePage.waitForPageToLoad()
                .closePopUp()
                .postValueIntoSearchField("Apple iPhone 11")
                .acceptSearch();
        productListPage.waitForPageToLoad()
                .closePopUp();
        productNames.add(productListPage.getProductNamesByIndex(0));
        productNames.add(productListPage.getProductNamesByIndex(1));
        productPrices.add(productListPage.getProductPricesByIndex(0));
        productPrices.add(productListPage.getProductPricesByIndex(1));
        productListPage.addProductToBasketByIndex(0)
                .closeBasketOnProductList()
                .closePopUp()
                .addProductToBasketByIndex(1);
        productPage.checkVisibleOfBasket();
        assertEquals(productPage.verifyCountOfProductInBasket(), 2);
        System.out.println("Count of products is verified");
        assertTrue(productPage.getProductNameFromListInBasket(0, productNames.get(0)));
        assertTrue(productPage.getProductNameFromListInBasket(1, productNames.get(1)));
        System.out.println("Name of product is verified");
        assertEquals(productPage.getProductPriceFromListInBasket(0), productPrices.get(0));
        assertEquals(productPage.getProductPriceFromListInBasket(1), productPrices.get(1));
        System.out.println("Product price in basket is verified");
        assertEquals(productPage.getTotalPriceInBasket(), productPrices.get(0) + productPrices.get(1));
        System.out.println("total price is verified");
    }
}
