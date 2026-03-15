package com.mint.pages;

import com.mint.base.BasePage;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IOS_AuthSelectionPage extends BasePage {
    private final By loginButton = By.xpath(
            "//XCUIElementTypeButton[contains(@name,'Login') or contains(@label,'Login')]"
    );
    private final By signUpButton = By.xpath(
            "//XCUIElementTypeButton[contains(@name,'Sign Up') or contains(@label,'Sign Up')]"
    );
    private final Duration screenWait = Duration.ofSeconds(12);

    public IOS_AuthSelectionPage(IOSDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isAuthSelectionVisible() {
        return isVisible(loginButton, screenWait) || isVisible(signUpButton, screenWait);
    }

    public void tapLogIn() {
        click(loginButton);
    }
}
