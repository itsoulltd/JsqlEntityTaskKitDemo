package com.infoworks.lab;

import com.infoworks.lab.beans.tasks.definition.TaskStack;
import com.infoworks.lab.beans.tasks.nuts.SimpleTask;
import com.infoworks.lab.models.MyCustomEvent;
import com.infoworks.lab.models.Passenger;
import com.infoworks.lab.rest.models.Message;
import com.infoworks.lab.rest.models.Response;
import com.infoworks.lab.rest.models.SearchQuery;
import com.infoworks.lab.rest.models.events.Event;
import com.infoworks.lab.rest.models.events.EventType;
import com.infoworks.lab.rest.models.pagination.Pagination;
import com.infoworks.lab.rest.models.pagination.SortOrder;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class TaskDemo {

    public static void main (String...args) throws IOException {
        //Lets know about Message.java:
        //Message is derived from Entity.java
        Message message = new Message();
        message.setEvent(new Event()
                .setEventType(EventType.ACTIVATE)
                .setUuid(UUID.randomUUID().toString())
                .setTimestamp(String.valueOf(new Date().getTime())));
        System.out.println("Message was: " + message.toString());

        //Custom Event:
        Message messageC = new Message();
        messageC.setEvent(new MyCustomEvent()
                .setPassenger(new Passenger())
                .setEventType(EventType.ACTIVATE)
                .setUuid(UUID.randomUUID().toString())
                .setTimestamp(String.valueOf(new Date().getTime())));
        System.out.println("Custom Event Message was: " + messageC.toString());
        //Now recreate Message from Json:
        String remoteJson = messageC.toString();
        Message myRemoteMessage = Message.unmarshal(Message.class, remoteJson);
        System.out.println("Both Custom Message is same: " + ( myRemoteMessage.getEvent().getUuid().equals(messageC.getEvent().getUuid()) ? "YES" : "NO" ));
        //

        //Lets know about Response.java:
        //Response is derived from Message.java
        Response response = new Response().setStatus(200).setMessage("Successful Transmission");
        System.out.println("Response was: " + response.toString());

        //Lets know about PagingQuery.java & SearchQuery.java:
        SearchQuery query = Pagination.createQuery(SearchQuery.class
                , 10
                , SortOrder.ASC
                , "CLUSTER_NAME","REGION_NAME", "AM_NAME");

        query.add("ROLE_NAME").isEqualTo("Gittu")
                .or("PERSON_MOBILE").isEqualTo("01712645571")
                .and("AGE").isGreaterThen(32);

        System.out.println("Newly-Created: " + query.toString());

        //Now Assume we have a Json String: (carrying over Http Request @Body)
        String json = "{\"page\":0,\"size\":10,\"descriptors\":[{\"order\":\"ASC\",\"keys\":[\"CLUSTER_NAME\",\"REGION_NAME\",\"AM_NAME\"]}],\"properties\":[{\"key\":\"ROLE_NAME\",\"value\":\"Gittu\",\"operator\":\"EQUAL\",\"type\":\"STRING\",\"nextKey\":\"PERSON_MOBILE\",\"logic\":\"OR\"},{\"key\":\"PERSON_MOBILE\",\"value\":\"01712645571\",\"operator\":\"EQUAL\",\"type\":\"STRING\",\"nextKey\":\"AGE\",\"logic\":\"AND\"},{\"key\":\"AGE\",\"value\":\"32\",\"operator\":\"GREATER_THAN\",\"type\":\"INT\"}]}\n";
        SearchQuery recreated = Message.unmarshal(SearchQuery.class, json);
        System.out.println("Re-Created: " + recreated.toString());
        //

        //Lets know about Task.java & associate stuff:
        TaskStack stack = TaskStack.createSync(false);

        stack.push(new SimpleTask((message1) -> {
            Message message2 = new Message();
            message2.setEvent(new Event().setEventType(EventType.CREATE));
            return message2;
        }));

        stack.push(new SimpleTask((message1) -> {
            Message message2 = new Message();
            message2.setEvent(new Event().setEventType(EventType.ADD));
            return message2;
        }));

        stack.push(new SimpleTask((message1) -> {
            Message message2 = new Message();
            message2.setEvent(new Event().setEventType(EventType.DELETE));
            return message2;
        }));

        stack.commit(true, (message1, state) -> {
            System.out.println("Task Done with: " + message1.toString());
        });
    }

}
