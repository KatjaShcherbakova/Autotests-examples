package basicSteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthIScout {


    @Step ("Authorisation")
    public void autorisationIScout(String url,String email, String password) {
        open(url);
        $("#link_loginAccountLink").hover().click();
        $(Selectors.byText("Profil")).click();
        $("#username").setValue(email).pressEnter();
        $("#password").setValue(password).pressEnter();
        $(".deposit-splash").parent().preceding(0).click();
        $("body").shouldHave(Condition.text(email));
    }

}
