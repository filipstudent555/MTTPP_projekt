package com.filip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;

    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpack).click();
    }

    public boolean isBackpackRemoveVisible() {
        return !driver.findElements(removeBackpack).isEmpty();
    }

    public String getCartBadgeTextOrNull() {
        return driver.findElements(cartBadge).isEmpty() ? null : driver.findElement(cartBadge).getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}