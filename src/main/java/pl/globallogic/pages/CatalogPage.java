package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage extends BasePage{
    //private final WebDriver driver;
    //private final WebDriverWait wait;

    private final By itemsCountBadge = By.className("shopping_cart_badge");

    private final String ITEM_SELECTOR = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";


    public CatalogPage(WebDriver driver) {
        super(driver);
    }



    private WebElement findBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public void addToCart(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).click();
    }

    public void removeFromCart(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).click();
    }

    public int cartItemsCount() {
        return Integer.parseInt(findBy(itemsCountBadge).getText());
    }

    public boolean isCartEmpty(){
        return !(driver.findElements(itemsCountBadge).size() > 0);
    }
}
