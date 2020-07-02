package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class APPage {
    public static SelenideElement
        bodyHtml = $("body"),
        signIn = $(".header_user_info"),
        emailFieldSingIn = $("#login_form input[id=email]"),
        passwordFieldSingIn = $("#login_form input[id=passwd]");

    @Step("Open main page automationpractice.com")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Click in the upper rihgt conner  on 'Sign in'")
    public void clickSignIn() {
        signIn.click();
    }

    @Step("Fill the form 'ALREADY REGISTERED?'")
    public void enterEmailPassword(String email, String pasword) {
        emailFieldSingIn.setValue(email);
        passwordFieldSingIn.setValue(pasword).pressEnter();
    }

    @Step("Assert that login ist successful")
    public void verifySuccessfulLogin(String name, String surname) {
        bodyHtml.shouldHave(text(name), text(surname));
    }

}
