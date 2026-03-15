package com.mint.tests;

import com.mint.base.IOSBaseTest;
import com.mint.pages.IOS_AuthSelectionPage;
import com.mint.pages.IOS_OnboardingPage;
import com.mint.pages.IOS_TrackingAlertPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSPreLoginFlowTest extends IOSBaseTest {

    @Test
    public void completePreLoginFlow() {
        IOS_TrackingAlertPage trackingAlertPage = new IOS_TrackingAlertPage(driver, wait);
        IOS_OnboardingPage onboardingPage = new IOS_OnboardingPage(driver, wait);
        IOS_AuthSelectionPage authSelectionPage = new IOS_AuthSelectionPage(driver, wait);

        trackingAlertPage.handleTrackingAlertIfPresent(true);

        Assert.assertTrue(
                onboardingPage.isOnboardingVisible(),
                "Onboarding screen did not appear after app launch."
        );

        onboardingPage.completeOnboardingFlow();

        Assert.assertTrue(
                authSelectionPage.isAuthSelectionVisible(),
                "Auth selection screen did not appear after onboarding."
        );

        authSelectionPage.tapLogIn();
    }
}
