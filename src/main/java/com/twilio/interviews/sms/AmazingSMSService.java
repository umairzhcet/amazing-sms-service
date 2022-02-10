package com.twilio.interviews.sms;

import com.google.gson.Gson;
import com.twilio.interviews.sms.controllers.SendSMSController;

import static spark.Spark.post;

/**
 * The main class of the service.
 * Execute this to have a server running on port 4567.
 */
public class AmazingSMSService {

    // Used to transform the request from json
    private Gson gson = new Gson();

    private SendSMSController sendSMSController = new SendSMSController();

    public void startServer() {
        post("/send-sms/:phoneNumber", sendSMSController::handlePOSTSendSMS, gson::toJson);
    }

    public static void main(String[] args) {
        new AmazingSMSService().startServer();
    }

}

