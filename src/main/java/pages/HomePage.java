package pages;

import base.Base;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base {


    @FindBy(xpath="//a[@class='login']")
    WebElement signInLink;
    HomePage homePage;


    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public HomePage clickSignInLink()
    {
        signInLink.click();
        Graphene.guardHttp(signInLink);
        return new HomePage();
    }







}


