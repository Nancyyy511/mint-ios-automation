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

public class SecurityQuestionPage extends BasePage {
    private final By title = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeStaticText' AND name CONTAINS[c] 'security'"
    );
    private final By answerField = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeTextField' OR type == 'XCUIElementTypeTextView'"
    );
    private final By continueButton = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeButton' AND (name CONTAINS[c] 'continue' OR label CONTAINS[c] 'continue')"
    );
    private final Duration stepWait = Duration.ofSeconds(20);

    public SecurityQuestionPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isSecurityQuestionVisible() {
        return isVisible(title, stepWait);
    }

    public void answerQuestion(String answer) {
        WebDriverWait stepWaiter = new WebDriverWait(driver, stepWait);
        try {
            WebElement answerInput = stepWaiter.until(ExpectedConditions.elementToBeClickable(answerField));
            answerInput.click();
            answerInput.clear();
            answerInput.sendKeys(answer);
            stepWaiter.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        } catch (TimeoutException e) {
            throw new AssertionError("Security question elements not found or not clickable.", e);
        }
    }
}
