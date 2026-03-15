package com.mint.tests;

import com.mint.base.IOSBaseTest;
import com.mint.pages.LoginPage;
import com.mint.pages.PinVerificationPage;
import com.mint.pages.SecurityQuestionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSLoginFlowTest extends IOSBaseTest {

    @Test
    public void completeLoginFlow() {
        LoginPage loginPage = new LoginPage(driver, wait);
        SecurityQuestionPage securityQuestionPage = new SecurityQuestionPage(driver, wait);
        PinVerificationPage pinVerificationPage = new PinVerificationPage(driver, wait);

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
