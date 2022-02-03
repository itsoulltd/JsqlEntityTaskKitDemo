package com.infoworks.lab.dpatterns.builder;

import com.infoworks.lab.dpatterns.builder.contact.Contact;

public class ContactBookApp {
    public static void main(String...arg){

        Contact contact = Contact.builder().build();
        System.out.println(contact.toString());

        Contact withFullName = Contact.builder()
                .firstName("Soha")
                .lastName("khan")
                .build();
        System.out.println(withFullName.toString());

        Contact withEmailAndPermanent = Contact.builder()
                .firstName("Soha")
                .lastName("khan")
                .email("skhan@gmail.com")
                .permanent("29, Delhi Darbar, India")
                .build();
        System.out.println(withEmailAndPermanent.toString());
    }
}
