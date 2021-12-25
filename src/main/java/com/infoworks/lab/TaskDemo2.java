package com.infoworks.lab;

import com.infoworks.lab.beans.tasks.definition.TaskStack;
import com.infoworks.lab.beans.tasks.nuts.AbstractTask;
import com.infoworks.lab.rest.models.Message;
import com.infoworks.lab.rest.models.Response;
import com.it.soul.lab.sql.query.models.Property;

import java.util.Date;

public class TaskDemo2 {

    public static void main(String...args){
        //TODO: Make a Login Task Flow:
        TaskStack loginStack = TaskStack.createSync(true);
        loginStack.push(new LoginTask("james@gmail.com", "432109"));
        loginStack.commit(true, (message, state) -> {
            System.out.println("Login Status: " + state.name());
        });

        System.out.println("\n");

        //TODO: Make a Registration Task Flow:
        TaskStack regStack = TaskStack.createSync(true);
        regStack.push(new CheckUserExistTask("ahmed@yahoo.com"));
        regStack.push(new RegistrationTask("ahmed@yahoo.com"
                , "5467123879"
                , "ahmed@yahoo.com"
                , "0101991246"
                , new Date()
                , 32));
        regStack.push(new SendEmailTask("xbox-support@msn.com"
                , "ahmed@yahoo.com"
                , "new-reg-email-temp-01"));
        regStack.commit(true, (message, state) -> {
            System.out.println("Registration Status: " + state.name());
        });

        System.out.println("\n");

        //TODO: Make a ForgetPassword Task Flow:
        TaskStack forgetPassStack = TaskStack.createSync(true);
        forgetPassStack.push(new ForgotPasswordTask("ahmed@yahoo.com"));
        forgetPassStack.push(new SendEmailTask("xbox-noreply@msn.com"
                , "ahmed@yahoo.com"
                , "forgot-pass-email-temp-01"));
        forgetPassStack.commit(true, (message, state) -> {
            System.out.println("ForgetPassword Status: " + state.name());
        });

        System.out.println("\n");

        //TODO: Make a ResetPassword Task Flow:
        TaskStack resetPassStack = TaskStack.createSync(true);
        resetPassStack.push(new ResetPasswordTask("dadre-3434nndsfd-2323mkj454j5jn-llwer45"
                , "5467123879"
                , "he-he-he-funny:)"));
        resetPassStack.push(new SendEmailTask("xbox-noreply@msn.com"
                , "ahmed@yahoo.com"
                , "reset-pass-email-temp-01"));
        resetPassStack.commit(true, (message, state) -> {
            System.out.println("ResetPassword Status: " + state.name());
        });
    }

    ///////////////////////////////////Example Of Implementing a Task////////////////////////////

    public static class LoginTask extends AbstractTask<Message, Response> {

        public LoginTask() {super();}

