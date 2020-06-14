package drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Browsers.CHROME;


public class CustomWebDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setBrowserName(CHROME);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("videoFrameRate", 24);

        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        WebDriverManager.chromedriver().setup();

        if(System.getProperty("selenoid_url") != null) {
            return new RemoteWebDriver(getRemoteWebdriverUrl(), capabilities);
        } else {
            return new ChromeDriver(capabilities);
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--lang=en");
//        chromeOptions.addArguments("--window-size=500,500");
        Configuration.browserSize = "1920x1080";

        return chromeOptions;
    }

    private URL getRemoteWebdriverUrl() {
        try {
            return new URL( "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}