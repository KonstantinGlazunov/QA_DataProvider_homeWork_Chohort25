package de.ait.demowebshop.fw;


import de.ait.demowebshop.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelperShop extends HelperBaseShop{
    public UserHelperShop(WebDriver driver) {
        super(driver);
    }
    public static String getRandomEmail() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3000);
        String randomEmail = "qq" + i + "@qq.qq";
        return randomEmail;
    }

    public void clickRegistrLink() {
        click(By.linkText("Register"));
    }

    public void clickOnSignOutButton() {
        click(By.linkText("Log out"));
    }

    public boolean isLogInLinkPresent() {
        return isElementPresent(By.linkText("Log in"));
    }

    public void login() {
        click(By.linkText("Log in"));
        fillAutorisationForm(new User().setEmail("qq123@qq.qq").setPassword( "Pass@1234"));
        click(By.cssSelector("input[value = 'Log in']"));
    }

    public void clickOnLoginLink() {
        click(By.linkText("Log in"));
    }

    public void clickOnLoginButton() {
        click(By.cssSelector("input[value = 'Log in']"));
    }

    public boolean isSignOutPresent() {
        return isElementPresent(By.linkText("Log out"));
    }

    public boolean isRegistrationComleted() {
        return isElementPresent(By.xpath("//div[contains(text(),'Your registration completed')]"));
    }

    public void fillRegistrationForm(User user) {
        type("#FirstName", user.getName());
        type("#LastName", user.getSurname());
        type("#Email", user.getEmail());
        type("#Password", user.getPassword());
        type("#ConfirmPassword", user.getPassword());
    }

    public void clickOnRegistrationButton() {
        click(By.cssSelector("#register-button"));
    }

    public boolean isLoginUnsuccessfull() {
        return isElementPresent(By.xpath("//span[text()='Login was unsuccessful. Please correct the errors and try again.']"));
    }

    public boolean isEmailAlreadyExist() {
        return isElementPresent(By.xpath("//li[contains(text(),'The specified email already exists')]"));
    }

    public void fillAutorisationForm(User user) {
        type("#Email", user.getEmail());
        type("#Password", user.getPassword());
    }


    public void clickOnLoginButtonForScreenCast() {
        pause(500);
        click(By.cssSelector("input[value = 'Log in']"));
    }

}
