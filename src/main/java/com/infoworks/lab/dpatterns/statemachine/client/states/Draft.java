package com.infoworks.lab.dpatterns.statemachine.client.states;

import com.infoworks.lab.dpatterns.statemachine.client.machine.iDocument;
import com.infoworks.lab.dpatterns.statemachine.client.users.Role;
import com.infoworks.lab.dpatterns.statemachine.client.users.User;
import com.infoworks.lab.util.states.context.State;
import com.infoworks.lab.util.states.context.StateContext;

public class Draft implements iDocState {

    private iDocument document;
    private User user;

    @Override
    public void setContext(StateContext context) {
        this.document = (iDocument) context;
    }

    @Override
    public boolean isValidNextState(Class<? extends State> sType) {
        return Moderation.class.isAssignableFrom(sType);
    }

    @Override
    public StateContext getContext() {
        return document;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void publish() {
        if (user.getRole() == Role.AUTHOR){
            System.out.println("Draft Successfully.");
        }else {
            System.out.println("Draft Permission Denied.");
        }
    }

    @Override
    public void render() {
        if (user.getRole() == Role.AUTHOR){
            System.out.println("Draft Rendering Successfully.");
        }else {
            System.out.println("Draft Rendering Permission Denied.");
        }
    }
}
