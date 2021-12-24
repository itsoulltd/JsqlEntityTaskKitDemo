package com.infoworks.lab.models;

import com.it.soul.lab.sql.SQLExecutor;
import com.it.soul.lab.sql.entity.*;
import com.it.soul.lab.sql.query.models.Property;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@TableName(value = "Passenger")
public class Passenger extends Entity {

    @PrimaryKey(name="id", auto=true)
    private Integer id = 0;
    private String name;
    private int age = 18;
    private boolean active;
    private String sex = Gender.NONE.name();
    private Date dob = new java.sql.Date(new Date().getTime());

    @Ignore
    private static int _autoIncrement = -1;

    public Passenger() {
        this.id = ++_autoIncrement;
    }

    public Passenger(String name
            , Gender sex
            , int age) {
        this();
        this.name = name;
        this.sex = sex.name();
        this.age = age;
        updateDOB(age, false);
    }

    private void updateDOB(int age, boolean isPositive) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.nonNull(getDob()) ? getDob() : new Date());
        int year = calendar.get(Calendar.YEAR) - ((isPositive) ? -age : age);
        calendar.set(Calendar.YEAR, year);
        setDob(calendar.getTime());
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = new java.sql.Date(dob.getTime());
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Property getPropertyTest(String key, SQLExecutor exe, boolean skipPrimary) {
        return getProperty(key, exe, skipPrimary);

    }

}
