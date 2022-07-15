package com.ti.framework.utils.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import static com.ti.framework.config.Constants.*;

public class SQLUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static Connection getDBConnection(String dbName) throws SQLException {
        return connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s?useSSL=false", DB_HOST,DB_PORT, dbName),
                DB_USERNAME,DB_PASSWORD);
    }

    private static Statement getStatement(String dbName) throws SQLException {
        return statement = getDBConnection(dbName).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    private static String readSqlFile(String sqlFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(DATA_FOLDER + "queries/" + sqlFile));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);
        }

        return  sb.toString();
    }

    private static ResultSet getResultSet(String dbName, String sqlFile) throws SQLException, IOException {
        return getStatement(dbName).executeQuery(readSqlFile(sqlFile));
    }

    public static void closeConnection() throws SQLException {
        if (statement != null) statement.close();
        if(connection != null) connection.close();
    }

    public static Object[][] getQueryTableArray(String dbName, String sqlFile) throws SQLException, IOException {
        resultSet = getResultSet(dbName,sqlFile);
        int colCount = resultSet.getMetaData().getColumnCount();
        resultSet.last();
        int rowCount = resultSet.getRow();
        resultSet.beforeFirst();

        Object[][] arr = new Object[rowCount][colCount];
        int row = 0;

        while (resultSet.next()){
            for (int i = 0; i < colCount; i++){
                arr[row][i] = resultSet.getObject(i+1);
            }
            row ++;
        }
        return arr;
    }
}
