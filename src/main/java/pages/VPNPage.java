package pages;

import base.Base;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VPNPage extends Base {

    @FindBy(xpath="//button[contains(text(),'Connect')]")
    WebElement vpnConnectBtn;

    public VPNPage() {
        PageFactory.initElements(driver,this);
    }

    public void connectToVPN()
    {
        vpnConnectBtn.click();
        Graphene.guardAjax(vpnConnectBtn);


    }
}
