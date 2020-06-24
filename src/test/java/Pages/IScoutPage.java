package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class IScoutPage {
//    Bootstrap bootstrap = new Bootstrap();

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
        submitButtonWithoutAuth = $(by("type", "button"));


    @Step("Open main page Immobilienscout24")
    public void openMainPageIScout(String url) {
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

    @Step("Assert the autorisation is succesful")
    public void assertAuth(String mail)
    {
        htmlBody.shouldHave(text(mail));
    }
    @Step("Hover on 'Suchen' on top menu")
    public void hoverTopMenuForSearch()
    {
        searchLink.hover().click();
    }
    @Step("Click submenu -'Mietwohnung' ")
    public void clickSubMenuApartmentForRent()
    {
        subMenuApartmentForRent.click();
    }
    @Step("Open page after authorisation for searching apartments")
    public void openSearchPageAfterAuth(String url) {
        open(url);
    }

    @Step("Input parameters for searching")
    public void inputApartParametersForSearchAuth(String town, String maxPrice, String rooms, String distance, String minArea)
    {
        fieldTownAuth.setValue(town);
        fieldTownAuth.pressTab();

        fieldPriceAuth.setValue(maxPrice);
        fieldPriceAuth.pressTab();

        fieldRoomsAuth.find(".oss-dropdown-button").click();
        dropdownRooms.$(by("data-value",rooms)).click();
//        dropdownRooms.$(byText("ab " + rooms)).click();


//        for(SelenideElement element:bootstrapRoomsAuth) {
//            String innerhtml = element.getAttribute("innerHTML");
//            System.out.println("Value from dropdown=====" + innerhtml);
//
//            if (innerhtml.contentEquals("ab " + rooms)) {
//                element.click();
//            }
//        }

        fieldDistanceAuth.find(".oss-dropdown-button").click();
        dropdownDistance.find("li[data-value*='10']").click();
//        dropdownDistance.$(byText(distance + " km")).click();


        fieldAreaAuth.setValue(minArea);
        fieldAreaAuth.pressTab();

        submitButtonAuth.click();
    }

    @Step("")
    public void inputApartParametersWithoutAuth(String town, String maxPrice, String rooms, String minArea, String distance) {
        fieldTownWithoutAuth.setValue(town).pressTab();
        fieldPriceWithoutAuth.setValue(maxPrice).pressTab();
        fieldRoomsWithoutAuth.click();
        fieldRoomsWithoutAuth.$(byText("ab " + rooms + " Zimmer")).click();
        fieldAreaWithoutAuth.setValue(minArea).pressTab();
        fieldDistanceWithoutAuth.click();
        fieldDistanceWithoutAuth.$(byText(distance + " km")).click();
        submitButtonWithoutAuth.click();

    }

}





