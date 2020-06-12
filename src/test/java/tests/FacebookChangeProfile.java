package tests;


import basicSteps.BasicSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Environment.*;
import static io.qameta.allure.Allure.step;


@Feature("Testing account profile Facebook")
@Tag("facebook")

public class FacebookChangeProfile extends TestBase{

    private final BasicSteps steps = new BasicSteps();

    @Test
    @Story("Make changes to the profile, check the changes are saved")
    @DisplayName ("Positive test, verification of changes in the profile")

    public void succesfulChangeProfileData(){

        steps.openMainPage(urlFacebook);
        steps.authorisationFacebook(emailFacebook,passwordFacebook);
        steps.verificatiounAccountFacebook(nameFacebook, surnameFacebook);

        step("Enter the profile for editing", ()->{
            $(by("data-type", "type_user")).click();
        });

        step("Click on the field Hometown",()->{
           $(byText("Hometown")).click();
        } );

        step("Enter the name of the hometown ",()->{
           $("#hometown").click();
           $("#pagelet_hometown label>[aria-autocomplete='list']").setValue("Saint Petersburg");
//          the variant to enter the data
//           $(by("role","combobox"),1).setValue("Saint Petersburg"");
           $(byText("Saint Petersburg, Russia")).click();
           $(by("name","__submit__")).click();
           $(by("aria-label","Close")).click();
        });

        step("Verify saving changes",()->{
            $("body").shouldHave(text("Saint Petersburg"));
        });

        step("Delete the information about Hometown",()->{
            $(by("data-tab-key","about")).click();
            $(by("aria-label","Remove")).click();
            $(".layerConfirm[type='submit']").click();

        });


    }
}
