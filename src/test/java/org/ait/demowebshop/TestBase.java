package org.ait.demowebshop;

import de.ait.demowebshop.fw.ApplicationShopManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static ApplicationShopManager app = new ApplicationShopManager(System.getProperty("browser", BrowserType.EDGE ));

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite(enabled = true)
    public void tierDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(){
        logger.info("Start test");
    }

    @AfterMethod
    public void stoptest(){
        logger.info("Stop test");
    }


}



