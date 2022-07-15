package com.ti.framework.utils.data;

import static com.ti.framework.config.Constants.DATA_FOLDER;

import com.ti.framework.config.PropertyManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
  private static String jsonFile = PropertyManager.getInstance().getProperty("JSONFile");
  private static JSONArray dataObj;
  private static JSONObject contentObj;

  public static Object[][] getJSONTableArray(String userType, byte JSONAttributes)
      throws IOException, ParseException {
    JSONParser parser = new JSONParser();

    contentObj = (JSONObject) parser.parse(new FileReader(new File(DATA_FOLDER + jsonFile)));
    dataObj = (JSONArray) contentObj.get(userType);

    Object[][] arr = new String[dataObj.size()][JSONAttributes];
        for(byte i=0; i<dataObj.size();i++){
          JSONObject obj = (JSONObject) dataObj.get(i);
          arr[i][0] = String.valueOf(obj.get("user"));
          arr[i][1] = String.valueOf(obj.get("password"));
          if(JSONAttributes == 3){
            arr[i][2] = String.valueOf(obj.get("error"));
          }
        }
        return arr;
  }
}
