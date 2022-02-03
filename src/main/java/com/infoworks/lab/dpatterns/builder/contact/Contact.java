package com.infoworks.lab.dpatterns.builder.contact;

public class Contact {

    public static ContactBuilder.Builder builder(){
        return new ContactBuilder.Builder();
    }

    private String firstName;
    private String lastName;
    private String email;
    private String home;
    private String permanent;

    public Contact() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPermanent() {
        return permanent;
    }

    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", home='" + home + '\'' +
                ", permanent='" + permanent + '\'' +
                '}';
    }

}
