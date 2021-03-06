package basicSteps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;

public class BaseStepsIndeed {

    @Step ("Open main Indeed webpage")
    public void openUrlIndeed(String url){
        parameter ("url Indeed:", url);
        open(url);
    }

    @Step ("Fill the login form")
    public void inputInLoginFormIndeed(String mail, String password){
        parameter("email Indeed:", mail);
        parameter("password Indeed",password);

        $(byText("Anmelden")).shouldBe(visible).click();
        $("#login-email-input").setValue(mail);
        $("#login-password-input").setValue(password);
        $("#login-submit-button").click();
    }

    @Step ("Login verification")
    public void loginVerificationIndeed(String mail){
        $(by("aria-label","Toggle menu")).click();
        $(".gnav-AccountMenu-user").shouldHave(text(mail));
    }
}
