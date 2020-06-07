package Steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class BasicStep {

    @Step ("Open the main page")
    public void openMainPage(String url){
        open(url);
    }

    @Step("Enter the emailFacebook and the passwordFacebook in the login form")
     public void authorisationFacebook(String email
            , String password){
        $(by("data-testid", "royal_email")).setValue(email);
        $(by("data-testid", "royal_pass")).setValue(password);
        $(by("data-testid", "royal_login_button")).click();
     }
}
