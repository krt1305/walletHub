package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementValidator {

    WebElement elem;
    public ElementValidator(WebElement elem) {
        this.elem=elem;
    }


    public boolean validateElemPresence(WebDriver driver,String locator)
    {
        List<WebElement> elems=driver.findElements(By.xpath(locator));

        if(elems.size()>0)
            return true;
        else
            return false;
    }
}
