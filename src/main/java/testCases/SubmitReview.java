package testCases;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("ratingStars"))));
        //hover over the stars
        WebElement element = driver.findElement(By.xpath(prop.getProperty("ratingStars")));

        Actions action = new Actions(driver);

        action.moveToElement(element).build().perform();

        WebElement ratings = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("ratingBar"))));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //click on five star rating
        //driver.findElement(By.xpath(prop.getProperty("fiveStar"))).click();
        Assert.assertEquals(true, driver.findElement(By.xpath("overallRating")).isDisplayed());
        Assert.assertEquals(true, driver.findElement(By.xpath("ratingLabel")).isDisplayed());
        Assert.assertEquals(true, driver.findElement(By.xpath("policyDropDown")).isDisplayed());

        //select policy type health
        driver.findElement(By.xpath(prop.getProperty("policyDropDown"))).click();
        driver.findElement(By.xpath(prop.getProperty("healthDropDownOption"))).click();

        //click on 5th star

        List<WebElement> stars=driver.findElements(By.xpath(prop.getProperty("emptyStar")));
        stars.get(4).click();

        //wait for rating label to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("ratingLabel"))));

        //click in review box
        driver.findElement(By.xpath(prop.getProperty("reviewBox"))).click();
        driver.findElement(By.xpath(prop.getProperty("reviewBox"))).sendKeys(prop.getProperty("reviewText"));
        driver.findElement(By.xpath(prop.getProperty("reviewSubmitBtn"))).click();





    }


    @Test
    public void tearDown()
    {
        closeBrowser();
        extent.flush();
    }
}
