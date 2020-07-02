package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
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

    @Disabled
    @Test
    @Story("Searching item on YandexMarket")
    @DisplayName("Positive test by searching item on YandexMarket")
    void succesfulSearchYandexMarket () {
        parameter("Item for search:", itemYandexMarket);

        step("Open the main page", ()->
            open(urlYandexMarket)));

        step ("Input itemYandexMarket in the search bar", () -> {
            $(byName("text")).val(itemYandexMarket);
            sleep(5000);
            $(byName("text")).pressEnter();
        });
        step("Click the first link", () ->
            $("[data-autotest-id='product-snippet'] img").click());

        step("Check that the search result contains the \"{itemYandexMarket}\"", () ->
            $("html").shouldHave(text(itemYandexMarket)));

    }

}
