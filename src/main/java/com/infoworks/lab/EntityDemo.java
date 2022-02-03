package com.infoworks.lab;

import com.infoworks.lab.models.Gender;
import com.infoworks.lab.models.Passenger;
import com.it.soul.lab.connect.DriverClass;
import com.it.soul.lab.sql.QueryExecutor;
import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.query.models.Where;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.infoworks.lab.JSQLDemo.createExecutor;

public class EntityDemo {

    public static void main (String...args) throws Exception {
        //
        JSQLDemo.runScripts(DriverClass.H2_EMBEDDED);
        //Lets know about Entity.java an abstract class to work with:

        //...
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

        //...
        //Feature 2: light-weight ORM (deeply rely on JDBC-Driver)
        QueryExecutor executor = createExecutor(DriverClass.H2_EMBEDDED);
        Passenger myPass = new Passenger();
        myPass.setName("Wares Ahmed");
        myPass.setActive(true);
        myPass.setSex(Gender.MALE.name());
        //Insert into DB:
        myPass.insert(executor);
        //Find from DB:
        List<Passenger> res = Entity.read(Passenger.class
                                            , executor
                                            , new Where("name").isLike("Wares%"));
        //print result:
        res.stream()
                .forEach(passenger -> {
                    //printing Passenger Name:
                    System.out.println(passenger.getName());
                });
        //
    }

}
