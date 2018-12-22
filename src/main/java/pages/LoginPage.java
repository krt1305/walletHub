package pages;

import base.Base;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.java2d.cmm.Profile;
import util.ElementValidator;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends Base {

    @FindBy(xpath="//input[@ng-model='fields.email']")
    WebElement emailTextField;

    @FindBy(xpath="//input[@type='password']")
    WebElement passwordTextField;

    @FindBy(xpath="//button[@data-hm-tap='doLogin($event);']")
    WebElement loginSubmitBtn;

    public LoginPage() {
        PageFactory.initElements(driver,this);

    }

    public String validateLoginPageTitle()
    {
        return driver.getTitle();
    }

    public List<ElementValidator> getElementValidators()
    {
        List<ElementValidator> elems=new ArrayList();
        elems.add(new ElementValidator(emailTextField));
        elems.add(new ElementValidator(passwordTextField));
        elems.add(new ElementValidator(loginSubmitBtn));
        return elems;

    }


    public ProfilePage login()
    {
        emailTextField.sendKeys(prop.getProperty("userName"));
        passwordTextField.sendKeys(prop.getProperty("password"));
        //wait
        loginSubmitBtn.click();
       // guardHttp(buttonWhichMakesFullPageRefresh).click();
        //guardAjax(buttonWhichMakesAjaxRequest).click();
        //guardNoRequest(buttonWhichMakesNoRequest).click();
        Graphene.guardHttp(loginSubmitBtn);

        return new ProfilePage();

    }




}
