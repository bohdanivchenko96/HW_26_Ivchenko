package citrus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    SelenideElement closePopupButton = $(By.className("el-dialog__headerbtn"));
    SelenideElement comparisonButton = $(By.cssSelector("div[class='user-actions__compare tips-parent']"));
    SelenideElement closeButtonOfBasket = $x(("//div[@class='el-dialog el-dialog--medium']//button[@class='el-dialog__headerbtn']/i"));
    public BasePage waitForPageToLoad() {

        new WebDriverWait(WebDriverRunner.getWebDriver(), 10000).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }
    public BasePage closePopUp(){
        if (closePopupButton.isDisplayed()){
            closePopupButton.click();
        }
        return this;
    }

    public BasePage navigateToComparison(){
        comparisonButton.click();
        return this;
    }
    public BasePage closeBasketOnProductList(){
        closeButtonOfBasket.shouldBe(Condition.visible).click();
        return this;
    }


}
