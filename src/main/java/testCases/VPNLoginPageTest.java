package testCases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VPNLoginPage;
import util.DriverManager;
import util.DriverManagerFactory;
import util.DriverType;

public class VPNLoginPageTest extends Base {

    VPNLoginPage vpnLoginPage;
    WebDriver driver;
    DriverManager driverManager;

    public VPNLoginPageTest() {
        super();
    }


    @BeforeTest
    public void setUp()
    {
        driverManager= DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver=driverManager.getDriver();
        openBrowser();
        vpnLoginPage=new VPNLoginPage();


    }

    @Test(priority = 1)
    public void launchExtensionTest()
    {
        String originalHandle = driver.getWindowHandle();
        //Navigate to template.html to get pop up window rendered in web page
        driver.get("chrome-extension://bfidboloedlamgdmenmlbipfnccokknp/ui/popup/template.html");
    }

    @Test(priority = 2)
    public void vpnLoginTest()
    {
        vpnLoginPage.loginToVPN();

    }
}
