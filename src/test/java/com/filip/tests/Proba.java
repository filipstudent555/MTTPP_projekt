package com.filip.tests;

import com.filip.base.BaseTest;
import org.testng.annotations.Test;

public class Proba extends BaseTest {
    @Test
    public void shouldOpenSauceDemoHome() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);
    }
}