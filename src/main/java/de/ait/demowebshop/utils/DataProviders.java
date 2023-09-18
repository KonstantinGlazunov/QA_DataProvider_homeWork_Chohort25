package de.ait.demowebshop.utils;

import de.ait.demowebshop.models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

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
}
