package utils;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

import static utils.ConfigProperties.getConfigProperty;

public abstract class MySQLConnector {

    //private static final String URL = "jdbc:mysql://localhost:3306/facts";
    //private static final String USERNAME = "root";
   // private static final String PASSWORD = "19876245";

    private static void runSql() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Query execution didn't succeed");
        }

    }

    public static String returnOneRow(String sqlQuery) {
        runSql();
        ArrayList<String> selectFirstRow;
        try {
            Connection connection = DriverManager.getConnection(getConfigProperty("mysql.jdbcString"),
                    getConfigProperty("mysql.username"), getConfigProperty("mysql.password"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            selectFirstRow = new ArrayList<String>();
            while (resultSet.next()) {
                selectFirstRow.add(resultSet.getString("fact"));
            }
        } catch (SQLException e) {
            System.out.println("Error in mySQL query!");
            e.printStackTrace();
            return null;
        }
        return selectFirstRow.get(5);

    }


    public static void executeQueries(ArrayList<String> interestingFacts) {
        runSql();
        try {
            Connection connection = DriverManager.getConnection(getConfigProperty("mysql.jdbcString"),
                    getConfigProperty("mysql.username"), getConfigProperty("mysql.password"));
            Statement statement = connection.createStatement();
            for (String interestingFact : interestingFacts) {
                String text = "INSERT INTO interesting VALUES(NULL," + "\"" + interestingFact + "\");";
                statement.execute(text);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void clearTheTable(String fullTableName)throws  SQLException{
        runSql();

        Connection connection = DriverManager.getConnection(getConfigProperty("mysql.jdbcString"),
                    getConfigProperty("mysql.username"), getConfigProperty("mysql.password"));

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM "+ fullTableName + ";");
    }

    public static ArrayList<Object> getResultSet(String sqlQuery) throws SQLException {
        runSql();

        Connection connection = DriverManager.getConnection(getConfigProperty("mysql.jdbcString"),
                getConfigProperty("mysql.username"), getConfigProperty("mysql.password"));

        ArrayList<Object> sms = new ArrayList<Object>();
        try {
            ResultSet sqlColumn = connection.createStatement().executeQuery(sqlQuery);

            while (sqlColumn.next()) {
                sms.add(sqlColumn.getObject(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object sm : sms) {
            System.out.println(sm.toString());
        }
        return sms;
    }
}