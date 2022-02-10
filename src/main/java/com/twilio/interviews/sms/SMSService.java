package com.twilio.interviews.sms;

import java.util.*;

public class SMSService {


    /**
     * Returns true of there is a node in the carrier graph that terminates that number
     * (i.e. the method CarrierNode::numberTerminatesAtThisCarrier returns true)
     */
    public boolean isSMSDeliverable(String phoneNumber) {
        if(findFinalCarrierForPhoneNumber(phoneNumber)!=null) {
        	return true;
        }
        return false;
    }

    /**
     * Returns the appropriate carrierID (@see com.twilio.interviews.sms.CarrierNode#carrierID) for the node that
     * terminates the phone number if it exists. If handle that scenario.
     */
    public String findFinalCarrierForPhoneNumber(String phoneNumber) {
        String carrier="";
        
        CarrierNode carrierNode=CarrierService.getCarrierNetworkGateway();
        Set<CarrierNode> nodes=carrierNode.getConnectedCarrierNodes(phoneNumber);
        if(nodes.isEmpty()) {
        	return null;
        }
        
        for(CarrierNode node:nodes) {
        	if(node.numberTerminatesAtThisCarrier(phoneNumber))
        	carrier=node.carrierName;
        }
        
        return carrier;
        
    }

    public void sendSMS(UUID messageID, String carrierName, String phoneNumber, String messageBody) {
        // Simulate some delay...
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + messageID + "] YAY! Message sent to " + phoneNumber + " in carrier '" + carrierName + "' at " + new Date() + " with body: " + messageBody );
    }

}
