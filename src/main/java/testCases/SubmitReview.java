package testCases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import listeners.EventCapture;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SubmitReview extends BaseTest {

    @Test
    public void submitReview()
    {
        logger=extent.createTest(this.getClass().getTypeName());
        logger.log(Status.INFO,"Opening browser");
        init();
        openBrowser("chrome");
        doLogin();
        gotoProfilePage();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("ratingStars"))));
        //hover over the stars
        WebElement element = driver.findElement(By.xpath(prop.getProperty("ratingStars")));

        Actions action = new Actions(driver);

        action.moveToElement(element).build().perform();


        WebElement ratings = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("ratingBar"))));
        element = driver.findElement(By.xpath(prop.getProperty("fourStar")));

        action.moveToElement(element).build().perform();
        element = driver.findElement(By.xpath(prop.getProperty("fiveStar")));
        action.moveToElement(element).build().perform();
        //action.moveToElement(element).click();
        driver.findElement(By.xpath(prop.getProperty("fiveStar"))).click();

      /*  EventFiringWebDriver eventHandler = new EventFiringWebDriver(driver);
        EventCapture eCapture = new EventCapture();
        //Registering with EventFiringWebDriver
        //Register method allows to register our implementation of WebDriverEventListner to listen to the WebDriver events
        eventHandler.register(eCapture);
        eCapture.afterClickOn(element,driver);
        eventHandler.unregister(eCapture);*/



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //click on five star rating

        //Assert.assertEquals(true, driver.findElement(By.xpath("overallRating")).isDisplayed());
       // Assert.assertEquals(true, driver.findElement(By.xpath("ratingLabel")).isDisplayed());
       // Assert.assertEquals(true, driver.findElement(By.xpath("mainPage")).isDisplayed());

        //select policy type health
        driver.findElement(By.xpath(prop.getProperty("policyDropDown"))).click();
        driver.findElement(By.xpath(prop.getProperty("healthDropDownOption"))).click();

        //click on 5th star

        List<WebElement> stars=driver.findElements(By.xpath(prop.getProperty("emptyStar")));
        stars.get(4).click();

        //wait for rating label to disappear
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("ratingLabel"))));

        //click in review box
        driver.findElement(By.xpath(prop.getProperty("reviewBox"))).click();
        String generatedString = RandomStringUtils.randomAlphabetic(200);
        System.out.println("Random string "+generatedString);
        driver.findElement(By.xpath(prop.getProperty("reviewBox"))).sendKeys(generatedString);
        driver.findElement(By.xpath(prop.getProperty("reviewSubmitBtn"))).click();





    }


    @Test
    public void tearDown()
    {
        closeBrowser();
        extent.flush();
    }
}
