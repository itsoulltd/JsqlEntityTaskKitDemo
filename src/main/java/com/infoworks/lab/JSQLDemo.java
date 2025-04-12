package com.infoworks.lab;

import com.infoworks.lab.models.Passenger;
import com.it.soul.lab.connect.DriverClass;
import com.it.soul.lab.sql.QueryExecutor;
import com.it.soul.lab.sql.SQLExecutor;

import java.sql.Connection;

public class JSQLDemo {

    public static void main(String...args) throws Exception {
        System.out.println("Start");
        JSQLController.executeScripts(DriverClass.H2_EMBEDDED);
        //Create a connection:
        Connection conn = JSQLController.createConnection(DriverClass.H2_EMBEDDED);
        //
        //Create a SQL-Executor:
        QueryExecutor executor = new SQLExecutor(conn);
        //Create a passenger:
        Passenger passenger = new Passenger();
        passenger.setName("Towhid");
        passenger.setAge(38);
        boolean inserted = passenger.insert(executor);
        //
        System.out.println("Inserted ? " + (inserted ? "YES" : "NO"));
        executor.close();
    }

}
