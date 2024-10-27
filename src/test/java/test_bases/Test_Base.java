package test_bases;

import static data_reader.Load_Properties.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

public class Test_Base {

    /*****************************************OBJECTS_DECLARATIONS/INSTANTIATIONS***********************************/
    protected AppiumDriver mobile_Driver;
    private UiAutomator2Options options;
    private AppiumDriverLocalService service;


    /************************************************ENVIRONMENT_DATA*********************************************/
    private final String platformName = environment_Data.getProperty("platformName");
    private final String platformVersion = environment_Data.getProperty("platformVersion");
    private final String deviceName = environment_Data.getProperty("deviceName");
    private final String automationName = environment_Data.getProperty("automationName");
    private final String appPackage = environment_Data.getProperty("appPackage");
    private final String appActivity = environment_Data.getProperty("appActivity");

    /********************************************ENVIRONMENT_CONFIGURATION***********************************/
    @SneakyThrows
    @BeforeMethod(groups = {"Regression"})
    public void setUpEnvironment() {

        /**
         * these commented desiredCapabilities are set using .setCapability method that exists in DesiredCapabilities class
         * and UiAutomator2Options class, but there are specific methods that exist only in the later class(UiAutomator2)
         * which can take the values of the capabilities directly without mentioning the name of each capability
         */
//        service = new AppiumServiceBuilder()
//                .withAppiumJS(new File("C:\\Users\\moham\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();//127.0.0.1   localhost
//        service.start();
        options = new UiAutomator2Options();
        options.setPlatformName(platformName);
        options.setPlatformVersion(platformVersion);
        options.setDeviceName(deviceName);
        options.setAutomationName(automationName);
        options.setAppPackage(appPackage);
        options.setAppActivity(appActivity);
        mobile_Driver = new AndroidDriver(new URL("http://localhost:4723"), options);
    }

}
