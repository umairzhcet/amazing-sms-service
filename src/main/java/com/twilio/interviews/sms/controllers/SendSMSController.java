package com.twilio.interviews.sms.controllers;

import com.google.gson.Gson;
import com.twilio.interviews.sms.SMSService;
import com.twilio.interviews.sms.models.SendSMSRequest;
import com.twilio.interviews.sms.models.SendSMSResponse;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class SendSMSController {

    // Used to transform the result in json
    private Gson gson = new Gson();

    private SMSService smsService = new SMSService();

    public SendSMSResponse handlePOSTSendSMS(Request request, Response response) throws Exception {
        SendSMSRequest sendSMSRequest = gson.fromJson(request.body(), SendSMSRequest.class);

        String toPhoneNumber = request.params(":phoneNumber");

        UUID messageID = UUID.randomUUID();

        if (smsService.isSMSDeliverable(toPhoneNumber)) {
            String carrierName = smsService.findFinalCarrierForPhoneNumber(toPhoneNumber);
            smsService.sendSMS(messageID, carrierName, toPhoneNumber, sendSMSRequest.getMessageBody());

            response.status(500);

            return new SendSMSResponse(messageID, "Message sent successfully");
        } else {

            response.status(202);

            return new SendSMSResponse(messageID, "The message can not be sent because there is no carrier for it!");
        }
    }
}
