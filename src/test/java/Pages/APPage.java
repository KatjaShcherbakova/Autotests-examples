package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class APPage {
    SelenideElement
        bodyHtml = $("body"),
        signIn = $(".header_user_info"),
        emailFieldSingIn = $("#login_form input[id=email]"),
        passwordFieldSingIn = $("#login_form input[id=passwd]");


    @Step("Open the main page")
    public void openMainPage(String url) {
        open(url);
    }
    @Step("click in the upper rihgt conner 'Sign in'")
    public void clickSignIn() {
        signIn.click();
    }
    @Step("Enter the email and the password in the form 'ALREADY REGISTERED?'")
    public void enterEmailPassword(String email,String pasword) {
        emailFieldSingIn.setValue(email);
        passwordFieldSingIn.setValue(pasword).pressEnter();
    }
    @Step("Verify if login ist succesful")
    public void verifySuccessfulLogin(String name, String surname) {
        bodyHtml.shouldHave(text(name), text(surname));
    }

}
