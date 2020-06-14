package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Feature("Test TinkoffBankPage")
    @Tag("tinkoff")

public class TinkoffTest extends TestBase{
  private static String CURRENCY = "Доллары США";
  private static String AMOUNT  = "5000";


    @Test
    @Story("TinkoffBank test")
    @DisplayName("Positive test")

        void successfulTestTinkoff(){
        parameter("Валюта:",CURRENCY);
        parameter("Сумма:",AMOUNT);

        //arrange
        step("Открываем главную страницу",()->{
            open("https://www.tinkoff.ru/");
        });
        //act
        step("Кликаем на 'Вклады'",()->{
            $(byText("Вклады")).closest("a").click();
        });
        //для заморозки dom используем в консоли команду setTimeout(function(){debugger},5000)
        step("В drop-down 'Валюта 'выбираем валюту $",()->{
            $("[data-qa-type='uikit/inputBox']",2).click();
            $("[data-qa-type='uikit/scroll']").$(byText(CURRENCY)).click();
        });
        //assert
        step("Проверяем,что валюта установилась корректно",()->{
            $("[data-qa-type='uikit/inputBox']",2).$("[data-qa-type='uikit/select.value']")
                    .shouldHave((text(CURRENCY)));
        });
        //act
        step("Устанавливаем сумму вклада 5000",()->{
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").setValue(AMOUNT);
        });
        //assert
        step("Проверяем, что сумма устанавилась корректно",()->{
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").shouldHave(value(AMOUNT));
        });
        //assert
        step("Проверяем наличие hover с нужным текстом",()->{
            $("div[data-qa-file=Pie]").scrollIntoView(true);
            $("div[data-qa-file=Pie]").shouldHave(text("за 24 месяца я накоплю"));
        });
        //act
        step("Наводим курсор на hover",()->{
            $("div[data-qa-file=Pie]").hover();
        });
        //assert
        step("Проверяем, что текст изменился на ожидаемый",()->{
            $("div[data-qa-file=Pie]").shouldHave(text("пополнения"));
        });
        //act
        step("Снимаем флаги в checkbox",()->{
            $("[data-qa-type='uikit/checkbox']").click();
            $("[aria-checked]").shouldNotBe(checked);

        });

    }




}
