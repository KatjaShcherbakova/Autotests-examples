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


    @Step
    public void openUrlIndeed(String url){
        parameter ("url Indeed:", url);
        open(url);
    }
    @Step
    public void inputInLoginFormIndeed(String email, String password){
        parameter("email Indeed:", email);
        parameter("password Indeed",password);
        $(byText("Anmelden")).shouldBe(visible).click();
        $("#login-email-input").setValue(email);
        $("#login-password-input").setValue(password);
        $("#login-submit-button").click();
    }
    @Step
    public void loginVerificationIndeed(String mail){
        $(by("aria-label","Toggle menu")).click();
        $(".gnav-AccountMenu-user").shouldHave(text(mail));
    }
}
