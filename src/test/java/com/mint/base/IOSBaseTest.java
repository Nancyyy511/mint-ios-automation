package com.mint.base;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IOSBaseTest {
    protected IOSDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    @Parameters({
            "BROWSERSTACK_USERNAME",
            "BROWSERSTACK_ACCESS_KEY",
            "BROWSERSTACK_APP_ID",
            "BROWSERSTACK_DEVICE",
            "BROWSERSTACK_OS_VERSION",
            "BROWSERSTACK_PROJECT",
            "BROWSERSTACK_BUILD",
            "BROWSERSTACK_HUB_URL"
    })
    public void setUp(
            Method method,
            @Optional("") String bsUser,
            @Optional("") String bsKey,
            @Optional("") String bsAppId,
            @Optional("") String bsDevice,
            @Optional("") String bsOsVersion,
            @Optional("") String bsProject,
            @Optional("") String bsBuild,
            @Optional("") String bsHubUrl
    ) throws MalformedURLException {
        String userName = getRequired("BROWSERSTACK_USERNAME", bsUser);
        String accessKey = getRequired("BROWSERSTACK_ACCESS_KEY", bsKey);
        String appId = getRequired("BROWSERSTACK_APP_ID", bsAppId);

        String deviceName = getOptional("BROWSERSTACK_DEVICE", bsDevice, "iPhone 13");
        String platformVersion = getOptional("BROWSERSTACK_OS_VERSION", bsOsVersion, "17");
        String projectName = getOptional("BROWSERSTACK_PROJECT", bsProject, "Mint iOS");
        String buildName = getOptional("BROWSERSTACK_BUILD", bsBuild, "Pre-login Flow");
        String sessionName = method.getName();

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setDeviceName(deviceName);
        options.setPlatformVersion(platformVersion);
        options.setApp(appId);
        options.setAutomationName("XCUITest");
        options.setCapability("autoAcceptAlerts", true);
        options.setCapability("noReset", false);


        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", userName);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("projectName", projectName);
        bstackOptions.put("buildName", buildName);
        bstackOptions.put("sessionName", sessionName);

        options.setCapability("bstack:options", bstackOptions);

        String hubUrlValue = getOptional(
                "BROWSERSTACK_HUB_URL",
                bsHubUrl,
                "https://hub-cloud.browserstack.com/wd/hub"
        );
        URL hubUrl = new URL(hubUrlValue);
        driver = new IOSDriver(hubUrl, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected RemoteWebDriver getDriver() {
        return driver;
    }

    private String getRequired(String key, String fromSuite) {
        String value = getOptional(key, fromSuite, null);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required config value: " + key);
        }
        return value;
    }

    private String getOptional(String key, String fromSuite, String defaultValue) {
        if (fromSuite != null && !fromSuite.isBlank()) {
            return fromSuite;
        }
        String fromEnv = System.getenv(key);
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        String fromProp = System.getProperty(key);
        if (fromProp != null && !fromProp.isBlank()) {
            return fromProp;
        }
        return defaultValue;
    }
}
