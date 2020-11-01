
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class BaseUITest {

    @BeforeClass
    public void startUp(){
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("/");

    }
}
