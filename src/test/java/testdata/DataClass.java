package testdata;

import static com.ti.framework.config.Constants.DB_INVALID_USERS;
import static com.ti.framework.config.Constants.DB_VALID_USERS;
import static com.ti.framework.utils.data.ExcelUtils.getExcelTableArray;
import static com.ti.framework.utils.data.JSONUtils.getJSONTableArray;
import static com.ti.framework.utils.data.SQLUtils.getQueryTableArray;

import com.ti.framework.config.PropertyManager;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class DataClass {
  static Object[][] testObjArray;
  private static String excelWorkBook = PropertyManager.getInstance().getProperty("WorkBook");
  private static String excelInvalidWorkSheet = PropertyManager.getInstance().getProperty("SheetInvalidUsers");
  private static String excelValidWorkSheet = PropertyManager.getInstance().getProperty("SheetValidUsers");

  @DataProvider(name="UsersExcelData")
  public static Object[][] getDataFromExcel(Method method){
    if (method.getName().equals("loginWithRightCredentials")){
      testObjArray = getExcelTableArray(excelWorkBook, excelValidWorkSheet);
    }else if (method.getName().equals("loginWithWrongCredentials")){
      testObjArray = getExcelTableArray(excelWorkBook, excelInvalidWorkSheet);
    }
    return testObjArray;
  }

  @DataProvider(name="UsersJSONData")
  public static Object[][] getDataFromJSON(Method method) throws IOException, ParseException {
    if (method.getName().equals("loginWithRightCredentials")){
      testObjArray = getJSONTableArray("validUser", (byte)2);
    }else if (method.getName().equals("loginWithWrongCredentials")){
      testObjArray = getJSONTableArray("invalidUser", (byte)3);
    }
    return testObjArray;
  }

  @DataProvider(name="UsersSQLData")
  public static Object[][] getDataFromQuery(Method method) throws IOException, SQLException {
    if (method.getName().equals("loginWithRightCredentials")){
      testObjArray = getQueryTableArray("demo_valid", DB_VALID_USERS);
    }else if (method.getName().equals("loginWithWrongCredentials")){
      testObjArray = getQueryTableArray("demo_invalid",DB_INVALID_USERS);
    }
    return testObjArray;
  }
}
