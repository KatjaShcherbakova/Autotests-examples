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
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Feature("Logging")
@Tag("indeed")

public class IndeedTest extends TestBase {

    private final BaseStepsIndeed steps = new BaseStepsIndeed();

    @Test
    @Story("Login with email and password")
    @DisplayName("Positive test, login with email and password")

  void successfulLogInIndeed(){
        step("Open main Indeed  webpage ",()->{
            open(urlIndeed);
        });
        step("Input email and password in the login form",()->{
          $(byText("Anmelden")).shouldBe(visible).click();
          $("#login-email-input").setValue(emailIndeed);
          $("#login-password-input").setValue(passwordIndeed);
          $("#login-submit-button").click();
        });
        step("Login verification",()->{
          $(by("aria-label","Toggle menu")).click();
          $(".gnav-AccountMenu-user").shouldHave(text(emailIndeed));
        });

    }


     @Test
     @Story("Search job on Indeed")
     @DisplayName("Positive Test, search job on Indeed without registration")

  void successfulSearchJobWihoutLogin(){
        parameter(" Job for searchin:", jobIndeed);
        parameter("Where`re we looking for the job:", whereJobIndeed);


        steps.openUrlIndeed(urlIndeed);
//        steps.inputInLoginFormIndeed(emailIndeed,passwordIndeed);
//        steps.loginVerificationIndeed(emailIndeed);

        step("Input jobname  and region into the search field",()->{
            $("#text-input-what").setValue(jobIndeed);
            $("#text-input-where").setValue(whereJobIndeed).pressEnter();
        });
        step("Job search verification",()->{
            $("body").shouldHave(text(jobIndeed));
        });

  }
    @Test
    @Story("Search job on Indeed")
    @DisplayName("Positive Test, save first vacancy in the account")

    void successfullSaveVacancy(){
        parameter(" Job for searchin:", jobIndeed);
        parameter("Where`re we looking for the job:", whereJobIndeed);

        steps.openUrlIndeed(urlIndeed);
        steps.inputInLoginFormIndeed(emailIndeed,passwordIndeed);
        steps.loginVerificationIndeed(emailIndeed);

        step("Input jobname  and region into the search field",()->{
            $("#text-input-what").setValue(jobIndeed);
            $("#text-input-where").setValue(whereJobIndeed).pressEnter();
        });
        step("Job search verification",()->{
            $("body").shouldHave(text(jobIndeed));
        });
        step("Click on the first result",()->{
           $("#resultsCol h2").click();
        });
        step("Сlick on the heart to save the vacancy",()->{
            $(".state-picker-button").click();
        });

        //remember the job name
        String jobSaved= $("#vjs-jobinfo>div").sibling(0).getText();

        step(" Check the account that the vacancy is saved",()->{
             $(".gnav-ProfileNavLinks>div").sibling(0).click();
             $(".gnav-AccountMenu a").sibling(0).click();
             $("body").shouldHave(text(jobSaved));
        });


//         sleep(6000);
    }


}