package tests.automationpractice;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.APPage;

import static helpers.APEnvironment.*;

@Feature("Account")
@Tag("a_practice")
@Tag("auth")
public class APLoginUser  {
    APPage apPage = new APPage();

    @Test
    @Story("Authentication of an existing user")
    @DisplayName("Successful login with a valid username and a valid password")
    void successfulLoginExistingUser() {
        apPage.openMainPage(urlAP);
        apPage.clickSignIn();
        apPage.enterEmailPassword(emailAP, passwordAP);
        apPage.verifySuccessfulLogin(userNameAP, userSurnameAP);
    }

}
