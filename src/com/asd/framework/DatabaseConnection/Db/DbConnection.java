package com.asd.framework.DatabaseConnection.Db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by eessca on 6/12/2017.
 */
public class DbConnection {
    static DbConnection dbConnectionObj = null;
    public Connection connectionObj=null;
    private DbConnection(){

    }
    public static DbConnection getCOnnection() {
        if (dbConnectionObj == null) {
            dbConnectionObj = new DbConnection();
        }
        return dbConnectionObj;
    }


    public Connection connect(DatabaseType databaseType,String host, int portNum,String database,
                              String userName,String password) throws Exception {
        try {
            if (databaseType.equals(DatabaseType.MySql)) {
                connectionObj = getMySqlConnection(host, portNum, database, userName, password);
            } else if (databaseType.equals(DatabaseType.MsSQL)) {
                connectionObj = getMsSqlConnection(host, portNum, database, userName, password);
            } else if (databaseType.equals(DatabaseType.PostgresSql)) {
                connectionObj = getPostGresConnection(host, portNum, database, userName, password);
            } else if (databaseType.equals(DatabaseType.ORACLE)) {
                connectionObj = getOracleConnection(host, portNum, database, userName, password);
            }
            return connectionObj;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public  Connection getMySqlConnection(String host, int portNum,String database,
                                          String userName,String password) throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        connectionProps.put("ssl","true");
        Class.forName("org.gjt.mm.mysql.Driver"); // load MySQL driver
        return DriverManager.getConnection( "jdbc:mysql://" + host + ":"
                + portNum + "/" + database, connectionProps);


//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            return DriverManager.getConnection(
//                    "jdbc:mysql://" +
//                            host +
//                            ":" + portNum + "/" + database,
//                    connectionProps);
//        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    public  Connection getMsSqlConnection(String host, int portNum,String database,
                                          String userName,String password) throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        Class.forName("com.jnetdirect.jsql.JSQLDriver" ); // load MsSQL driver
        return DriverManager.getConnection( "jdbc:JSQLConnect://" + host + ":"
                + portNum  + "//"+ database, connectionProps);


    }

    public  Connection getPostGresConnection(String host, int portNum,String database,
                                           String userName,String password) throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        connectionProps.put("ssl","true");
        Class.forName("oracle.jdbc.driver.OracleDriver"); // load PostGres driver
        return DriverManager.getConnection("jdbc:postgresql://" + host + ":"
                + portNum  + "//"+ database, connectionProps);
    }
    public  Connection getOracleConnection(String host, int portNum,String database,
                                           String userName,String password) throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        Class.forName("oracle.jdbc.driver.OracleDriver"); // load Oracle driver
        return DriverManager.getConnection("jdbc:oracle:thin:" + host + ":"
                + portNum  + "//"+ database, connectionProps);
    }



}
