package com.mint.pages;

import com.mint.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By title = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Log in'"
    );
    private final By usernameField = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeTextField' AND (" +
                    "name CONTAINS[c] 'email' OR label CONTAINS[c] 'email' OR " +
                    "name CONTAINS[c] 'mobile' OR label CONTAINS[c] 'mobile' OR " +
                    "name CONTAINS[c] 'phone' OR label CONTAINS[c] 'phone')"
    );
    private final By passwordField = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeSecureTextField' OR name CONTAINS[c] 'password' OR label CONTAINS[c] 'password'"
    );
    private final By loginButton = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeButton' AND (name CONTAINS[c] 'login' OR label CONTAINS[c] 'login')"
    );
    private final Duration stepWait = Duration.ofSeconds(20);

    public LoginPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isLoginScreenVisible() {
        return isVisible(title, stepWait);
    }

    public void login(String username, String password) {
        WebDriverWait stepWaiter = new WebDriverWait(driver, stepWait);
        try {
            WebElement usernameInput = stepWaiter.until(ExpectedConditions.elementToBeClickable(usernameField));
            usernameInput.click();
            usernameInput.clear();
            usernameInput.sendKeys(username);

            WebElement passwordInput = stepWaiter.until(ExpectedConditions.elementToBeClickable(passwordField));
            passwordInput.click();
            passwordInput.clear();
            passwordInput.sendKeys(password);

            stepWaiter.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (TimeoutException e) {
            throw new AssertionError("Login screen elements not found or not clickable.", e);
        }
    }
}
