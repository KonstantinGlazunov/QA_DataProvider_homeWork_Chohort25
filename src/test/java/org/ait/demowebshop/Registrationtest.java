package org.ait.demowebshop;


import de.ait.demowebshop.models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registrationtest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLogInLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickRegistrLink();
    }

    @AfterMethod(enabled = false)
    public void tierDown() {
        super.tierDown();
    }


    @Test
    public void PositiveRegistrationNewUserTest() {

        String randomEmail = app.getUser().getRandomEmail();

        app.getUser().fillRegistrationForm(new User().setName("Ivan").setSurname("Ivanov").setEmail(randomEmail).setPassword("Pass@1234"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isRegistrationComleted());
    }

    @Test
    public void registrationExistedUserTest() {
        app.getUser().fillRegistrationForm(new User().setName("Ivan").setSurname("Ivanov").setEmail("qq123@qq.qq").setPassword("Pass@1234"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isEmailAlreadyExist());

    }

    @Test
    public void registrationUserWithoutEmail() {
        app.getUser().fillRegistrationForm(new User().setName("Ivan").setSurname("Ivanov").setPassword("Pass@1234"));
        app.getUser().clickOnRegistrationButton();
        app.getUser().isElementPresent(By.xpath("//span[contains(text(),'Email is required.')]"));

    }


}
