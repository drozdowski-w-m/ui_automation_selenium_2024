package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends BasePage{
    //private final WebDriver driver;
    private final String hostUrl;
    //private final WebDriverWait wait;

    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");

    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    private final String IS_USER_LOGGED_IN_SIGN = "inventory.html";

    public LandingPage(WebDriver driver, String hostUrl) {
        super(driver);
        this.hostUrl = hostUrl;
        PageFactory.initElements(driver, this);
    }
    public void visit(){
        super.visit(this.hostUrl);
    }

    public void loginWith(String username, String password) {
        //super.type(usernameFieldLocator,username);
        //findElementBy(passwordFieldLocator).sendKeys(password + Keys.ENTER);
        //super.type(passwordFieldLocator, password + Keys.ENTER);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

    }

    public boolean isUserLoggedInSuccessfully() {
        return driver.getCurrentUrl().contains(IS_USER_LOGGED_IN_SIGN);
    }

    public boolean userLoggedUnsuccessfully(){
        return driver.findElement(By.cssSelector("button.error-button")).isDisplayed();

    }

    private WebElement findBy(By locator){
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }
}
