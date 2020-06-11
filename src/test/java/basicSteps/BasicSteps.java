package basicSteps;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
public class BasicSteps {

    @Step("Open the main page")
    public void openMainPage(String url){
        parameter("url Facebook", url);
        open(url);
    }

    @Step("Enter the emailFacebook and the passwordFacebook in the login form")
     public void authorisationFacebook(String email, String password){
        parameter ("Email:", email);
        parameter("Password:", password);

        $(by("data-testid", "royal_email")).setValue(email);
        $(by("data-testid", "royal_pass")).setValue(password);
        $(by("data-testid", "royal_login_button")).click();
     }

     @Step("Verification account")
     public void verificatiounAccountFacebook(String name, String surname){
        parameter("Account name:",name);
        parameter("Account surname", surname);

         $(by("data-type", "type_user")).shouldHave(text(name), text(surname));
//         $("body").shouldHave(text(name));
//         $$("body").find(visible).shouldHave(text(name),text(surname));

     }
}
