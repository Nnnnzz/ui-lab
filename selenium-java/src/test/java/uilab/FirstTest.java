package uilab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class FirstTest {
    @Test
    // Goal: the Selenium web form opens and its page title is correct.
    void webFormOpens() {
        String path = System.getenv("CHROMEDRIVER");
        if (path == null || path.isBlank()) throw new IllegalStateException("CHROMEDRIVER is not set");
        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            assertEquals("Web form", driver.getTitle());
        } finally { driver.quit(); }
    }
}
