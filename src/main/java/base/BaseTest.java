package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ExtentManager;


public class BaseTest {

    public WebDriver driver=null;
    public static Properties prop=null;
    public ExtentReports extent= ExtentManager.getReportInstance();
    public ExtentTest logger;
    public boolean gridRun=false;

    public void init()
    {
        logger.log(Status.INFO,"In base test init ");
        if(prop==null)
        {
            try
            {
                prop=new Properties();
                System.out.println("OR proprties path "+System.getProperty("user.dir")+"/src/main/resources/OR.properties");
                FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/OR.properties");
                try {
                    prop.load(fs);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }



    public void openBrowser(String browser) {


        ChromeOptions options = new ChromeOptions();
        //Add PureVPN extension on browser launch
        options.addExtensions(new File("/Users/rabia/Desktop/vpn.crx"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(ChromeOptions.CAPABILITY, options);


        switch(browser)
        {
            case "firefox":

                cap=DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                cap.setJavascriptEnabled(true);
                System.setProperty("webdriver.gecko.driver", "/Users/rabia/Desktop/geckodriver");
                driver=new FirefoxDriver();

            break;


            case "chrome":

                System.out.println("Chrome driver init");
            //    cap=DesiredCapabilities.chrome();
             //   cap.setBrowserName("Chrome");
              //  cap.setJavascriptEnabled(true);
                System.setProperty("webdriver.chrome.driver", "/Users/rabia/Desktop/chromedriver");
                driver=new ChromeDriver(cap);
                String originalHandle = driver.getWindowHandle();
                System.out.println("Opening extension");

                //Navigate to template.html to get pop up window rendered in web page
                driver.get("chrome-extension://bfidboloedlamgdmenmlbipfnccokknp/ui/popup/template.html");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Make some manipulation to close "installation greeting message window" and switch to actual setting of extension
             //   for(String handle : driver.getWindowHandles()) {
              //      if (!handle.equals(originalHandle)) {
                        System.out.println("IN if");
                //        driver.switchTo().window(handle);
                        //click on connect
                driver.switchTo().window(originalHandle);
                        System.out.println("Before clicking connect");
                       // driver.findElement(By.xpath("//button[contains(text(),'Connect')]")).click();
                        //System.out.println("After clicking connect");
                        //driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                        System.out.println("Login button displayed "+ driver.findElement(By.xpath("//a[contains(text(),'Login')]")).isDisplayed());
                        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
                        System.out.println("After clicking login");
                driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                driver.findElement(By.xpath("//input[@id='userName']")).clear();
                driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("purevpn0m7496014");
                driver.findElement(By.xpath("//input[@id='userPass']")).sendKeys("Password@1305!");
                driver.findElement(By.xpath("//button[@id='btnLogin']")).click();
                driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
                driver.findElement(By.xpath("//button[contains(text(),'Connect')]")).click();
                WebDriverWait wait=new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Disconnect')]"))));


                System.out.println("Out of foor loop");
                driver.switchTo().window(originalHandle);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                break;


        }

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		logger.log(Status.INFO, "Browser opened successfully " + browser);

    }


        public void closeBrowser()
    {
        System.out.println("In quit function");
        driver.quit();
    }



    public boolean doLogin()
    {

        driver.get(prop.getProperty("URL"));
        driver.findElement(By.xpath(prop.getProperty("signInLink"))).click();

        try {
            waitforPagetoLoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(prop.getProperty("emailID"))).sendKeys(prop.getProperty("userName"));
        driver.findElement(By.xpath(prop.getProperty("passwordField"))).sendKeys(prop.getProperty("password"));
        driver.findElement(By.xpath(prop.getProperty("loginSubmit"))).click();
        try {
            waitforPagetoLoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isElementpresent(prop.getProperty("walletMenu")))
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    public void waitforPagetoLoad() throws InterruptedException
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        String state=(String)js.executeScript("return document.readyState");
        while(!state.equals("complete"))
        {
            Thread.sleep(5000);
            state=(String)js.executeScript("return document.readyState");

        }

    }


    public boolean isElementpresent(String xpath)
    {
        System.out.println("In is element present function");
        List <WebElement> elem=driver.findElements(By.xpath(xpath));
        System.out.println("Element size in iselement present is"+elem.size());
        if(elem.size() >0)
            return true;
        else
            return false;

    }
}
