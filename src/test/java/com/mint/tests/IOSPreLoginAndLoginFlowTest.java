package com.mint.tests;

import com.mint.base.IOSBaseTest;
import com.mint.pages.IOS_AuthSelectionPage;
import com.mint.pages.IOS_OnboardingPage;
import com.mint.pages.IOS_TrackingAlertPage;
import com.mint.pages.LoginPage;
import com.mint.pages.PinVerificationPage;
import com.mint.pages.SecurityQuestionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSPreLoginAndLoginFlowTest extends IOSBaseTest {

    @Test
    public void completePreLoginAndLoginFlow() {
        IOS_TrackingAlertPage trackingAlertPage = new IOS_TrackingAlertPage(driver, wait);
        IOS_OnboardingPage onboardingPage = new IOS_OnboardingPage(driver, wait);
        IOS_AuthSelectionPage authSelectionPage = new IOS_AuthSelectionPage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        SecurityQuestionPage securityQuestionPage = new SecurityQuestionPage(driver, wait);
        PinVerificationPage pinVerificationPage = new PinVerificationPage(driver, wait);

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

        Assert.assertTrue(
                loginPage.isLoginScreenVisible(),
                "Login screen did not appear."
        );

        loginPage.login("01282349004", "@Testing09");

        Assert.assertTrue(
                securityQuestionPage.isSecurityQuestionVisible(),
                "Security question screen did not appear."
        );

        securityQuestionPage.answerQuestion("1");

        Assert.assertTrue(
                pinVerificationPage.isPinScreenVisible(),
                "Verify PIN screen did not appear."
        );

        pinVerificationPage.enterPinWithZeroFourTimes();
    }
}