        public LoginTask(String username, String password) {
            super(new Property("username", username), new Property("password", password));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO MAKE A LOGIN:
            String username = getPropertyValue("username").toString();
            String password = getPropertyValue("password").toString();
            //...
            String msg = String.format("%s : %s", username, password);
            System.out.println("Login is Done for " + username);
            //....
            return new Response().setMessage(msg).setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class CheckUserExistTask extends AbstractTask<Message, Response> {

        public CheckUserExistTask() {super();}

        public CheckUserExistTask(String username) {
            super(new Property("username", username));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO CHECK AN User Already Exist or Not:
            String username = getPropertyValue("username").toString();
            //...
            String msg = String.format("User exist: %s", username);
            System.out.println("Check User Exist for " + username);
            //....
            return new Response().setMessage(msg).setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class RegistrationTask extends AbstractTask<Message, Response> {

        public RegistrationTask() {super();}

        public RegistrationTask(String username, String password, String email, String contact, Date dob, Integer age) {
            super(new Property("username", username)
                    , new Property("password", password)
                    , new Property("email", email)
                    , new Property("contact", contact)
                    , new Property("dob", dob)
                    , new Property("age", age));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO MAKE A REGISTRATION:
            String username = getPropertyValue("username").toString();
            String password = getPropertyValue("password").toString();
            String email = getPropertyValue("email").toString();
            String contact = getPropertyValue("contact").toString();
            Date dob = new Date(Long.valueOf(getPropertyValue("dob").toString()));
            Integer age = Integer.valueOf(getPropertyValue("age").toString());
            //....
            System.out.println("Registration is Done for " + username);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class LogoutTask extends AbstractTask<Message, Response> {

        public LogoutTask() {super();}

        public LogoutTask(String token) {
            super(new Property("token", token));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO LOGOUT:
            String token = getPropertyValue("token").toString();
            //....
            System.out.println("Logout is Done for " + token);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class ForgotPasswordTask extends AbstractTask<Message, Response> {

        public ForgotPasswordTask() {super();}

        public ForgotPasswordTask(String email) {
            super(new Property("email", email));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO FORGOT PASSWORD:
            String email = getPropertyValue("email").toString();
            //....
            System.out.println("Forget Pass is Done for " + email);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class ResetPasswordTask extends AbstractTask<Message, Response> {

        public ResetPasswordTask() {super();}

        public ResetPasswordTask(String token, String oldPass, String newPass) {
            super(new Property("token", token), new Property("oldPass", oldPass), new Property("newPass", newPass));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO RESET PASSWORD:
            String token = getPropertyValue("token").toString();
            String oldPass = getPropertyValue("oldPass").toString();
            String newPass = getPropertyValue("newPass").toString();
            //....
            System.out.println("Reset Pass is Done for " + token);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class SendEmailTask extends AbstractTask<Message, Response> {

        public SendEmailTask() {super();}

        public SendEmailTask(String sender, String receiver, String templateId) {
            super(new Property("sender", sender), new Property("receiver", receiver), new Property("templateId",templateId));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO SEND EMAIL:
            String sender = getPropertyValue("sender").toString();
            String receiver = getPropertyValue("receiver").toString();
            String emailTemplateID = getPropertyValue("templateId").toString();
            //....
            System.out.println("Email Has Sent To " + receiver);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class SendSMSTask extends SendEmailTask {

        public SendSMSTask() {super();}

        public SendSMSTask(String sender, String receiver, String templateId) {
            super(sender, receiver, templateId);
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO SEND SMS:
            String sender = getPropertyValue("sender").toString();
            String receiver = getPropertyValue("receiver").toString();
            String smsTemplateID = getPropertyValue("templateId").toString();
            //....
            System.out.println("SMS Has Sent To " + receiver);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class SendOTPSmsTask extends SendSMSTask {

        public SendOTPSmsTask() {super();}

        public SendOTPSmsTask(String sender, String receiver, String templateId) {
            super(sender, receiver, templateId);
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            //TODO: DO THE BUSINESS LOGIC TO SEND OTP SMS:
            String sender = getPropertyValue("sender").toString();
            String receiver = getPropertyValue("receiver").toString();
            String otpTemplateID = getPropertyValue("templateId").toString();
            //....
            System.out.println("OTP Has Sent To " + receiver);
            //....
            return new Response().setMessage("").setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }

    //////////////////////////////Example of a minimal Task///////////////////////////
    public static class ExampleTask extends AbstractTask<Message, Response> {

        public ExampleTask() {super();}

        public ExampleTask(String data) {
            super(new Property("data", data));
        }

        @Override
        public Response execute(Message message) throws RuntimeException {
            String savedData = getPropertyValue("data").toString();
            //....
            //....
            return new Response().setMessage(savedData).setStatus(200);
        }

        @Override
        public Response abort(Message message) throws RuntimeException {
            String reason = message != null ? message.getPayload() : "UnknownError!";
            return new Response().setMessage(reason).setStatus(500);
        }
    }
    ///////////////////////////////////////END////////////////////////////////////////

}
