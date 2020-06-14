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
    @DisplayName("Positive test with calculator form")

    void successfulTestTinkoff() {
        parameter("Валюта:", CURRENCY);
        parameter("Сумма:", AMOUNT);

        step("Открываем главную страницу", () -> {
            open("https://www.tinkoff.ru/");
        });
        step("Кликаем на 'Вклады'", () -> {
            $(byText("Вклады")).closest("a").click();
        });
        //для заморозки dom используем в консоли команду setTimeout(function(){debugger},5000)
        step("В drop-down 'Валюта 'выбираем валюту $", () -> {
            $("[data-qa-type='uikit/inputBox']", 2).click();
            $("[data-qa-type='uikit/scroll']").$(byText(CURRENCY)).click();
        });
        step("Проверяем,что валюта установилась корректно", () -> {
            $("[data-qa-type='uikit/inputBox']", 2).$("[data-qa-type='uikit/select.value']")
                    .shouldHave((text(CURRENCY)));
        });
        step("Устанавливаем сумму вклада 5000", () -> {
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").setValue(AMOUNT);
        });
        step("Проверяем, что сумма устанавилась корректно", () -> {
            $("[data-qa-type='uikit/inputBox.inputContainer'] input").shouldHave(value(AMOUNT));
        });
}
     @Test
     @Story("TinkoffBAnk Website, Hover on Page")
     @DisplayName("Positive Test,the hover shows the expected text")

     void successfulTestWithHover() {
         step("Открываем страницу с калькулятором вклада", () -> {
             open("https://www.tinkoff.ru/deposit/");
         });
         step("Проверяем наличие hover с нужным текстом", () -> {
             $("div[data-qa-file=Pie]").scrollIntoView(true);
             $("div[data-qa-file=Pie]").shouldHave(text("за 24 месяца я накоплю"));
         });
         step("Наводим курсор на hover", () -> {
             $("div[data-qa-file=Pie]").hover();
         });
         step("Проверяем, что текст изменился на ожидаемый", () -> {
             $("div[data-qa-file=Pie]").shouldHave(text("пополнения"));
         });
     }

      @Test
      @Story("TinkoffBAnk Website, Checkbox on Page")
      @DisplayName("Positive Test,remove the flag in the checkbox")

      void successfulTestWithCheckbox(){
        step("Открываем страницу с калькулятором вклада",()->{
            open("https://www.tinkoff.ru/deposit/");
        });
        step("Снимаем флаги в checkbox",()->{
            $("div[data-qa-file=Pie]").scrollIntoView(true);
            $("[data-qa-type='uikit/checkbox']").click();
        });
        step("Проверяем, что флаг снят",()->{
            $("[aria-checked]").shouldNotBe(checked);

        });

         }
}
