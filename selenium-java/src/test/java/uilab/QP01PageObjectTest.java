package uilab;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.nio.file.Files;
import java.nio.file.Path;

class QP01PageObjectTest extends BaseTest {
    @Test
    // Goal: QP-01 — a restricted custom pizza is generated, unauthenticated rating is blocked, and admin sees the pizza.
    void customPizzaReachesAdminList() throws Exception {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String pizzaName = "Lab Pizza " + stamp;
        QP01Page page = new QP01Page(driver);
        page.open();
        page.enableAdvancedAndConfigure(pizzaName);
        QP01Page.PizzaResult result = page.requestPizza();
        assertEquals(pizzaName, result.name(), "Step 4: generated pizza name");
        assertNotEquals("Knife", result.tool(), "Step 4: excluded tool must not be used");
        assertTrue(result.calories() <= 600, "Step 4: calories per slice must be <= 600");
        page.rateWithoutLogin();
        assertEquals("Please log in first.", page.ratingMessage(), "Step 5: login is required to rate");
        Path evidence = Path.of("../screenshots/selenium-qp01-customer-" + stamp + ".png");
        Files.createDirectories(evidence.getParent());
        Files.write(evidence, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        System.out.printf("QP01_CUSTOMER_RESULT name=%s tool=%s calories=%d screenshot=%s%n", result.name(), result.tool(), result.calories(), evidence);
        page.openAdminAndVerify(pizzaName, result.tool());
        Path adminEvidence = Path.of("../screenshots/selenium-qp01-admin-" + stamp + ".png");
        Files.write(adminEvidence, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        System.out.printf("QP01_ADMIN_RESULT screenshot=%s%n", adminEvidence);
    }
}
