package com.mint.pages;

import com.mint.base.BasePage;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IOS_OnboardingPage extends BasePage {
    private final By nextButton = By.xpath(
            "//XCUIElementTypeButton[" +
                    "contains(translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next') or " +
                    "contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next')" +
                    "]"
    );
    private final By letsGoTradingButton = By.xpath(
            "//XCUIElementTypeButton[" +
                    "contains(translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),' go trading') or " +
                    "contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'go trading')" +
                    "]"
    );
    private final Duration stepWait = Duration.ofSeconds(20);

    public IOS_OnboardingPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isOnboardingVisible() {
        return isVisible(nextButton, stepWait);
    }

    public void completeOnboardingFlow() {
        WebDriverWait stepWaiter = new WebDriverWait(driver, stepWait);

        stepWaiter.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        stepWaiter.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        stepWaiter.until(ExpectedConditions.elementToBeClickable(letsGoTradingButton)).click();
    }
}
