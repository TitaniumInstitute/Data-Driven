package test;

import static com.ti.framework.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import testdata.DataClass;

public class TestClass extends Base {

    @Test(dataProvider = "UsersSQLData", dataProviderClass = DataClass.class, priority = 1)
    void loginWithWrongCredentials(int id, String user, String pass, String error, Method method){
        startTest(method.getName(),String.format("Getting error: %s, using user: %s and pass: %s",error, user, pass));

        actualPage = getInstance(LoginPage.class)
                .loginAs(user)
                .withPass(pass)
                .andRememberMe(true)
                .login();

        actualPage = getInstance(LoginPage.class);
        actualPage.as(LoginPage.class).verifyErrorText(error);
    }

    @Test(dataProvider = "UsersSQLData", dataProviderClass = DataClass.class, priority = 2)
    void loginWithRightCredentials(int id, String user, String pass, Method method){
        startTest(method.getName(),String.format("Login Using Right Credentials, user: %s and pass: %s", user, pass));

        actualPage = getInstance(LoginPage.class)
                .loginAs(user)
                .withPass(pass)
                .andRememberMe(true)
                .login();

        actualPage.as(MainPage.class).verifySchoolTitle();
    }
}
