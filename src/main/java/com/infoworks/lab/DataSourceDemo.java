package com.infoworks.lab;

import com.infoworks.lab.models.Person;
import com.it.soul.lab.data.simple.SimpleDataSource;
import org.junit.Test;

public class DataSourceDemo {

    public static void main(String[] args) {
        //TODO:
        DataSourceDemo demo = new DataSourceDemo();
        demo.readTest();
    }

    private SimpleDataSource<String, Object> dataSource;
    private SimpleDataSource<Integer, Object> intDataSource;

    private void loadDataSource(){
        dataSource = new SimpleDataSource<>();

        dataSource.put("p-1", new Person()
                .setName("John")
                .setEmail("john@gmail.com")
                .setAge(36)
                .setGender("male"));

        dataSource.put("p-2", new Person()
                .setName("Eve")
                .setEmail("eve@gmail.com")
                .setAge(21)
                .setGender("female"));

        dataSource.put("p-3", new Person()
                .setName("Mosses")
                .setEmail("mosses@gmail.com")
                .setAge(31)
                .setGender("male"));

        dataSource.put("p-4", new Person()
                .setName("Abraham")
                .setEmail("abraham@gmail.com")
                .setAge(31)
                .setGender("male"));

        dataSource.put("p-5", new Person()
                .setName("Ahmed")
                .setEmail("ahmed@gmail.com")
                .setAge(31)
                .setGender("male"));

        dataSource.put("p-6", new Person()
                .setName("Adam")
                .setEmail("adam@gmail.com")
                .setAge(31)
                .setGender("male"));
    }

    private void loadIntDataSource(){
        intDataSource = new SimpleDataSource<>();

        intDataSource.add(new Person()
                .setName("John")
                .setEmail("john@gmail.com")
                .setAge(36)
                .setGender("male"));

        intDataSource.add(new Person()
                .setName("Eve")
                .setEmail("eve@gmail.com")
                .setAge(21)
                .setGender("female"));

        intDataSource.add(new Person()
                .setName("Mosses")
                .setEmail("mosses@gmail.com")
                .setAge(31)
                .setGender("male"));

        intDataSource.add(new Person()
                .setName("Abraham")
                .setEmail("abraham@gmail.com")
                .setAge(31)
                .setGender("male"));

        intDataSource.add(new Person()
                .setName("Ahmed")
                .setEmail("ahmed@gmail.com")
                .setAge(31)
                .setGender("male"));

        intDataSource.add(new Person()
                .setName("Adam")
                .setEmail("adam@gmail.com")
                .setAge(31)
                .setGender("male"));
    }

    @Test
    public void readTest() {
        PLogger LOG = new PLogger();
        //
        loadDataSource();
        LOG.printMillis("");
        //
        System.out.println("===========================0-(datasource.size())======================");
        int maxItem = dataSource.size();
        Object[] readAll = dataSource.readSync(0, maxItem);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================1-2==========================");
        readAll = dataSource.readSync(1, 2);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================2-3=========================");
        readAll = dataSource.readSync(2, 3);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================0-3=========================");
        readAll = dataSource.readSync(0, 3);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================0-2========================");
        readAll = dataSource.readSync(0, 2);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("==========================100-10=======================");
        readAll = dataSource.readSync(100, 10);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("==========================0-0=======================");
        readAll = dataSource.readSync(0, 0);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("==========================1-0=======================");
        readAll = dataSource.readSync(1, 0);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("==========================(Asynch)===================");
        dataSource.readAsync(4, 1, (items) -> {
            System.out.println("==========================4-1=======================");
            for (Object p : items) {
                System.out.println(p.toString());
            }
        });
        LOG.printMillis("");
        dataSource.readAsync(4, 2, (items) -> {
            System.out.println("==========================4-2=======================");
            for (Object p : items) {
                System.out.println(p.toString());
            }
        });
        LOG.printMillis("");
        dataSource.readAsync(4, 3, (items) -> {
            System.out.println("==========================4-3=======================");
            for (Object p : items) {
                System.out.println(p.toString());
            }
        });
    }

    @Test
    public void addTest() {
        PLogger LOG = new PLogger();
        //
        loadIntDataSource();
        System.out.println(intDataSource.size());
        LOG.printMillis("");
        //
        System.out.println("===========================0-(datasource.size())======================");
        int maxItem = intDataSource.size();
        Object[] readAll = intDataSource.readSync(0, maxItem);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================1-2==========================");
        readAll = intDataSource.readSync(1, 2);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================2-3=========================");
        readAll = intDataSource.readSync(2, 3);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
        LOG.printMillis("");
        System.out.println("===========================0-3=========================");
        readAll = intDataSource.readSync(0, 3);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void additionFuncTest() {
        PLogger LOG = new PLogger();
        ////When String is Key:
        SimpleDataSource<String, Person> dataSource = new SimpleDataSource<>();

        dataSource.put("p-1", new Person()
                .setName("John")
                .setEmail("john@gmail.com")
                .setAge(36)
                .setGender("male"));

        dataSource.add(new Person()
                .setName("Abraham")
                .setEmail("Abraham@gmail.com")
                .setAge(45)
                .setGender("male"));

        LOG.printMillis("");
        System.out.println("===========================0-2==========================");
        Object[] readAll = dataSource.readSync(0, 2);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }

        ////When Integer is Key:
        SimpleDataSource<Integer, Person> intDataSource = new SimpleDataSource<>();

        intDataSource.put(0, new Person()
                .setName("John")
                .setEmail("john@gmail.com")
                .setAge(36)
                .setGender("male"));

        Integer intKey = intDataSource.add(new Person()
                .setName("Abraham")
                .setEmail("Abraham@gmail.com")
                .setAge(45)
                .setGender("male"));
        LOG.printMillis("");
        System.out.println("===========================1-1==========================");
        Person person = intDataSource.read(intKey);
        System.out.println(person.toString());
        LOG.printMillis("");
        System.out.println("");
    }

    @Test
    public void addDeleteTests() {
        PLogger LOG = new PLogger();
        SimpleDataSource<String, Person> dataSource = new SimpleDataSource<>();

        Person a = new Person()
                .setName("John")
                .setEmail("john@gmail.com")
                .setAge(36)
                .setGender("male");

        Person b = new Person()
                .setName("Abraham")
                .setEmail("Abraham@gmail.com")
                .setAge(45)
                .setGender("male");

        if(!dataSource.contains(a)) dataSource.add(a);
        if(!dataSource.contains(b)) dataSource.add(b);
        LOG.printMillis("");
        System.out.println("===========================0-2==========================");
        Object[] readAll = dataSource.readSync(0, 2);
        for (Object p : readAll) {
            System.out.println(p.toString());
        }

        System.out.println("Size before delete: " + dataSource.size());
        if(dataSource.contains(a)) dataSource.delete(a);
        System.out.println("Size after delete: " + dataSource.size());
        LOG.printMillis("");
    }

}