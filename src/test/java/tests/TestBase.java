package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.AutomationPracticeFormPage;
import pages.TextBoxPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.TestProperties.baseUrl;
import static helpers.TestProperties.browser;
import static helpers.TestProperties.browserSize;
import static helpers.TestProperties.browserVersion;
import static helpers.TestProperties.headless;
import static helpers.TestProperties.remoteCredentials;
import static helpers.TestProperties.remoteUrl;

public class TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    AutomationPracticeFormPage automationPracticeFormPage = new AutomationPracticeFormPage();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeAll
    static void configurationBrowser() {
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = baseUrl;
        Configuration.browserVersion = browserVersion;
        Configuration.headless = headless;
        Configuration.remote = "https://" + remoteCredentials + "@" + remoteUrl;


        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();

                options.addArguments(
                        "--disable-dev-shm-usage",
                        "--no-sandbox"
                );

                Configuration.browserCapabilities = options;
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();

                Configuration.browserCapabilities = options;
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        if (Configuration.remote != null) {
            Configuration.browserCapabilities.setCapability(
                    "selenoid:options",
                    Map.of(
                            "enableVNC", true,
                            "enableVideo", true
                    )
            );
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.addVideo();
        closeWebDriver();
    }
}
