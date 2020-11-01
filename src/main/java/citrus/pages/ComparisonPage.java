package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ComparisonPage extends BasePage {
    ElementsCollection basketButtonList = $$x("//i[@class='icon-new-citrus-cart el-tooltip item']");
    SelenideElement basketCount = $x("//div[@class='user-actions__cart h-icons__icon ctrs-basket-mini-icon']//span[@class='counter']");

    public ComparisonPage checkVisibilityOComparisonfPage() {
        $(By.cssSelector("div[class='header-compare']")).shouldBe(Condition.visible);
        return this;
    }

    public ComparisonPage addToBasket(int index) {
        basketButtonList.get(index).shouldBe(Condition.visible).click();
        return this;
    }
    public ComparisonPage closeBasket(){
        super.closeBasketOnProductList();
        return this;
    }

    public ComparisonPage checkCountOfBasket(String count) {
        basketCount.shouldHave(Condition.text(count));
        return this;
    }
}
