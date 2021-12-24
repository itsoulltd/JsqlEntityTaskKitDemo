package com.infoworks.lab;

import com.infoworks.lab.models.Gender;
import com.infoworks.lab.models.Passenger;
import com.it.soul.lab.connect.DriverClass;
import com.it.soul.lab.connect.JDBConnection;
import com.it.soul.lab.sql.QueryExecutor;
import com.it.soul.lab.sql.SQLExecutor;
import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.query.models.Where;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityDemo {

    public static void main (String...args) throws Exception {
        //
        JSQLDemo.runScripts();

        //Lets know about Entity.java an abstract class to work with:
        //Feature 1: free serialization & deserialization
        Passenger passengerA = new Passenger();
        passengerA.setName("Masum");
        passengerA.setActive(true);
        //Marshaling passenger into map:
        Map<String, Object> passDataA = passengerA.marshallingToMap(true);
        System.out.println("Serialize Passenger into Map: " + passDataA);
        //....
        //Reverse the previous action:
        Map<String, Object> passDataB = new HashMap<>();
        passDataB.put("name", "Tareq");
        passDataB.put("active", "false");
        passDataB.put("sex", "MALE");
        //Unmarshal passenger from Map:
        Passenger passengerB = new Passenger();
        passengerB.unmarshallingFromMap(passDataB, true);
        System.out.println("Deserialize Passenger from Map: " + passengerB.toString());

        //Feature 2: light-weight ORM (deeply rely on JDBC-Driver)
        QueryExecutor executor = createExecutor();
        Passenger myPass = new Passenger();
        myPass.setName("Wares Ahmed");
        myPass.setActive(true);
        myPass.setSex(Gender.MALE.name());
        myPass.insert(executor); //Do the insert
        //Retrieve from DB:
        List<Passenger> res = Entity.read(Passenger.class
                , executor
                , new Where("name").isLike("Wares"));
        //print
        res.stream().forEach(passenger -> System.out.println(passenger.getName()));
        //
    }

    private static QueryExecutor createExecutor() throws SQLException {
        Connection conn = new JDBConnection.Builder(DriverClass.MYSQL)
                .host("localhost", "3306")
                .database("testDB")
                .credential("root","root@123")
                .build();
        //Create a SQL-Executor:
        QueryExecutor executor = new SQLExecutor(conn);
        return executor;
    }

}
