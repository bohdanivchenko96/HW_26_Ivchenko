package citrus.pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProductListPage extends BasePage {
    ElementsCollection productNamesList = $$x("//h5");
    ElementsCollection allProductNamesList = $$x("//div[@class='product-card__name']//span");
    ElementsCollection getAllProductPricesList = $$x("//div[@class='prices__price']//span[@class='price']");
    ElementsCollection productPricesList = $$x("//span[@class='price-number']");
    ElementsCollection productBasketIcons = $$x("//i[@class='icon-new-citrus-cart el-tooltip item']");
    ElementsCollection productsOverviewList = $$x("//div[@class='product-card__overview']");
    ElementsCollection comparisonButtons = $$(By.cssSelector("i[class='icon-comparison2 el-tooltip item']"));
    SelenideElement comparisonCount = $x("//div[@class='user-actions__compare tips-parent']//span[@class='counter']");
    ElementsCollection inputPriceValue = $$x("//input[@class='el-input__inner']");


    public ProductListPage closePopUp() {
        super.closePopUp();
        return this;
    }

    public ProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }
    public void findByName(String productName){
        $(By.partialLinkText(productName)).click();
    }
    public void findProductByName(String productName) {
        while (true) {
            if ($(By.partialLinkText(productName)).isDisplayed()) {
                $(By.partialLinkText(productName)).scrollTo();
                break;
            } else {
                $(By.cssSelector("a[class='catalog-card-container more-items product-card product-card--mini']")).scrollTo().click();
            }
        }
    }

    public int findPrice(String productName) {
        return Integer.parseInt($x("//h5[contains(text(),'Apple iPhone 11 128Gb Black')]/../../..//span[@class='price-number']").getText().replaceAll("[^0-9]", ""));

    }

    public void clickOnBuyButton() {

        $x("//h5[contains(text(),'Apple iPhone 11 128Gb Black')]/../../..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
    }
    public String getProductNamesByIndex(int index){
        return productNamesList.get(index).getText();
    }
    public int getProductPricesByIndex(int index){
        return Integer.parseInt(productPricesList.get(index).getText().replaceAll("[^0-9]", ""));
    }

    public ProductListPage addProductToBasketByIndex(int index){
        productBasketIcons.get(index).click();
        return this;
    }
    public ProductListPage closeBasketOnProductList(){
        super.closeBasketOnProductList();
        return this;
    }
    public ProductListPage addToCompariosonFromList(int index){
        comparisonButtons.get(index).click();
        return this;
    }
    public ProductListPage goToComparison(){
        super.navigateToComparison();
        return this;
    }

    public ProductListPage checkComparisonCount(String count) {
        comparisonCount.shouldHave(Condition.text(count));
        return this;
    }

    public ProductListPage setPriceValue(int min, int max) {
        inputPriceValue.first().shouldBe(Condition.enabled).clear();
        $x("//span[@class='icon filter']").shouldBe(Condition.visible).click();
        inputPriceValue.first().setValue(String.valueOf(min));

        inputPriceValue.last().shouldBe(Condition.enabled).clear();

        inputPriceValue.last().setValue(String.valueOf(max));
        $x("//span[@class='icon filter']").shouldBe(Condition.visible).click();
        return this;
    }
    
    public boolean checkThatNameContainsWord(String productName){
        boolean result = true;
        allProductNamesList.first().shouldBe(Condition.visible);
        allProductNamesList.last().shouldBe(Condition.visible);
        for(SelenideElement names : allProductNamesList){
            if(!names.getText().contains(productName)){
                result = false;
            }
        }
        return result;
    }
    public boolean checkThatNameContainsWord(String memoryValueOne, String memoryValueTwo){
        boolean result = false;
        allProductNamesList.first().shouldBe(Condition.visible);
        for(SelenideElement names : allProductNamesList){
            result = false;
            if(names.getText().contains(memoryValueOne) || names.getText().contains(memoryValueTwo)){
                result = true;
            }
        }
        return result;
    }

    public boolean checkAllPricesCorespondToTheFilter(int min, int max){
        boolean result = false;
        for(SelenideElement prices : getAllProductPricesList){
            int price = Integer.parseInt(prices.getText().replaceAll("[^0-9]", ""));
            if((price > min) && (price < max)){
                result = true;
            } else {
                    result = false;
            }
        }
        return result;
    }

    public ProductListPage filerByMemory(String s) {
        $x("//span[@class='el-checkbox__label']//a[contains(text(),'" + s + "')]/../../span[@class='el-checkbox__input']").shouldBe(Condition.visible).click();

        return this;
    }

    public boolean openHoverDetails() {
        boolean result = false;
        int count = 0;
        System.out.println(allProductNamesList.size());
        for(SelenideElement element : productsOverviewList){
            element.shouldBe(Condition.visible).hover();
            System.out.println($x("//div[@class='product-card__properties']").shouldBe(Condition.visible).getText().contains("Металл"));
            if ($x("//div[@class='product-card__properties']").shouldBe(Condition.visible).getText().contains("Металл")){
                count++;
            }
        }
        if (count - 1 == allProductNamesList.size()){
            return result = true;
        } else {
            return result;
        }

    }
}