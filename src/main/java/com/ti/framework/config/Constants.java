package com.ti.framework.config;

public class Constants {
    public static final String BASE_URL = PropertyManager.getInstance().getProperty("Url");

    //System Variables
    private static final String USER_DIR = System.getProperty("user.dir");
    public static final String RESULTS_FOLDER = USER_DIR + "/results/";
    public static final String RESOURCE_FOLDER = USER_DIR + "/src/main/resources/";

    //Results Directory
    public static final String SCREENSHOT_FOLDER = RESULTS_FOLDER + "screenshots/";
    public static final String VIDEO_FOLDER = RESULTS_FOLDER+"video/";

    //Data
    public static final String DATA_FOLDER = RESOURCE_FOLDER + "dataresources/";

    //DataBase
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String DB_HOST = PropertyManager.getInstance().getProperty("DBHost");
    public static final String DB_PORT = PropertyManager.getInstance().getProperty("DBPort");
    public static final String DB_USERNAME = PropertyManager.getInstance().getProperty("DBUserName");
    public static final String DB_PASSWORD = PropertyManager.getInstance().getProperty("DBPassword");

    public static final String DB_VALID_USERS = "ValidUsers.sql";
    public static final String DB_INVALID_USERS = "InvalidUsers.sql";

}
