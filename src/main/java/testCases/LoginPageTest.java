package testCases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.ElementValidator;
import util.Environment;

public class LoginPageTest {


    Environment env;
    LoginPage loginPage;

    @BeforeSuite
    @Parameters({"environment"})
    public void init(String environment)
    {
        ConfigFactory.setProperty("env", environment);
        env = ConfigFactory.create(Environment.class);
    }

    @Test
    public void testElemPresence()
    {

       /* loginPage.getElementValidators().
                stream().
                parallel().
                map(ElementValidator::validateElemPresence).
                forEach(Assert.assertTrue());
                */
    }

}
