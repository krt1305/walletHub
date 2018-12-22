package pages;

import base.Base;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VPNLoginPage extends Base {

    @FindBy(xpath="//a[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy(xpath="//input[@id='userName']")
    WebElement userNameField;

    @FindBy(xpath="//input[@id='userPass']")
    WebElement passwordField;

    @FindBy(xpath="//button[@id='btnLogin']")
    WebElement vpnloginBtn;

    @FindBy(xpath="//div[@id='loaderOverlay']")
    WebElement loginProgress;


    public VPNLoginPage() {
        PageFactory.initElements(driver,this);
    }



    public VPNPage loginToVPN()
    {
        loginBtn.click();
        Graphene.guardAjax(loginBtn);
        userNameField.sendKeys("purevpn0m7496014");
        passwordField.sendKeys("Password@1305!");
        vpnloginBtn.click();
        Graphene.guardHttp(vpnloginBtn);
        return new VPNPage();

    }
}
