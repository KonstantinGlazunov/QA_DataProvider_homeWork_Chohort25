package org.ait.demowebshop;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class  AddProductsInCartTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLogInLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().login();
        //  app.getCart().clearCart();
        app.getHomePage().toHomePage();
    }

    // не получается очистить корзину в афтерметоде
    @Test
    public void positiveAddProductBookInCart() {
        app.getCart().clickOnFirstProduct();
        String productNameOnProductPage = app.getCart().getProductNameOnProductPage();
        app.getCart().fillInputsCaseGiftCard();
        app.getCart().clickOnAddToCartButton();
        app.getCart().toShopingCart();
        Assert.assertTrue(app.getCart().isProductInCart(productNameOnProductPage));

    }
@AfterMethod
    public void postCondition(){app.getCart().clearCart();}

}


