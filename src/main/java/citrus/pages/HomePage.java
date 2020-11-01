package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage{
    SelenideElement searchField = $(By.cssSelector("input[type='search']"));
    SelenideElement acceptSearchButton = $(By.cssSelector("button[class='global-search__submit']"));

    public HomePage closePopUp(){
        super.closePopUp();
        return this;
    }
    public HomePage waitForPageToLoad(){
        super.waitForPageToLoad();
        return this;
    }

    public HomePage openHiddenMenu(String menuName) {
        $$x("//a[@href='/smartfony/']/span[contains(text(),'" + menuName + "')]").get(1).hover();
        return this;
    }

    public HomePage selectGroup(String linkText) {
        $(By.linkText(linkText)).click();
        return this;
    }
    public HomePage postValueIntoSearchField(String searchProduct) {
        searchField.shouldBe(Condition.visible).setValue(searchProduct);
        return this;
    }
    public HomePage acceptSearch(){
        acceptSearchButton.shouldBe(Condition.visible).click();
        return this;
    }
}
