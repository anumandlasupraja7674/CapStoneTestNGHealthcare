package com.wipro.CapStoneTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class HealthProgramsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // ✅ Initialize driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // ✅ Initialize wait after driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open home page
        driver.get("https://westfloridaahec.org/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ✅ Navigate to a specific program
    private void navigateToProgram(String programName) {
        WebElement programsMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[@id='menu-item-264']//span[@class='menu-text'][normalize-space()='PROGRAMS']")
                )
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(programsMenu).perform();

        WebElement subMenuItem = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText(programName))
        );
        subMenuItem.click();

        switchToNewWindowIfOpened();
    }

    // ✅ Handle new tab/window
    private void switchToNewWindowIfOpened() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // ✅ Validation method
    private boolean isProgramContentDisplayed(String expectedText) {
        return driver.getPageSource().toLowerCase().contains(expectedText.toLowerCase());
    }

    // ====== Test Cases ======

    @Test(description = "Verify Quit Tobacco Program")
    public void testQuitTobacco() {
        navigateToProgram("Tobacco");
        Assert.assertTrue(isProgramContentDisplayed("Tobacco"),
                "Quit Tobacco content not found!");
    }

    @Test(description = "Verify Healthy Aging Program")
    public void testHealthyAging() {
        navigateToProgram("Healthy Aging");
        Assert.assertTrue(isProgramContentDisplayed("Healthy Aging"),
                "Healthy Aging content not found!");
    }

    @Test(description = "Verify AHEC Scholars Program")
    public void testAHECScholars() {
        navigateToProgram("AHEC Scholars");
        Assert.assertTrue(isProgramContentDisplayed("AHEC Scholars"),
                "AHEC Scholars content not found!");
    }

    @Test(description = "Verify Covering Florida Program")
    public void testFlorida() {
        navigateToProgram("Covering Florida");
        Assert.assertTrue(isProgramContentDisplayed("Covering Florida"),
                "Covering Florida content not found!");
    }
}
