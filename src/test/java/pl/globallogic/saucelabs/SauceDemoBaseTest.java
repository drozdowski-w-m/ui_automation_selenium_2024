package pl.globallogic.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pl.globallogic.drivermanager.DriverManager;
import pl.globallogic.pages.CatalogPage;
import pl.globallogic.pages.LandingPage;

public class SauceDemoBaseTest
{
    protected WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected String sauceDemoUrl = "https://www.saucedemo.com";
    protected String standardUser = "standard_user";
    protected String lockedOutUser = "locked_out_user";
    protected String performanceGlitchUser = "performance_glitch_user";
    protected String password = "secret_sauce";
    //String itemName = "";
    protected LandingPage landingPage;
    protected CatalogPage catalogPage;


    @BeforeTest
    public void driverSetup(){

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("-private");
        driver = DriverManager.getDriver(System.getProperty("browser"));
        //driver = new ChromeDriver(chromeOptions);

        landingPage = new LandingPage(driver, sauceDemoUrl);
        catalogPage = new CatalogPage(driver);
    }
    @AfterTest
    public void driverKill(){
        driver.quit();
    }

}
