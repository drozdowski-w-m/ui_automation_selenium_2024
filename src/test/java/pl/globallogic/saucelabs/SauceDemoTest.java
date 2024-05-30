package pl.globallogic.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import pl.globallogic.pages.CatalogPage;
import pl.globallogic.pages.LandingPage;

import java.time.Duration;
import java.util.List;



public class SauceDemoTest extends SauceDemoBaseTest{



    private void addItemToTheCart(WebDriver driver, String itemName) {
        //get element add to cart button using item name and click it
        String selectorBlueprint = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";
        driver.findElement(By.xpath(selectorBlueprint.formatted(itemName))).click();

    }

    @Test
    public void shouldSuccessfullyLoginStandardUser(){
        //LandingPage landingPage = new LandingPage(driver,sauceDemoUrl);
        System.out.println(System.getProperty("browser"));
        landingPage.visit();
        landingPage.loginWith(standardUser, password);
        Assert.assertTrue(landingPage.isUserLoggedInSuccessfully());
        /*
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(standardUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys(password+Keys.ENTER);

        String expectedText = "Products";
        WebElement cartProducts = driver.findElement(By.cssSelector("span[data-test='title']"));
        Assert.assertEquals(expectedText, cartProducts.getText());*/
    }

    @Test
    public void shouldShowAnErrorForLockedOutUser(){

        landingPage.visit();
        landingPage.loginWith(lockedOutUser, password);
        Assert.assertTrue(landingPage.userLoggedUnsuccessfully());
        /*
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(lockedOutUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys(password+Keys.ENTER);

        boolean isErrorDisplayed = driver.findElement(By.cssSelector("button.error-button")).isDisplayed();
        Assert.assertEquals(isErrorDisplayed,true);

         */
    }

    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromCatalogView(){
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(standardUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys(password+Keys.ENTER);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();


        String isItemAdded = "Remove";
        String canRemove = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(canRemove, isItemAdded);
        //System.out.println(canRemove);
       // String price = driver.findElement(By.xpath("//div[contains(@class,'inventory-item-price')]")).getText();
       // System.out.println(price);

        //shopping_cart_badge
//        boolean sizeOfCart = driver.findElement(By.cssSelector("span[data-test='shopping_cart_badge']")).getSize() != null;
        //    isPresent = true;
  //      System.out.println(sizeOfCart);
    //    Assert.assertEquals(sizeOfCart,1);
        //item_4_title_link

    }

    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromCatalogView2(){

        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;
        //LandingPage landingPage = new LandingPage(driver, sauceDemoUrl);
        landingPage.visit();
        landingPage.loginWith(standardUser, password);
        //CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);
    }

    @Test
    public void shouldRemoveSauceLabsBackpackFromCartOnCatalogPage(){

        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;
        boolean cartEmpty = true;

        landingPage.visit();
        landingPage.loginWith(standardUser, password);

        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);

        catalogPage.removeFromCart(itemName);
        Assert.assertEquals(cartEmpty,catalogPage.isCartEmpty());


        //driver.findElement(By.id("remove-sauce-labs-backpack")).click();


    }

    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromItemDetailsView(){

        landingPage.visit();
        landingPage.loginWith(standardUser,password);


        driver.findElement(By.id("item_4_title_link")).click();
        driver.findElement(By.id("add-to-cart")).click();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String isItemAdded = "Remove";
        String canRemove = driver.findElement(By.id("remove")).getText();
        Assert.assertEquals(canRemove, isItemAdded);


    }

    @Test
    public void shouldRemoveSauceLabsBackpackFromCartFromItemDetailsView(){
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(standardUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys(password+Keys.ENTER);

        driver.findElement(By.id("item_4_title_link")).click();
        driver.findElement(By.id("add-to-cart")).click();
        driver.findElement(By.id("remove")).click();

        String isItemAdded = "Add to cart";
        String canRemove = driver.findElement(By.id("add-to-cart")).getText();
        Assert.assertEquals(canRemove, isItemAdded);
    }

    @Test
    public void shouldHavePriceInformationForItemInACart(){
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(standardUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
        inputPassword.sendKeys(password+Keys.ENTER);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String isItemAdded = "Remove";
        String canRemove = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(canRemove, isItemAdded);

        List<WebElement> divsWithInventoryPrices = driver.findElements(By.cssSelector("div.inventory_item_price"));
        String firstPriceInCatalogue = divsWithInventoryPrices.get(0).getText();
        //System.out.println(firstPrice);
        driver.get("https://www.saucedemo.com/cart.html");

        List<WebElement> divsWithPricesInCart = driver.findElements(By.cssSelector("div.inventory_item_price"));
        String firstPriceInCart = divsWithPricesInCart.get(0).getText();

        Assert.assertEquals(firstPriceInCart,firstPriceInCatalogue);
    }

    @Test
    public void shouldAddListOfItemsToTheCart() throws InterruptedException {
        driver.get(sauceDemoUrl);

        WebElement inputUser = driver.findElement(By.id("user-name"));
        inputUser.clear();
        inputUser.sendKeys(standardUser);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.clear();
      //  Thread.sleep(3000);
        inputPassword.sendKeys(password+Keys.ENTER);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String isItemAdded = "Remove";
        String canRemove = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(canRemove, isItemAdded);
        //Thread.sleep(3000);
        List<WebElement> divsWithInventoryPrices = driver.findElements(By.cssSelector("div.inventory_item_price"));
        String firstPriceInCatalogue = divsWithInventoryPrices.get(0).getText();
        //System.out.println(firstPrice);
        driver.get("https://www.saucedemo.com/cart.html");

        List<WebElement> divsWithPricesInCart = driver.findElements(By.cssSelector("div.inventory_item_price"));
        String firstPriceInCart = divsWithPricesInCart.get(0).getText();
        Thread.sleep(3000);


    }





}
