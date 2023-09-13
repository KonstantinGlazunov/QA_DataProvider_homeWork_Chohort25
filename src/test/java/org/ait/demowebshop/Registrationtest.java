package org.ait.demowebshop;


import de.ait.demowebshop.models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @DataProvider
    public Iterator<Object[]> newUserWithCswFile() throws IOException {
        String randomEmail = app.getUser().getRandomEmail();;
        List<Object[]> testUserslist = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            testUserslist.add(new Object[]{new User().setName(split[0])
                    .setSurname(split[1])
                    .setEmail(split[2]+randomEmail)
                    .setPassword(split[3])});
            line = reader.readLine();
        }
        return testUserslist.iterator();
    }



    @Test(dataProvider = "newUserWithCswFile")
    public void PositiveRegistrationNewUserTestFromDataProviderWithCsv(User user) {

        app.getUser().fillRegistrationForm(user);
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isRegistrationComleted());
    }


}
