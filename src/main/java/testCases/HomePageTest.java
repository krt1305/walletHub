package testCases;

import base.Base;
import org.testng.annotations.BeforeTest;

public class HomePageTest extends Base {

    public HomePageTest() {
        super();
    }

    @BeforeTest
    public void setUp()
    {

        openBrowser();

    }
}
