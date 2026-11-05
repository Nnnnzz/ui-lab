package uilab;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page Object: all QuickPizza locators and browser interactions stay in this class. */
class QP01Page {
    static final String URL = "https://quickpizza.grafana.com/";
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By heading = By.cssSelector("h1");
    private final By advanced = By.xpath("//label[.//span[normalize-space()='Advanced']]");
    private final By calories = By.xpath("//label[normalize-space()='Max Calories per Slice']/../input");
    private final By tools = By.cssSelector("select");
    private final By knifeOption = By.xpath("//select/option[normalize-space()='Knife']");
    private final By vegetarian = By.id("default-checkbox");
    private final By pizzaName = By.cssSelector("input#pizza-name");
    private final By requestPizza = By.name("pizza-please");
    private final By nameResult = By.xpath("//p[starts-with(., 'Name:')]");
    private final By toolResult = By.xpath("//p[starts-with(., 'Tool:')]");
    private final By caloriesResult = By.xpath("//p[starts-with(., 'Calories per slice:')]");
    private final By loveIt = By.name("rate-5");
    private final By ratingResult = By.id("rate-result");

    QP01Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(heading, "Looking to break out of your pizza routine?"));
    }

    void enableAdvancedAndConfigure(String name) {
        driver.findElement(advanced).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(calories));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tools));
        wait.until(ExpectedConditions.visibilityOfElementLocated(vegetarian));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pizzaName));
        WebElement max = driver.findElement(calories);
        max.clear();
        max.sendKeys("600");
        wait.until(ExpectedConditions.presenceOfElementLocated(knifeOption));
        new Select(driver.findElement(tools)).selectByVisibleText("Knife");
        WebElement veg = driver.findElement(vegetarian);
        if (!veg.isSelected()) veg.click();
        WebElement nameBox = driver.findElement(pizzaName);
        nameBox.clear();
        nameBox.sendKeys(name);
    }

    PizzaResult requestPizza() {
        driver.findElement(requestPizza).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameResult));
        String name = driver.findElement(nameResult).getText().replaceFirst("^Name:\\s*", "").trim();
        String tool = driver.findElement(toolResult).getText().replaceFirst("^Tool:\\s*", "").trim();
        String caloriesText = driver.findElement(caloriesResult).getText().replaceFirst("^Calories per slice:\\s*", "").trim();
        return new PizzaResult(name, tool, Integer.parseInt(caloriesText));
    }

    void rateWithoutLogin() {
        driver.findElement(loveIt).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ratingResult));
    }

    void openAdminAndVerify(String name, String tool) {
        driver.get(URL + "admin");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input")));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        if (!"admin".equals(username.getDomProperty("value")) || !"admin".equals(password.getDomProperty("value"))) {
            throw new AssertionError("QuickPizza admin login fields are not prefilled with the lab test data");
        }
        By adminHeading = By.xpath("//*[contains(normalize-space(.), 'Latest pizza recommendations')] ");
        boolean signedIn = false;
        for (int attempt = 0; attempt < 2 && !signedIn; attempt++) {
            driver.findElement(By.cssSelector("button[type=submit]")).click();
            try {
                new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Latest pizza recommendations"));
                signedIn = true;
            } catch (org.openqa.selenium.TimeoutException ignored) {
                if (attempt == 1) throw ignored;
                driver.navigate().refresh();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            }
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), name));
        String row = driver.findElement(By.xpath("//*[contains(normalize-space(.), '" + name + "')]")).getText();
        if (!row.contains(tool)) throw new AssertionError("Admin list row does not contain generated tool. Row: " + row);
    }

    String ratingMessage() { return driver.findElement(ratingResult).getText().trim(); }
    record PizzaResult(String name, String tool, int calories) { }
}
