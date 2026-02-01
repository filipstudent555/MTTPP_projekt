package com.filip.tests;

import com.filip.base.BaseTest;
import com.filip.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTests extends BaseTest {

    private void loginAsStandardUser() {
        new LoginPage(driver)
                .open()
                .typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLogin();
    }

    @Test
    public void TC01_validLogin_shouldOpenInventory() {
        loginAsStandardUser();

        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertEquals(inventory.getTitleText(), "Products", "Nakon logina treba biti naslov 'Products'.");
    }

    @Test
    public void TC02_invalidPassword_shouldShowError() {
        LoginPage login = new LoginPage(driver)
                .open()
                .typeUsername("standard_user")
                .typePassword("wrong_password");
        login.clickLogin();

        Assert.assertTrue(login.isErrorVisible(), "Error poruka treba biti vidljiva.");
        Assert.assertTrue(login.getErrorText().toLowerCase().contains("username and password"),
                "Error poruka treba spominjati username/password.");
    }

    @Test
    public void TC03_lockedOutUser_shouldBeBlocked() {
        LoginPage login = new LoginPage(driver)
                .open()
                .typeUsername("locked_out_user")
                .typePassword("secret_sauce");
        login.clickLogin();

        Assert.assertTrue(login.isErrorVisible(), "Locked out user treba dobiti error.");
        Assert.assertTrue(login.getErrorText().toLowerCase().contains("locked out"),
                "Error poruka treba spominjati 'locked out'.");
    }

    @Test
    public void TC04_addBackpackToCart_shouldShowBadge1() {
        loginAsStandardUser();

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();

        Assert.assertTrue(inventory.isBackpackRemoveVisible(), "Nakon dodavanja treba postojati Remove button.");
        Assert.assertEquals(inventory.getCartBadgeTextOrNull(), "1", "Cart badge treba biti 1.");
    }

    @Test
    public void TC05_checkoutWithoutFirstName_shouldShowValidationError() {
        loginAsStandardUser();

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();
        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.hasAnyItems(), "Košarica treba imati barem 1 item prije checkouta.");
        cart.clickCheckout();

        String url = driver.getCurrentUrl();
        System.out.println("URL nakon klika checkout: " + url);

        Assert.assertTrue(
            url.contains("checkout-step-one"),
            "Nismo na stranici Checkout Step one. Trenutni URL: " + url
        );

        CheckoutStepOnePage checkout = new CheckoutStepOnePage(driver);
        Assert.assertTrue(
            checkout.getTitleText().toLowerCase().contains("checkout"),
            "Title nije checkout. Title: " + checkout.getTitleText()
        );
        checkout.typeLastName("Test")
                .typePostalCode("10000");
        checkout.clickContinue();

        Assert.assertTrue(checkout.isErrorVisible(), "Treba se prikazati validacijska greška.");
        Assert.assertTrue(checkout.getErrorText().toLowerCase().contains("first name"),
                "Poruka treba tražiti First Name.");
    }
}