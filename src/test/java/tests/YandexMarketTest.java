package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.itemYandexMarket;
import static helpers.Environment.urlYandexMarket;
import static io.qameta.allure.Allure.step;

@Feature("Search item on YandexMarket")
@Tag("yandex")
public class YandexMarketTest extends TestBase{


    @Test
    @Story("Searching item on YandexMarket")
    @DisplayName("Positive test by searching item on YandexMarket")

    void succesfulSearchYandexMArket (){

        // arrange
        step("Open the main page",()->{
            open(urlYandexMarket);
        });
        //act
        step ("Input itemYandexMarket",()->{
            $("#header-search").setValue(itemYandexMarket).pressEnter();
        });
        step("Click the first link",()->{
            $(".n-snippet-cell2__title").click();
        });
        //assert
        step("Search the result, it should have itemYandexMarket",()->{
            switchTo().window(1);
            $("html").shouldHave(text(itemYandexMarket));
        });

    }

}
