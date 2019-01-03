/*
 * MysqlModel handles the method which connect to Azure Mysql database and execute the query
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Wendy Chiang
 */
public class MysqlModel {

    // Initialize connection variables.
    private String host = "mysqlwendytest.mysql.database.azure.com";
    private String database = "twosixtransaction";
    private String user = "pingjoutest@mysqlwendytest";
    private String password = "Itisatest123";
    private Connection connection = null;

    //constructor of MysqlModel
    public MysqlModel() {
        connectDB();
    }

    /**
     * Connect to DB
     */
    private String connectDB() {
        String reply = "";

        // check that the driver is installed
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver NOT detected in library path." + e);
            reply = "sorry class not found";
            return reply;
        }

        System.out.println("MySQL JDBC driver detected in library path.");

        // Initialize connection object
        try {
            String url = String.format("jdbc:mysql://%s:3306/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);


            // get connection
            connection = DriverManager.getConnection(url, properties);

        } catch (SQLException e) {
            System.out.println("Failed to create connection to database" + e);
            reply = e.toString();
        }
        return reply;

    }

    /**
     * This method will call createJSON to run query to get the information of
     * revenue
     *
     * @return a JSON string of revenue
     */
    public String getRevenue() {
        connectDB();
        String queryString = "";
        String jsonString = "";
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            // Perform SQL queries over the connection.
            try {
                ResultSet results = null;
                Statement statement = connection.createStatement();

                //run four queries in four database
                for (int i = 2013; i < 2018; i++) {
                    switch (i) {
                        case (2013):
                            queryString = "SELECT sum(sales_amount) from transactions_2013;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                three = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2014):
                            queryString = "SELECT sum(sales_amount) from transactions_2014;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                four = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2015):
                            queryString = "SELECT sum(sales_amount) from transactions_2015;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                five = Double.valueOf(results.getString(1));
                            }
                            break;
                            
                        case (2016):
                            queryString = "SELECT sum(sales_amount) from transactions_2016;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                six = Double.valueOf(results.getString(1));
                            }
                            break;

                    }

                }
                //create JSON
                jsonString = createJSON("revenue", three, four, five, six);

            } catch (SQLException e) {
                System.out.println("Encountered an error when executing given sql statement" + e);
                jsonString = e.toString();
            }
        } else {
            System.out.println("Failed to create connection to database.");
            jsonString = "Failed to create connection to database.";

        }

        return jsonString;
    }

    /**
     * This method will call createJSON to run query to get the information of
     * user count
     *
     * @return a JSON string of revenue
     */
    public String getActiveuser() {
        String queryString = "";
        String jsonString = "";
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            // Perform SQL queries over the connection.
            try {
                ResultSet results = null;
                Statement statement = connection.createStatement();
                for (int i = 2013; i < 2018; i++) {
                    switch (i) {
                        case (2013):
                            queryString = "SELECT count(distinct user) from transactions_2013;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                three = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2014):
                            queryString = "SELECT count(distinct user) from transactions_2014;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                four = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2015):
                            queryString = "SELECT count(distinct user) from transactions_2015;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                five = Double.valueOf(results.getString(1));
                            }
                            break;
                            
                        case (2016):
                            queryString = "SELECT count(distinct user) from transactions_2016;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                six = Double.valueOf(results.getString(1));
                            }
                            break;

                        
                    }

                }
                //create JSON
                jsonString = createJSON("activeusers", three, four, five, six);

            } catch (SQLException e) {
                System.out.println("Encountered an error when executing given sql statement" + e);
                jsonString = e.toString();
            }

        } else {
            System.out.println("Failed to create connection to database.");
            jsonString = "Failed to create connection to database.";
        }

        return jsonString;
    }

    /**
     * This method will call createJSON to run query to get the information of
     * new user count
     *
     * @return a JSON string of revenue
     */
    public String getNewuser() {
        String queryString = "";
        String jsonString = "";
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            // Perform SQL queries over the connection.
            try {
                ResultSet results = null;
                Statement statement = connection.createStatement();
                for (int i = 2013; i < 2018; i++) {
                    switch (i) {
                        case (2013):
                            queryString = "select count(distinct user) from transactions_2013\n"
                                    + "where join_date like \"%2013\";";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                three = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2014):
                            queryString = "select count(distinct user) from transactions_2014\n"
                                    + "where join_date like \"%2014\";";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                four = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2015):
                            queryString = "select count(distinct user) from transactions_2014\n" +
                                          "where join_date like \"%2015\";";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                five = Double.valueOf(results.getString(1));
                            }
                            break;
                            
                        case (2016):
                            queryString = "select count(distinct user) from transactions_2016\n" +
                                          "where join_date like \"%2016\";";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                six = Double.valueOf(results.getString(1));
                            }
                            break;

                    }

                }
                jsonString = createJSON("newusercount", three, four, five, six);

            } catch (SQLException e) {
                System.out.println("Encountered an error when executing given sql statement" + e);
                jsonString = e.toString();
            }
        } else {
            System.out.println("Failed to create connection to database.");
            jsonString = "Failed to create connection to database.";
        }

        return jsonString;
    }

    /**
     * This method will call createJSON to run query to get the information of
     * user count
     *
     * @return a JSON string of revenue
     */
    public String getAvgRevenue() {
        String queryString = "";
        String jsonString = "";
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        if (connection != null) {
            System.out.println("Successfully created connection to database.");

            // Perform SQL queries over the connection.
            try {
                ResultSet results = null;
                Statement statement = connection.createStatement();
                for (int i = 2013; i < 2018; i++) {
                    switch (i) {
                        case (2013):
                            queryString = "SELECT sum(sales_amount)/count(distinct user) from transactions_2013;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                three = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2014):
                            queryString = "SELECT sum(sales_amount)/count(distinct user) from transactions_2014;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                four = Double.valueOf(results.getString(1));
                            }
                            break;
                        case (2015):
                            queryString = "SELECT sum(sales_amount)/count(distinct user) from transactions_2015;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                five = Double.valueOf(results.getString(1));
                            }
                            break;
                            
                        case (2016):
                            queryString = "SELECT sum(sales_amount)/count(distinct user) from transactions_2016;";
                            results = statement.executeQuery(queryString);
                            while (results.next()) {
                                System.out.println(results.getString(1));
                                six = Double.valueOf(results.getString(1));
                            }
                            break;

                    }

                }
                //create JSON
                jsonString = createJSON("arpau", three, four, five, six);

            } catch (SQLException e) {
                System.out.println("Encountered an error when executing given sql statement" + e);
                jsonString = e.toString();
            }
        } else {
            System.out.println("Failed to create connection to database.");
            jsonString = "Failed to create connection to database.";
        }

        return jsonString;
    }

    /**
     *
     * @param view the view that user input
     * @param three value for 2013
     * @param four value for 2014
     * @param five value for 2015
     * @param six value for 2016
     * @return a JSON format string which will be shown no the browser
     */
    private String createJSON(String view, double three, double four, double five, double six) {

        return ("{ \"" + view + "\" : { \"2013\": " + String.format("%.2f", three) + ", \"2014\": " + String.format("%.2f", four) 
                + ", \"2015\": " + String.format("%.2f", five) + ", \"2016\": " + String.format("%.2f", six) + "} }");
    }

    //main is just for testing
    public static void main(String[] args) {

        MysqlModel model = new MysqlModel();

        System.out.println(model.getRevenue());
        System.out.println(model.createJSON("newusercount", 123, 456, 566, 666));
        float test = 2.555555f;
        System.out.println(String.format("%.2f", test));

    }

}
