package com.mint.pages;

import com.mint.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PinVerificationPage extends BasePage {
    private final By title = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'Enter PIN'"
    );
    private final By zeroKey = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeButton' AND name == '0'"
    );
    private final Duration stepWait = Duration.ofSeconds(20);

    public PinVerificationPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isPinScreenVisible() {
        return isVisible(title, stepWait);
    }

    public void enterPinWithZeroFourTimes() {
        WebDriverWait stepWaiter = new WebDriverWait(driver, stepWait);
        try {
            for (int i = 0; i < 4; i++) {
                stepWaiter.until(ExpectedConditions.elementToBeClickable(zeroKey)).click();
            }
        } catch (TimeoutException e) {
            throw new AssertionError("PIN keypad not available or not clickable.", e);
        }
    }
}
