package com.twilio.interviews.sms.models;

import java.util.UUID;

public class SendSMSResponse {

    private UUID messageID;
    private String status;

    public SendSMSResponse(UUID messageID, String status) {
        this.messageID = messageID;
        this.status = status;
    }

    public UUID getMessageID() {
        return messageID;
    }

    public void setMessageID(UUID messageID) {
        this.messageID = messageID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
