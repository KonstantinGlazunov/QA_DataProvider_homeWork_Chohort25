package de.ait.demowebshop.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartHelper  extends HelperBaseShop{
    public static final String ADD_TO_CART_BUTTON_SELECTOR = "#add-to-cart-button-2";
    String FIRST_PRODUCT_SELECTOR = ".product-item:first-of-type img[src]:first-of-type";

    public CartHelper(WebDriver driver) {
        super(driver);
    }

    public void toShopingCart() {
        click(By.xpath("//span[contains(text(),'Shopping cart')]"));
    }

    public void fillInputsCaseGiftCard() {
        WebElement recipientNameField = driver.findElement(By.cssSelector("#giftcard_2_RecipientName")); //проверяем поля giftcard
        if (recipientNameField != null) {
            type("#giftcard_2_RecipientName", "Recipient Name");

            type("#giftcard_2_RecipientEmail", "recepient@email.qq");
        }
    }

    public void clearCart() {
        toShopingCart(); //кликаем на корзину
        if (isElementPresent(By.xpath("//tbody/tr[1]/td[1]/input[1]"))) {
            click(By.xpath("//tbody/tr[1]/td[1]/input[1]"));
            click(By.cssSelector("input[value='Update shopping cart']"));
        }
    }

    public boolean isProductInCart(String productNameOnProductPage) {
        return isElementPresent(By.xpath(
                "//td[@class='product']/a[text()='" +
                        productNameOnProductPage +
                        "']"));
    }

    public void clickOnAddToCartButton() {
        click(By.cssSelector(CartHelper.ADD_TO_CART_BUTTON_SELECTOR));
    }

    public void clickOnFirstProduct() {
        click(By.cssSelector(FIRST_PRODUCT_SELECTOR));
    }

    public String getProductNameOnProductPage() {
        return driver.findElement(By.cssSelector("h1[itemprop]")).getText();
    }
}
