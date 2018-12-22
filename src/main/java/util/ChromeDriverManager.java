package util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    DesiredCapabilities capabilities;
    ChromeOptions options;
    @Override
    public void createDriver() {

        capabilities=DesiredCapabilities.chrome();
        addChromeExtensions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.driver", "/Users/rabia/Desktop/chromedriver");
        driver = new ChromeDriver();

    }

    public void addChromeExtensions()
    {
        options = new ChromeOptions();
        //Add PureVPN extension on browser launch
        options.addExtensions(new File("/Users/rabia/Desktop/vpn.crx"));
    }
}
