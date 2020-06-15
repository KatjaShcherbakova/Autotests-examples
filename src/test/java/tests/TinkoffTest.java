package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

  @Feature("Test TinkoffBankPage")
  @Tag("tinkoff")

  public class TinkoffTest extends TestBase{

  private final static String CURRENCY = "Доллары США";
  private final static String AMOUNT  = "5000";
  private final static String HoverTextWithoutCursor= "за 24 месяца я накоплю";
  private final static String HoverTextWithCursor= "пополнения";


  @Test
  @Story("TinkoffBank deposit calculator on the website")
  @DisplayName("Positive test with calculator form")

    void successfulTestCalculatorTinkoff() {

        Configuration.fastSetValue=true;

        parameter("Валюта:", CURRENCY);
        parameter("Сумма:", AMOUNT);

        step("Открываем главную страницу", () -> {

            open("https://www.tinkoff.ru/");
        });
        step("Кликаем на 'Вклады'", () -> {
            $(byText("Вклады")).closest("a").click();
        });
        //для заморозки dom используем в консоли команду setTimeout(function(){debugger},5000) or F8
        step("В drop-down 'Валюта 'выбираем валюту $", () -> {
            $("[data-qa-type='uikit/inputBox']", 2).scrollIntoView(true);
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
        parameter(" Текст в Hover до наведения курсора:", HoverTextWithoutCursor);
        parameter("Текст в Hover с наведенным курсором",HoverTextWithCursor);

         step("Открываем страницу с калькулятором вклада", () -> {
             open("https://www.tinkoff.ru/deposit/");
         });
         step("Проверяем наличие hover с нужным текстом", () -> {
             $("div[data-qa-file=Pie]").scrollIntoView(true);
             $("div[data-qa-file=Pie]").shouldHave(text(HoverTextWithoutCursor));
         });
         step("Наводим курсор на hover", () -> {
             $("div[data-qa-file=Pie]").hover();
         });
         step("Проверяем, что текст изменился на ожидаемый", () -> {
             $("div[data-qa-file=Pie]").shouldHave(text(HoverTextWithCursor));
         });
     }

  @Test
  @Story("TinkoffBAnk Website, checkbox on the page")
  @DisplayName("Positive Test,remove one flag in the checkbox")

      void successfulRemoveOneFlagCheckbox(){
         step("Открываем страницу с калькулятором вклада",()->{
            open("https://www.tinkoff.ru/deposit/");
        });
         step("Снимаем флаг в checkbox",()->{
            $("[data-qa-type='uikit/checkbox']").scrollIntoView(true);
            $("[data-qa-type='uikit/checkbox']").click();
        });
         step("Проверяем, что флаг снят",()->{
            $("[aria-checked]").shouldNotBe(checked);

        });

         }
  @Test
  @Story("TinkoffBank Website, checkbox on the page")
  @DisplayName("Positive Test,remove all flags in the checkbox")

     void successfulRemoveAllFlagsCheckbox(){
         step("Открываем страницу с калькулятором вклада",()->{
            open("https://www.tinkoff.ru/deposit/");
        });
         step("Снимаем все флаги в checkbox",()->{
            $("[data-qa-type='uikit/checkbox']").scrollIntoView(true);
         for (SelenideElement checkbox : $$("[data-qa-type='uikit/checkbox']"))
            {checkbox.click();
            }
        });

         step("Проверяем, что все флаги сняты",()->{
            for (SelenideElement checkbox : $$("[aria-checked]"))
            {checkbox.shouldNotBe(checked);
            }

        });

    }  
}
