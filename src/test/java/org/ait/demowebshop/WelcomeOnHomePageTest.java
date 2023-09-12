package org.ait.demowebshop;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WelcomeOnHomePageTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getHomePage().isWelcomePresent()) {
            app.getHomePage().toHomePage();
        }
    }

    @Test
    public void isWelcomeComponentPresent() {
        Assert.assertTrue(app.getHomePage().isWelcomePresent());
    }

}
