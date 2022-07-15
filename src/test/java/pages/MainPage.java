package pages;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;

import com.ti.framework.base.BasePage;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(className = "wpsp-preLoading")
    protected WebElement dvPreLoading;

    @FindBy(className = "wpsp-schoolname")
    private WebElement spnTI;

    @FindBy(xpath = "//*[text()=' Create New']")
    private WebElement btnCreateNew;

    @FindBy(id = "d_teacher")
    private List<WebElement> icnDelete;

    @FindBy(xpath = "//a[contains(text(),'Ok')]")
    WebElement btnOk;

    private String getSchoolName(){
        return highLight(spnTI).getText();
    }

    public MainPage verifySchoolTitle(){
        // Modified
        verification.verifyTextAreEquals(getSchoolName(),"Titanium School");
        return this;
    }
}
