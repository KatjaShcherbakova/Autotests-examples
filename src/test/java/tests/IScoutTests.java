package tests;

import pages.IScoutPage;
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


@Feature("Account")
@Tag("iscout")
public class IScoutTests extends TestBase {

    private final AuthIScout steps= new AuthIScout();
    private final IScoutPage iScoutPage = new IScoutPage();

    //      Test with plain steps
    @Test
    @Story("Authorization")
    @DisplayName("Successful login of an existing user")
    void successfulLoginExistingUserIScout() {
        step("Open main page Immobilienscout24", () ->
                open(urlIScout));

        step("Hover over the icon for authorization", () ->
            $("#link_loginAccountLink").hover().click());
        step("Click on the 'Profil' in the dropdown menu", () ->
            $(byText("Profil")).click());
        step("Fill the authorization form", () -> {
            $("#username").setValue(emailIScout).pressEnter();
            $("#password").setValue(passwordIScout).pressEnter();
        });
        step("Close the information window", () -> {
            sleep(5000);
            $(".deposit-splash").parent().preceding(0).click();
        });

        step("Assert that the authorization is successful", () ->
            $("body").shouldHave(text(emailIScout)));
    }

    //       Tests with plain steps and most frequently used steps
    @Test
    @Story("Profile changes")
    @DisplayName("Positive test, add a information about child, then delete it")
    void successfulAddDeleteMemberOfFamily () {
        steps.autorizationIScout(urlIScout, emailIScout, passwordIScout);

        step ("Click the 'ändern' button in the 'Einziehende Personen' field", () ->
            $(".bubble-header").click());
        step ("Click 'Kind hinzufügen'", ()->
            $(byText("Kind hinzufügen")).click());
        step ("Input 'Vorname', 'Nachname' and 'Geburtsjahr'", () -> {
            $(".input-group input").setValue("Ivan");
            $(".input-group input",1).setValue("Ivanov");
            $(".input-group input",2).setValue("2010");
            $(".title button",1).click();
        });

        step ("Assert that the child ist added", ()-> {
            $("body").shouldHave(text("Ivan"), text("Ivanov"), text("2010"));
            $(byText("Speichern")).click();

        });

        step ("Click the 'ändern' button in the 'Einziehende Personen' field", () ->
            $(".bubble-header").click());
        step ("Delete the information about the child", () -> {
            $(".inhabitants-list span",7).click();
            $(".title button").click();
            $(byText("Speichern")).click();
            // todo assert deleted
        });

    }

    //              Tests with page objects
    @Test
    @Story("Check the top menu in the profile")
    @DisplayName("Go to the search page from the top menu")
    void successfulSwitchToSearchFromProfile() {
        iScoutPage.openPageIScout(urlIScout);

        iScoutPage.inputAuthData(emailIScout, passwordIScout);
        iScoutPage.assertAuth(emailIScout);
        iScoutPage.hoverTopMenuForSearch();
        iScoutPage.clickSubMenuApartmentForRent();

        iScoutPage.checkPageForText("Mietwohnung suchen");
    }

    @Test
    @Story("Saving search results in a profile with pre-authorization")
    @DisplayName("Positive search for an apartment by parameters and save the first result in the profile")
    void successfulSearchAndSaveResult() {
        iScoutPage.openPageIScout(urlIScout);

        iScoutPage.inputAuthData(emailIScout, passwordIScout);
        iScoutPage.assertAuth(emailIScout);
        iScoutPage.openPageIScout(urlPageSearchAfterAuthIScout);

        iScoutPage.inputApartParametersForSearchAuth(
                townIScout, maxPriceIScout, roomsIScout, distanceiScout, minAreaIScout);

// todo
//        iScoutPage.assertSuccessfulSearch();
//        iScoutPage.saveNameOfFirstResult();
//        iScoutPage.clickHeartSignInFirstResult();
//        iScoutPage.assertSuccessfulSavedResultInProfile();
    }

    @Test
    @Story("Search for an apartment by parameters without authorization")
    @DisplayName("Positive search for an apartment by parameters without authorisation")
    void successfulSearchApartmentWithoutAuth() {
        iScoutPage.openPageIScout(urlIScout);
        iScoutPage.inputApartParametersWithoutAuth(
                townIScout, maxPriceIScout, roomsIScout, minAreaIScout, distanceiScout);

//todo Assert
    }

    @Test
    @Story("Check the availability of files for download, section-'Umziehen'")
    @DisplayName("Successful download und check PDF file 'Mietschuldenfreiheitsbescheinigung'")
    void successfulCheckPDFFile() throws IOException {
//        String expectedText = "Mietsgfgfggfng";
        String expectedText = "Mietschuldenfreiheitsbescheinigung";

        iScoutPage.openPageIScout(urlMoving);
        iScoutPage.downloadPDFFileAndCheck(expectedText);

    }

}

