package Pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class IScoutPage {

    SelenideElement
        accountSign = $("#link_loginAccountLink"),
        subMenuProfile = $(byText("Profil")),
        loginFieldEmail = $("#username"),
        getLoginFieldPassword = $("#password"),
        closeButtonOfSplashAdv = $(".deposit-splash"),
        htmlBody = $("body"),
        searchLink = $(".topnavigation__scroll_navigation label"),
        subMenuApartmentForRent = $(byText("Mietwohnungen")),

        fieldTownAuth = $(".oss-location-container input"),
        fieldPriceAuth = $(".one-step-search__pretty-label-container input"),
        fieldRoomsAuth = $(".oss-no-of-rooms"),
        dropdownRooms = $(".oss-no-of-rooms ul"),
        fieldDistanceAuth = $(".oss-radius"),
        dropdownDistance = $(".oss-radius ul"),
        fieldAreaAuth = $(".one-step-search__pretty-label-container input", 1),
        submitButtonAuth = $(by("type", "submit")),

        fieldTownWithoutAuth = $(".oss-location-container input"),
        fieldPriceWithoutAuth = $(".oss-price"),
        fieldRoomsWithoutAuth = $(".oss-rooms"),
        fieldDistanceWithoutAuth = $(".oss-radius"),
        fieldAreaWithoutAuth = $(".oss-area"),
        submitButtonWithoutAuth = $(by("type", "button")),
        filePDF = $(byText("Mietschuldenfreiheitsbestätigung"));




    @Step("Open main page Immobilienscout24")
    public void openPageIScout(String url) {
        open(url);
    }

    @Step("Input authorisation data in the login form")
    public void inputAuthData(String email, String password) {
        accountSign.hover().click();
        subMenuProfile.click();
        loginFieldEmail.setValue(email).pressEnter();
        getLoginFieldPassword.setValue(password).pressEnter();
        closeButtonOfSplashAdv.parent().preceding(0).click();
    }

    @Step("Assert the authorisation is succesful")
    public void assertAuth(String mail) {
        htmlBody.shouldHave(text(mail));
    }
    @Step("Hover on 'Suchen' on top menu")
    public void hoverTopMenuForSearch() {
        searchLink.hover().click();
    }
    @Step("Click submenu -'Mietwohnung' ")
    public void clickSubMenuApartmentForRent() {
        subMenuApartmentForRent.click();
    }
    @Step("Check for text on page")
    public void checkPageForText(String text) {
        htmlBody.shouldHave(text(text));
    }
    @Step("Entering parameters for apartment search with pre-authorization")
    public void inputApartParametersForSearchAuth
            (String town, String maxPrice, String rooms, String distance, String minArea) {
        fieldTownAuth.setValue(town);
        fieldTownAuth.pressTab();

        fieldPriceAuth.setValue(maxPrice);
        fieldPriceAuth.pressTab();

        fieldRoomsAuth.find(".oss-dropdown-button").click();
        sleep(6000);
        SelenideElement room = dropdownRooms.$(byText("ab " + rooms));
        room.click();
        String sr = room.getText();
        System.out.println(sr);

//         variant to locate value
//        dropdownRooms.$(by("data-value",rooms)).click();


/*       loop and if realisation
        for(SelenideElement element:bootstrapRoomsAuth) {
            String innerhtml = element.getAttribute("innerHTML");
            System.out.println("Value from dropdown=====" + innerhtml);

            if (innerhtml.contentEquals("ab " + rooms)) {
                element.click();
            }
       }
*/
        fieldDistanceAuth.find(".oss-dropdown-button").click();
        dropdownDistance.$(withText(distance)).click();
//        dropdownDistance.$(byText(distance + " km")).click();

        fieldAreaAuth.setValue(minArea);
        fieldAreaAuth.pressTab();
        submitButtonAuth.click();
    }

    @Step("Entering parameters for finding an apartment without pre-authorization")
    public void inputApartParametersWithoutAuth
            (String town, String maxPrice, String rooms, String minArea, String distance) {
        fieldTownWithoutAuth.setValue(town).pressTab();
        fieldPriceWithoutAuth.setValue(maxPrice).pressTab();
        fieldRoomsWithoutAuth.click();
        fieldRoomsWithoutAuth.$(byText("ab " + rooms + " Zimmer")).click();
        fieldAreaWithoutAuth.setValue(minArea).pressTab();
        fieldDistanceWithoutAuth.click();
        fieldDistanceWithoutAuth.$(byText(distance + " km")).click();
        submitButtonWithoutAuth.click();

    }
    @Step("Download the PDF file und check it for expected text")
    public void downloadPDFFileAndCheck(String text) throws IOException, NullPointerException {
        File actuelFile= filePDF.download();
        PDF pdf = new PDF(actuelFile);

        assertThat(pdf, PDF.containsText(text));

    }


}





