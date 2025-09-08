package com.wipro.CapStoneTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactUsTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://westfloridaahec.org/contact-us/");
        System.out.println("Opened Contact Us page ");
    }

    @Test(priority = 1)
    public void verifyContactHeader() {
        WebElement header = driver.findElement(By.xpath("//*[@id=\"post-127\"]/div/div[1]/div/div/div/div[1]/h3"));
        String actualText = header.getText();
        String expectedText = "CONTACT US WITH EASE";
        System.out.println("Header text found: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText), "Header text does not match!");
    } 
    
    @Test(priority = 2)
    public void verifyAddressIcon() {
        WebElement addressIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'fa-map-marker')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressIcon);
        Assert.assertTrue(addressIcon.isDisplayed(), "Address icon is not visible!");
    }

    @Test(priority = 3)
    public void verifyCallIcon() {
        WebElement callIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'fa-phone')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", callIcon);
        Assert.assertTrue(callIcon.isDisplayed(), "Call Us icon is not visible!");
    }

    @Test(priority = 4)
    public void verifyFaxIcon() {
        WebElement faxIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'fa-fax')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faxIcon);
        Assert.assertTrue(faxIcon.isDisplayed(), "Fax icon is not visible!");
    }

    @Test(priority = 5)
    public void verifyEmailIcon() {
        WebElement emailIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'fa-envelope')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailIcon);
        Assert.assertTrue(emailIcon.isDisplayed(), "Email icon is not visible!");
    }
    
    @Test(priority = 6)
    public void verifyGoogleMapFrame() {
        WebElement mapFrame = driver.findElement(By.tagName("iframe"));
        Assert.assertTrue(mapFrame.isDisplayed(), "Google Map iframe is not visible!");
        System.out.println("Google Maps iframe is visible ");
    }
    
   

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("All Contact Us validations successfully done and browser closed ");
    }
}
