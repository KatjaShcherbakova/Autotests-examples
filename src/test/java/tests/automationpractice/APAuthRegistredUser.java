package tests.automationpractice;

import Pages.APPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.APEnvironment.*;

@Feature("Authentication")
@Tag("a_practice")
@Tag("auth")
public class APAuthRegistredUser {
    APPage apPage = new APPage();

    @Test
    @Story("Authentication of an already registered user")
    @DisplayName("Successful test, user will be able to login with a valid username and valid password.")
    void successfulAuthRegistredUser() {
        apPage.openMainPage(urlAP);
        apPage.clickSignIn();
        apPage.enterEmailPassword(emailAP,passwordAP);
        apPage.verifySuccessfulLogin(userNameAP,userSurnameAP);
    }
//    @Test
//    @Story("")
//    @DisplayName("")

}
