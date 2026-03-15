import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Map;

public class FirstIOSTest {

    @Test
    public void launchApp() throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName("iOS");
        options.setDeviceName("iPhone 13");
        options.setPlatformVersion("17");
        options.setApp("bs://38001f23fc41c131354c5260c6cc33391690e87b");

        options.setCapability("bstack:options", Map.of(
                "userName", "nancyawny_ghQmBl",
                "accessKey", "LGk3fZq74N9FLhLBza1g",
                "projectName", "Mint-iOS",
                "buildName", "Mint.ipa",
                "sessionName", "Launch App Test"
        ));
        options.setCapability("autoAcceptAlerts", true);


        IOSDriver driver = new IOSDriver(
                new URL("https://hub-cloud.browserstack.com/wd/hub"),
                options
        );

        System.out.println("App launched successfully!");

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        driver.quit();
    }

}
