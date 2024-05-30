package pl.globallogic.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class SeleniumConfigurationTest {
    WebDriver driver;

    @BeforeTest
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterTest
    public void driverKill(){
        driver.quit();
    }


    @Test
    public void shouldNavigateToGooglePageAndVerifyPageTitleChrome(){

        driver.get("https://www.google.com");
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Google", pageTitle);
    }

    @Test
    public void GoogleSeleniumWebsite(){

        driver.get("https://www.google.com");
        driver.findElement(By.id("L2AGLb")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea.gLFyf")));
        WebElement inputSearch = driver.findElement(By.cssSelector("textarea.gLFyf"));
        inputSearch.clear();
        inputSearch.sendKeys("Selenium java"+ Keys.ENTER);

        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
