package tests;

import Steps.BasicStep;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

import static helpers.Environment.*;


@Feature("Testing account profile Facebook")
@Tag("facebook")

public class FacebookChangeProfile extends TestBase{

    private final BasicStep steps = new BasicStep();

    @Test
    @Story("Make changes to the profile, check the changes are saved")
    @DisplayName ("Positive test, verification of changes in the profile")

    void succesfulChangeProfileData(){
        steps.openMainPage(urlFacebook);
        steps.authorisationFacebook(emailFacebook,passwordFacebook);

        assertEquals (true,false);


    }
}
