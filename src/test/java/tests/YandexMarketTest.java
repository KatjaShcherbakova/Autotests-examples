package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

import static helpers.Environment.*;

@Feature("Search item on YandexMarket")
public class YandexMarketTest extends TestBase{


    @Test
    @Story("Searching item on YandexMarket")
    @DisplayName("Positive test by searching item on YandexMarket")

    void succesfulSearchYandexMArket (){

        // arrange
        step("Open home page",()->{
            open(urlYandexMarket);
        });
        //act
        step ("Input itemYandexMarket",()->{
            $("#header-search").setValue(itemYandexMarket).pressEnter();
        });
        step("Click first link",()->{
            $(".n-snippet-cell2__title").click();
        });
        //assert
        step("Search result should have itemYandexMarket",()->{
            switchTo().window(1);
            $("html").shouldHave(text(itemYandexMarket));
        });

    }

}
