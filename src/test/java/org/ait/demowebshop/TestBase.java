package org.ait.demowebshop;

import de.ait.demowebshop.fw.ApplicationShopManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationShopManager app = new ApplicationShopManager(System.getProperty("browser", BrowserType.CHROME ));

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
    public void startTest(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void stoptest(ITestResult result){
        if (result.isSuccess()){
            logger.info("PASSED: " + result.getMethod().getMethodName());
        }else {
            logger.info("FILED: " + result.getMethod().getMethodName() + " Screenshot: " + app.getUser().takeScreenShot());
        }
        logger.info("Stop test");
        logger.error("************************************************");
    }


}



