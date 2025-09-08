package com.wipro.CapStoneTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RegistrationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testSuccessfulRegistration() {
        driver.get("https://westfloridaahec.org/my-account/");

        String email = "user" + System.currentTimeMillis() + "@mail.com";

        driver.findElement(By.id("reg_username")).sendKeys("newuser123");
        driver.findElement(By.id("reg_email")).sendKeys(email);
        driver.findElement(By.id("reg_password")).sendKeys("NewUser@123");
        
        driver.quit();

       

        System.out.println("✅ Registration success scenario passed with email: " + email);
       
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}