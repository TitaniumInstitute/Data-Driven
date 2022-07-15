package pages;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends MainPage {

    @FindBy(id = "user_login")
    private WebElement txtUser;

    @FindBy(name = "pwd")
    private WebElement txtPassword;

    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;

    @FindBy(xpath = "//input[contains(@value,'Log')]")
    private WebElement btnLogin;

    @FindBy(id = "login_error")
    private WebElement lblError;

    public LoginPage loginAs(String userName){
        highLight(txtUser).clear();
        txtUser.sendKeys(userName);
        return this;
    }

    public LoginPage withPass(String password){
        highLight(txtPassword).clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage andRememberMe(boolean checked){
        if (!chkRememberMe.isSelected() && checked){
            highLight(chkRememberMe).click();
        }
        return this;
    }

    public MainPage login(){
        highLight(btnLogin).click();
        return (MainPage) (actualPage = getInstance(MainPage.class));
    }

    private String getError(){
        return highLight(lblError).getText();
    }

    public void verifyErrorText(String error){
        verification.verifyTextAreEquals(getError(),error);
    }
}
