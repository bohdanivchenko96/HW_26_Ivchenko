package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage extends BasePage{
    SelenideElement productPrice = $(By.cssSelector("div[class='price'] > span > span"));
    SelenideElement buyButton = $(By.cssSelector("div.buy-block__base >div.normal > button[class='btn orange full']"));
    SelenideElement totalPriceInBasket = $(By.cssSelector("span[class='ctrs-main-price ctrs-basket-footer__new-price']"));
    SelenideElement productNameInBusket = $(By.cssSelector("a[class='ex-active active ctrs-basket-product__name']"));
    SelenideElement productNameInBusketOnList = $(By.cssSelector("a[class='ctrs-basket-product__name']"));
    SelenideElement productPriceInBasket = $(By.cssSelector("span[class='ctrs-main-price']"));
    ElementsCollection productPriceInBasketList = $$(By.cssSelector("span[class='ctrs-main-price']"));
    ElementsCollection productNamesListInBusketOnList = $$(By.cssSelector("a[class='ctrs-basket-product__name']"));

    public ProductPage closePopUp(){
        super.closePopUp();
        return this;
    }
    public ProductPage waitForPageToLoad(){
        super.waitForPageToLoad();
        return this;
    }


    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductPage clickBuyButton() {
        buyButton.shouldBe(Condition.visible).click();
        return this;
    }

    public ProductPage checkVisibleOfBasket(){
        $(By.cssSelector("div[class='el-dialog el-dialog--medium']")).shouldBe(Condition.visible);
        return this;
    }

    public int verifyCountOfProductInBasket(){
        return $$(By.cssSelector("div[class='ctrs-basket-product']")).size();

    }
    public boolean checkProductNameInBasket(String productName){
        return productNameInBusket.getText().contains(productName);
    }
    public boolean checkProductNameInBasketOnList(String productName){
        return productNameInBusketOnList.getText().contains(productName);
    }
    public int getProductPriceInBasket(){
        int price;

        return price = Integer.parseInt(productPriceInBasket.getText().replaceAll("[^0-9]", ""));
    }
    public  Integer getProductPriceFromListInBasket(int index){
        return Integer.parseInt(productPriceInBasketList.get(index).getText().replaceAll("[^0-9]", ""));
    }
    public int getTotalPriceInBasket(){
        int price;
        return price = Integer.parseInt(totalPriceInBasket.getText().replaceAll("[^0-9]", ""));

    }
    public boolean getProductNameFromListInBasket(int index, String productName){
        return productNamesListInBusketOnList.get(index).getText().contains(productName);
    }
}
