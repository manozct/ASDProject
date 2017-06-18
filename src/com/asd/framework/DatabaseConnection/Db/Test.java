package com.asd.framework.DatabaseConnection.Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eessc on 6/14/2017.
 */
public class Test {

    public static void main(String[] args) {

        try {
            DbConnection.getCOnnection(DatabaseType.MySql, "localhost",
                  3306, "mts", "root", "1234");
//            Connection conn = DbConnection.dbConnectionObj.connect(DatabaseType.MySql, "localhost",
//                    3306, "mts", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection Successful");

        try {
           // ResultSet rs = DbAccess.table("customer").get();

            //ResultSet rs = DbAccess.table("customer")
                    //.select("id", "name").where("ID","1").orWhere("ID","10").get();

            ResultSet rs=DbAccess.table("customer")
                    .select("ID","name")
                    .get();

            while (rs.next()) {
                System.out.println(rs.getString(1).toString());
            }


            Map<String,String> valuess=new HashMap<>();
            valuess.put("ID", "100");
            valuess.put("name", "111");

            valuess.put("ID", "101");
            valuess.put("name", "1111");



            DbAccess.table("customer")
                    .values(valuess).insert();




//            DbAccess.table("customer")
//                    .set(valuess)
//                    .where("ID", "1")
//                    .update();
//



        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}

