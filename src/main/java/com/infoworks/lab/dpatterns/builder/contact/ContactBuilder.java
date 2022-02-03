package com.infoworks.lab.dpatterns.builder.contact;

public class ContactBuilder {

    public interface Build {
        Contact build();
    }

    public interface Name extends Build {
        Name firstName(String fname);
        Address lastName(String lname);
    }

    public interface Address extends Build {
        Address email(String email);
        Address home(String home);
        Build permanent(String permanent);
    }

    public static class Builder implements Build, Name, Address {

        private final Contact _contact;

        public Builder() {
            _contact = new Contact();
        }

        @Override
        public Contact build() {
            return _contact;
        }

        @Override
        public Name firstName(String fname) {
            _contact.setFirstName(fname);
            return this;
        }

        @Override
        public Address lastName(String lname) {
            _contact.setLastName(lname);
            return this;
        }

        @Override
        public Address email(String email) {
            _contact.setEmail(email);
            return this;
        }

        @Override
        public Address home(String home) {
            _contact.setHome(home);
            return this;
        }

        @Override
        public Build permanent(String permanent) {
            _contact.setPermanent(permanent);
            return this;
        }
    }

}
