package com.infoworks.lab.dpatterns.statemachine;

import com.infoworks.lab.dpatterns.statemachine.client.machine.Document;
import com.infoworks.lab.dpatterns.statemachine.client.states.Draft;
import com.infoworks.lab.dpatterns.statemachine.client.states.Moderation;
import com.infoworks.lab.dpatterns.statemachine.client.states.Published;
import com.infoworks.lab.dpatterns.statemachine.client.users.Role;
import com.infoworks.lab.dpatterns.statemachine.client.users.User;

public class StateMachineApp {

    public static void main (String[] args){
        //Both way its same: but mix mode did not implemented:
        Document docs = new Document(Draft.class, Moderation.class, Published.class);
        //Document docs = new Document(new Moderation(), new Draft(), new Published());
        //
        System.out.println("---------Draft & AUTHOR---------");
        docs.enter(Draft.class);
        //OR
        //docs.moveNext();
        docs.setUserToCurrentState(new User(Role.AUTHOR));
        docs.render();
        docs.publish();
        //
        System.out.println("---------Moderation & MODERATOR---------");
        docs.enter(Moderation.class);
        //OR
        //docs.moveNext();
        docs.setUserToCurrentState(new User(Role.MODERATOR));
        docs.render();
        docs.publish();
        //
        System.out.println("---------Published & PUBLISHER---------");
        docs.enter(Published.class);
        //OR
        //docs.moveNext();
        docs.setUserToCurrentState(new User(Role.PUBLISHER));
        docs.render();
        docs.publish();
        //
        System.out.println("---------Draft & MODERATOR---------");
        docs.enter(Draft.class);
        //OR
        //docs.moveNext();
        docs.setUserToCurrentState(new User(Role.MODERATOR));
        docs.render();
        docs.publish();
    }

}
