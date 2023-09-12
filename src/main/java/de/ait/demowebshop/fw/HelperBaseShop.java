package de.ait.demowebshop.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBaseShop {
    WebDriver driver;

    public HelperBaseShop(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();



    }

    public void type(String hashtag, String text) {
        if(text!=null){
        driver.findElement(By.cssSelector(hashtag)).click();
        driver.findElement(By.cssSelector(hashtag)).clear();
        driver.findElement(By.cssSelector(hashtag)).sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
