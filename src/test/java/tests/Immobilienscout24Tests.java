package tests;


import basicSteps.AuthIScout;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;

@Feature("Work with profile")
@Tag("iscout")
public class Immobilienscout24Tests extends TestBase{

    private final AuthIScout steps= new AuthIScout();

    @Test
    @Story("Authorisation")
    @DisplayName("Positive test, authorisation of already registered user ")
    //Test with plain steps
    void successfulAuthoficationIScout() {
        step("Open main page Immobilienscout24", ()-> {
            open(urlIScout);
        });
        step("Hover over the icon for authorisation", ()-> {
            $("#link_loginAccountLink").hover().click();
        });
        step("Click on the 'Profil' in the drop-down menu", ()-> {
            $(byText("Profil")).click();
        });
        step("Input the email and password", ()-> {
            $("#username").setValue(emailIScout).pressEnter();
            $("#password").setValue(passwordIScout).pressEnter();
        });
        step("Close the information window", ()-> {
            $(".deposit-splash").parent().preceding(0).click();
        });
        step("Assert that the authofication ist successful", ()-> {
            $("body").shouldHave(text(emailIScout));
        });
    }

    @Test
    @Story("Profile changes")
    @DisplayName("Positive test, add a information about child, then delete it")
    void successfulAddDeleteMemberOfFamily () {

        steps.autorisationIScout(urlIScout,emailIScout,passwordIScout);
        step ("Click the button 'ändern' in the field 'Einziehende Personen'", ()-> {
            $(".bubble-header").click();
        });
        step ("Click 'Kind hinzufügen'", ()-> {
            $(byText("Kind hinzufügen")).click();
        });
        step ("Input 'Vorname','Nachname' and 'Geburtsjahr'", ()-> {
            $(".input-group input").setValue("Ivan");
            $(".input-group input",1).setValue("Ivanov");
            $(".input-group input",2).setValue("2010");
            $(".title button",1).click();
        });
        step ("Assert that the child ist added", ()-> {
            $("body").shouldHave(text("Ivan"),text("Ivanov"),text("2010"));
            $(byText("Speichern")).click();

        });
        step ("Click the button 'ändern' in the field 'Einziehende Personen'", ()->{
            $(".bubble-header").click();
        });
        step ("Delete the information about the child", ()->{
            $(".inhabitants-list span",7).click();
            $(".title button").click();
            $(byText("Speichern")).click();

        });

    }

}
