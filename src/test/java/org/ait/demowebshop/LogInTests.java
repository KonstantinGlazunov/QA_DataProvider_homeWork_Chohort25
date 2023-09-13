package org.ait.demowebshop;

import de.ait.demowebshop.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LogInTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLogInLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
    }

    @Test
    public void positiveLoginTest() {
        app.getUser().login();
        Assert.assertTrue(app.getUser().isSignOutPresent());
    }


    @Test
    public void negativeLoginTest() {
        app.getUser().fillAutorisationForm(new User().setEmail("qq123@qq.qqqqqqqqqqqqqqqqqqqq").setPassword("Pass@1234"));
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isLoginUnsuccessfull());
    }

    @Test(enabled = false)
    public void positiveLoginTestWithScreenCast() throws IOException, AWTException {
        app.getUser().deleteScreenCast();
        app.getUser().startRecording();
        app.getUser().login();
        //Assert.assertTrue(app.getUser().isSignOutPresent());
        app.getUser().pause(2000);
        app.getUser().stopRecording();
    }


}
