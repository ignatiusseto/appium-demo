import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Created by igi on 05/07/15.
 */
public class AppiumIOS {
    private AppiumDriver driver;

    @BeforeMethod
    public void setupTest(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platform-version", "7.1");
        capabilities.setCapability("deviceName", "iPhone Simulator");
        String appPath = "/Volumes/DATA/development/projects/school/angularjs_projects/appium/assets/TestApp7.1.app.zip";
        capabilities.setCapability("app", appPath);
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities) {
                public WebElement scrollTo(String s) {
                    return null;
                }

                public WebElement scrollToExact(String s) {
                    return null;
                }
            };
        } catch(MalformedURLException e) {
                e.printStackTrace();
            }
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null) driver.quit();
    }

    @Test
    public void simpleTest(){
        driver.findElementByName("IntegerA").sendKeys("10");
        driver.findElementByName("IntegerB").sendKeys("15");
        driver.findElementByName("computeSumButton").click();
        int answer = Integer.parseInt(driver.findElementByName("Answer").getText());
        assertEquals(answer, 25);
    }
}
