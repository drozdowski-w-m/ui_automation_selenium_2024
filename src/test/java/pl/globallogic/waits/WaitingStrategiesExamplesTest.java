package pl.globallogic.waits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.globallogic.drivermanager.DriverManager;
import pl.globallogic.webdriver.actions.ActionsBaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class WaitingStrategiesExamplesTest extends ActionsBaseTest {

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadImplicitly() throws InterruptedException {
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = driver.findElement(By.id("landscape"));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadExplicitly(){
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadFluently(){
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilTheResultIsAvailable(){
        // 5 + 5 == 10
        String expectedResult = "10";
        By screenLocator = By.className("screen");
        driver.get(PLAYGROUND_BASE + "/slow-calculator.html");
        driver.findElement(By.xpath("//span[text()='5']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='5']")).click();
        driver.findElement(By.xpath("//span[text()='=']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(screenLocator, expectedResult));
        String actualResult = driver.findElement(screenLocator).getText();
        Assert.assertEquals(expectedResult, actualResult);
    }


    private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
    @Test
    void testScreenshotPng() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        logger.info("Screenshot created on {}", screenshot);
        Path destination = Paths.get("screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
        logger.info("Screenshot moved to {}", destination);
    }

    @Test
    void testWebElementScreenshot() throws IOException{
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement form = driver.findElement(By.tagName("form"));
        File screenshot = form.getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("webelement-screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
    }
}
