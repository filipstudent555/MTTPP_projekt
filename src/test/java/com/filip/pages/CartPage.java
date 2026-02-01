package com.filip.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItem = By.className("cart_item");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
    }

    public boolean hasAnyItems() {
        return !driver.findElements(cartItem).isEmpty();
    }

    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        btn.click();

        try {
            wait.until(ExpectedConditions.urlContains("checkout-step-one"));
            return;
        } catch (TimeoutException ignored) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        }
    }
}