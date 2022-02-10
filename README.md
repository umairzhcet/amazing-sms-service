# AMAZING SMS Service

This is a toy project to be used during tech screenings/interviews. It simulates (in a very, very simplified way!) a service that should be able to send a SMS to a phone number if there is a carrier to deliver the message to.

## How to run

Execute the class `com.twilio.interviews.sms.AmazingSMSService` OR execute `./mvnw clean compile exec:java`

This should start a webservice listening on port `4567`. There is one endpoint in this application: `[POST] /send-sms`

## Carrier Graph

The carrier graph represents the carriers, their inter connections and which numbers they can handle.
This is a visual representation of the graph.

![Carrier Graph](/docs/carrier-graph.png)

## Objective

The objective of this exercise is to implement the following 3 methods:

* com.twilio.interviews.sms.CarrierNode.getConnectedCarrierNodes
* com.twilio.interviews.sms.SMSService.isSMSDeliverable
* com.twilio.interviews.sms.SMSService.findFinalCarrierForPhoneNumber


(All the methods are mared with a `// TODO: Implement me!`)

## Example curl

```
curl -v \
     http://localhost:4567/send-sms/4157696332 \
     --data '{"messageBody":"Hello Twilio!"}'
```

## Expected results:

Phone Number | Success  | Carrier
------------ | -------- | ----------
5555550000   | yes      | tmobile5
4157696332   | yes      | att415769
4001231234   | yes      | tmobile400
4101231234   | no       | -----
1234567890   | no       | -----
2000000000   | yes      | telia2000000000

## Links

[Java 8 API](https://docs.oracle.com/javase/8/docs/api/)

[Java Regular expression Patterns](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)

[Spark framework](http://sparkjava.com)