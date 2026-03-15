package com.mint.pages;

import com.mint.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IOS_TrackingAlertPage extends BasePage {
    private final List<String> acceptLabels = List.of(
            "Allow",
            "Allow While Using App"
    );
    private final List<String> denyLabels = List.of(
            "Ask App Not to Track",
            "Don't Allow",
            "Don’t Allow"
    );
    private final Duration shortWait = Duration.ofSeconds(3);

    public IOS_TrackingAlertPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean handleTrackingAlertIfPresent(boolean acceptTracking) {
        try {
            new WebDriverWait(driver, shortWait)
                    .until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ignored) {
            return false;
        }

        try {
            if (acceptTracking) {
                if (clickFirstMatchingAlertButton(acceptLabels)) {
                    return true;
                }
                driver.switchTo().alert().accept();
                return true;
            }
            if (clickFirstMatchingAlertButton(denyLabels)) {
                return true;
            }
            driver.switchTo().alert().dismiss();
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    private boolean clickFirstMatchingAlertButton(List<String> labels) {
        for (String label : labels) {
            By locator = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name CONTAINS[c] '" + label + "'");
            if (isVisible(locator, shortWait)) {
                click(locator, shortWait);
                return true;
            }
        }
        return false;
    }
}
