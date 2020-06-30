package tests;


import Pages.IScoutPage;
import basicSteps.AuthIScout;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;

@Feature("Work with profile")
@Tag("iscout")
public class Immobilienscout24Tests extends TestBase{


    private final AuthIScout steps= new AuthIScout();
    private final IScoutPage iScoutPage = new IScoutPage();

//              Test with plain steps
    @Test
    @Story("Authorisation")
    @DisplayName("Positive test, authorisation of already registered user ")
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
            sleep(5000);
            $(".deposit-splash").parent().preceding(0).click();
        });
        step("Assert that the authofication ist successful", ()-> {
            $("body").shouldHave(text(emailIScout));
        });
    }

//              Tests with plain steps and most frequently used steps

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

    @Test
    @Story("Check the top menu in the profile")
    @DisplayName("Go to the search page from the top menu")
    void successfulSwitchToSearchFromProfile() {
        iScoutPage.openPageIScout(urlIScout);
        iScoutPage.inputAuthData(emailIScout,passwordIScout);
        iScoutPage.assertAuth(emailIScout);
        iScoutPage.hoverTopMenuForSearch();
        iScoutPage.clickSubMenuApartmentForRent();
        iScoutPage.checkPageForText("Mietwohnung suchen");

    }

    @Test
    @Story("Saving search results in a profile with pre-authorisation")
    @DisplayName("Positive search for an apartment by parameters and save the first result in the profile")
    void successfulSearchAndSaveResult () {
        iScoutPage.openPageIScout(urlIScout);
        iScoutPage.inputAuthData(emailIScout,passwordIScout);
        iScoutPage.assertAuth(emailIScout);
        iScoutPage.openPageIScout(urlPageSearchAfterAuthIScout);
        iScoutPage.inputApartParametersForSearchAuth
                (townIScout, maxPriceIScout,roomsIScout,distanceiScout, minAreaIScout);

// should to do
//        iScoutPage.assertSuccessfulSearch();
//        iScoutPage.saveNameOfFirstResult();
//        iScoutPage.clickHeartSignInFirstResult();
//        iScoutPage.assertSuccessfulSavedResultInProfile();
    }

    @Test
    @Story("Search for an apartment by parameters without authorisation")
    @DisplayName("Positive serch for an apartment by parameters without authorisation")
    void successfulSearchApartmentWithoutAuth() {
        iScoutPage.openPageIScout(urlIScout);
        iScoutPage.inputApartParametersWithoutAuth(townIScout, maxPriceIScout,roomsIScout,minAreaIScout,distanceiScout);
//here should be Assert
    }

    @Test
    @Story("Checking the availability of files for download, section-'Umziehen'")
    @DisplayName("Positive test, download und check PDF file 'Mietschuldenfreiheitsbescheinigung'")
    void successfulCheckPDFFile() throws IOException {
//        String expectedText = "Mietsgfgfggfng";
        String expectedText = "Mietschuldenfreiheitsbescheinigung";

        iScoutPage.openPageIScout(urlMoving);
        iScoutPage.downloadPDFFileAndCheck(expectedText);

    }

}

