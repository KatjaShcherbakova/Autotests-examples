package tests;


import com.codeborne.selenide.Condition;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Work with files")
@Tag("file")
@Tag("exel")


public class TestFileVerification {

    @Test
    @Story("EXEL file test")
    @DisplayName("Positive test, download und  search for the text in the EXEL file")

    void successfulSearchTextinFileExel()  throws IOException {

        String expectedFileText = "Черепанова";

        String jenkinsLogin = "testuser";
        String jenkinsPassword = "testpassword%";

        open("https://jenkins.autotests.cloud/login");
        $(byName("j_username")).val(jenkinsLogin);
        $(byName("j_password")).val(jenkinsPassword).pressEnter();
        $(withText(jenkinsLogin)).shouldBe(Condition.visible);

        open("https://jenkins.autotests.cloud/job/Katja_AllTests/ws/src/test/resources/");

        File actuelFile= $("[href='ExelTestFile.xlsx']").download();
        XLS xls = new XLS(actuelFile);
        assertThat(xls, XLS.containsText(expectedFileText));
    }
}
