package com.infoworks.lab;

import com.infoworks.lab.models.Passenger;
import com.it.soul.lab.connect.DriverClass;
import com.it.soul.lab.connect.JDBConnection;
import com.it.soul.lab.connect.io.ScriptRunner;
import com.it.soul.lab.sql.QueryExecutor;
import com.it.soul.lab.sql.SQLExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class JSQLDemo {

    public static void main(String...args) throws SQLException {
        System.out.println("Start");
        runScripts();
        //Create a connection:
        Connection conn = new JDBConnection.Builder(DriverClass.MYSQL)
                .host("localhost", "3306")
                .database("testDB")
                .credential("root","root@123")
                .build();
        //
        //Create a SQL-Executor:
        QueryExecutor executor = new SQLExecutor(conn);
        //Create a passenger:
        Passenger passenger = new Passenger();
        passenger.setName("Towhid");
        passenger.setAge(38);
        boolean inserted = passenger.insert(executor);
        System.out.println("Inserted ? " + (inserted ? "YES" : "NO"));
    }

    public static void runScripts() throws SQLException {
        ScriptRunner runner = new ScriptRunner();
        Connection conn = new JDBConnection.Builder(DriverClass.MYSQL)
                .host("localhost", "3306")
                .database("testDB")
                .credential("root","root@123")
                .build();
        //
        File file = new File("testDB.sql");
        String[] cmds = runner.commands(runner.createStream(file));
        runner.execute(cmds, conn);
    }

}
