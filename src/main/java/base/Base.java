package base;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import util.Environment;
import util.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver=null;
    public static Properties prop=null;
    public static Environment env;


    public Base() {
        if(prop==null)
        {
            try
            {
                prop=new Properties();
                FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/config/config.properties");
                //	Sxystem.out.println(System.getProperty("user.dir")+"/OR.properties");
                try {
                    prop.load(fs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(prop.getProperty("URL"));
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        System.out.println("FInished inti function");
    }


    public void openBrowser()
    {
        DesiredCapabilities cap=null;
        String browser=prop.getProperty("browser");
        if(browser.equalsIgnoreCase("firefox"))
        {
            cap= DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
            cap.setJavascriptEnabled(true);
            System.setProperty("webdriver.gecko.driver", "/Users/rabia/Desktop/geckodriver");
            driver=new FirefoxDriver();
        }


        else if(browser.equalsIgnoreCase("chrome"))
        {
            cap=DesiredCapabilities.chrome();
            cap.setBrowserName("Chrome");
            cap.setJavascriptEnabled(true);
            System.setProperty("webdriver.chrome.driver", "/Users/rabia/Desktop/chromedriver");
            driver=new ChromeDriver();

        }
        else
        {


        }

        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

    }
}
