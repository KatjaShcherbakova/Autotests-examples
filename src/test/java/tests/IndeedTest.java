package tests;

import basicSteps.BaseStepsIndeed;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;


@Feature("Logging")
@Tag("indeed")
public class IndeedTest extends TestBase {
    private final BaseStepsIndeed steps = new BaseStepsIndeed();
    String jobSaved = "";

//                PLAIN STEPS
    @Test
    @Story("Login with email and password")
    @DisplayName("Positive test, login with email and password")
    void successfulLogInIndeed(){
        step("Open main Indeed webpage", () ->
            open(urlIndeed));

        step("Fill the login form", () -> {
            $(byText("Anmelden")).shouldBe(visible).click();
            $("#login-email-input").setValue(emailIndeed);
            $("#login-password-input").setValue(passwordIndeed);
            $("#login-submit-button").click();
        });

        step("Assert that login is successful", () -> {
            $(by("aria-label","Toggle menu")).click();
            $(".gnav-AccountMenu-user").shouldHave(text(emailIndeed));
        });

    }

     @Test
     @Story("Search for a job on Indeed")
     @DisplayName("Successful job search on Indeed without registration")
     void successfulSearchJobWihoutAth(){
        parameter(" Job for searching:", jobIndeed);
        parameter("Where`re we looking for the job:", whereJobIndeed);

        step("Open main page", () ->
            open(urlIndeed));

        step("Enter jobname  and region into the search field", () -> {
            $("#text-input-what").setValue(jobIndeed);
            $("#text-input-where").setValue(whereJobIndeed).pressEnter();
        });
        step("Assert that job search is successful", () ->
            $("body").shouldHave(text(jobIndeed)));
    }

//          TESTS WITH FREQUENTLY USED STEPS
    @Test
    @Story("Search for a job on Indeed")
    @DisplayName("Successful save first vacancy in the account")

    void successfullSaveVacancy() {
        parameter(" Job for searching:", jobIndeed);
        parameter("Where`re we looking for the job:", whereJobIndeed);

        steps.openUrlIndeed(urlIndeed);
        steps.inputInLoginFormIndeed(emailIndeed,passwordIndeed);
        steps.loginVerificationIndeed(emailIndeed);

        step("Enter the  jobname  and the region into the search field", () -> {
            $("#text-input-what").setValue(jobIndeed);
            $("#text-input-where").setValue(whereJobIndeed).pressEnter();
        });
        step("Job search verification", () ->
            $("body").shouldHave(text(jobIndeed)));
        step("Click on the first result", () ->
            $("#resultsCol h2").click());
        step("Сlick on the heart to save the vacancy", () ->
            $(".state-picker-button").click());
        step("Remember the first search result", ()->
           jobSaved=  $("#vjs-jobinfo [id=vjs-cn]").getText());
        step("Check the account that the vacancy is saved", () -> {
            $(".gnav-ProfileNavLinks>div").sibling(0).click();
            $(".gnav-AccountMenu a").sibling(0).click();
            $("body").shouldHave(text(jobSaved));
        });
    }

}