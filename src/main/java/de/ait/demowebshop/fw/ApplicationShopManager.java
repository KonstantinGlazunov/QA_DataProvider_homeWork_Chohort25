package de.ait.demowebshop.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationShopManager {
    String browser;
    WebDriver driver;

    UserHelperShop user;
    CartHelper cart;

    HomePageHelperShop homePage;

    public ApplicationShopManager(String browser) {
        this.browser = browser;
    }


    public void init() {
        System.err.close();
        if(browser.equalsIgnoreCase("chrome"))
        driver = new ChromeDriver();
        if(browser.equalsIgnoreCase("OPERA_BLINK"))
            driver = new OperaDriver();
        if(browser.equalsIgnoreCase("edge"))
            driver = new EdgeDriver();
          if(browser.equalsIgnoreCase("firefox"))
            driver = new FirefoxDriver();

        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new UserHelperShop(driver);
        cart = new CartHelper(driver);
        homePage = new HomePageHelperShop(driver);
    }

    public void stop() {
        driver.quit();
    }

    public UserHelperShop getUser() {
        return user;
    }

    public CartHelper getCart() {
        return cart;
    }

    public HomePageHelperShop getHomePage() {
        return homePage;
    }
}
