package com.filip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutStepOnePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By errorMsg = By.cssSelector("[data-test='error']");
    private final By title = By.className("title");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
    }

    public CheckoutStepOnePage typeLastName(String v) {
        wait.until(ExpectedConditions.elementToBeClickable(lastName)).clear();
        driver.findElement(lastName).sendKeys(v);
        return this;
    }

    public CheckoutStepOnePage typePostalCode(String v) {
        wait.until(ExpectedConditions.elementToBeClickable(postalCode)).clear();
        driver.findElement(postalCode).sendKeys(v);
        return this;
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isErrorVisible() {
        return !driver.findElements(errorMsg).isEmpty();
    }

    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }
}