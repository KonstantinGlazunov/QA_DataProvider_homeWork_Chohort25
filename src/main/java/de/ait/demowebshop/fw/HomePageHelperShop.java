package de.ait.demowebshop.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelperShop extends HelperBaseShop{
    public HomePageHelperShop(WebDriver driver) {
        super(driver);
    }

    public void toHomePage() {
        click(By.cssSelector("img[title]:first-of-type"));
    }

    public boolean isWelcomePresent() {
        return isElementPresent(By.xpath("//h2[contains(text(),'Welcome to our store!')]"));
    }
}
