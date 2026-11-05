package uilab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

class WaitTest extends BaseTest {
    @Test
    // Goal: wait for a box that is added asynchronously after the user clicks Add a box!.
    void addABoxAfterExplicitWait() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box0")));
        assertEquals("redbox", driver.findElement(By.id("box0")).getDomAttribute("class"));
    }
}
