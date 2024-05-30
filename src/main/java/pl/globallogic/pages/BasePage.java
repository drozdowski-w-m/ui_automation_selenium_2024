package pl.globallogic.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;



public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected int timeout = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void visit(String url) {
        logger.info("Navigating to '{}'", url);
        driver.get(url);
    }

    protected WebElement findElementBy(By locator) {
        logger.info("Looking for element with locator {}", locator);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    protected List<WebElement> findElementsBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
        );
    }

    protected void type(By locator, String text) {
        logger.info("Typing '{}' to element located by {}", text, locator);
        WebElement target = findElementBy(locator);
        target.clear();
        target.sendKeys(text);
    }

    protected void click(By locator) {
        findElementBy(locator).click();
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logger.warn("Thread interrupted while pausing!!!");
            throw new RuntimeException(e);
        }
    }

    protected boolean isDisplayed(By locator) {
        logger.info("Verify visibility of element located by {}", locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ex){
            logger.warn("Timeout of {} wait for {}", timeout, locator);
            return false;
        }
        return true;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
