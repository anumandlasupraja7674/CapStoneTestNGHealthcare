package com.wipro.CapStoneTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class HomePageTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://westfloridaahec.org/");
    }

    @Test(priority = 1)
    public void openHomePage() {
        // Assert homepage logo is visible instead of title
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@class='fusion-standard-logo']")));
        Assert.assertTrue(logo.isDisplayed(), "Home page not loaded correctly");
        System.out.println("Home page loaded successfully");
    }

    @Test(priority = 2)
    public void clickOnLogo() {
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[@class='fusion-standard-logo']")));
        logo.click();
        System.out.println("Logo clicked successfully");
    }

    @Test(priority = 3)
    public void searchKeyword() {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("s")));
        searchInput.sendKeys("health");
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();
        System.out.println("Search executed successfully");
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
