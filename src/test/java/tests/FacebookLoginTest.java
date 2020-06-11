package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;


@Feature("Testing account profile Facebook")
@Tag ("facebook")

public class FacebookLoginTest extends TestBase {

    @Test
    @Story("Login to existing account")
    @DisplayName("Positive test, account verification XXXX")

    void successfulLogin () {

    //arrange
        step("Open the main page", ()->{
            open(urlFacebook);
        });

    //act
        step("Enter the emailFacebook and the passwordFacebook in the login form",()->{
            $(by("data-testid", "royal_email")).setValue(emailFacebook);
            $(by("data-testid", "royal_pass")).setValue(passwordFacebook);
            $(by("data-testid", "royal_login_button")).click();

        });
     //assert

        step("Login validation", ()->{
//           possible variants for asserts
//            $(by("data-type", "type_user")).shouldHave(text(nameFacebook), text(surnameFacebook));
//            $("body").shouldHave(text(nameFacebook+" "+surnameFacebook));
            $$("body").find(visible).shouldHave(text(nameFacebook),text(surnameFacebook));

        });

    }
}
