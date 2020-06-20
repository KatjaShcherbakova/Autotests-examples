package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.itemYandexMarket;
import static helpers.Environment.urlYandexMarket;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Feature("Search item on YandexMarket")
@Tag("yandex")
public class YandexMarketTests extends TestBase {
//    @Disabled
    @Test
    @Story("Searching item on YandexMarket")
    @DisplayName("Positive test by searching item on YandexMarket")
    void succesfulSearchYandexMarket (){
        Configuration.fastSetValue=true;
        parameter("Item for search:", itemYandexMarket);

        step("Open the main page",()-> {
            open(urlYandexMarket);
        });
        step ("Input itemYandexMarket in the search bar",()-> {
            $(byName("text")).val(itemYandexMarket).pressEnter();
            sleep(2000);
            $(byName("text")).pressEnter();

//            $("#header-search").pressEnter();
        });
        step("Click the first link",()->{
            $("[data-autotest-id='product-snippet'] img").click();
        });
        step("Search the result, it should have itemYandexMarket",()-> {
            switchTo().window(1);
            $("html").shouldHave(text(itemYandexMarket));
        });

    }

}
