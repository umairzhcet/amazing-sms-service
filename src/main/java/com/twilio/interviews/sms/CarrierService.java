package com.twilio.interviews.sms;

 class CarrierService {

    public CarrierService() {
        // nop
    }

    /**
     * Simulated method that returns a node in a *graph* that represents the network of carriers and their connections
     * This graph can be assumed to be immutable for the duration of time that this service is running.
     */
    public static CarrierNode getCarrierNetworkGateway() {

        // this is supposed to be a slow method.
        // Introducing a delay for the purpose of demonstration
        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            // nop
        }

        CarrierNode gateway = new CarrierNode("PartnerA");

        CarrierNode telefonica302 = new CarrierNode("Telefonica302", "302.*");
        CarrierNode verizon415123 = new CarrierNode("verizon415123", "415123.*");
        CarrierNode att415769     = new CarrierNode("att415769"    , "415769.*");
        CarrierNode tmobile5      = new CarrierNode("tmobile5"     , "5.*");
        CarrierNode tmobile500    = new CarrierNode("tmobile500"   , "500.*");
        CarrierNode tmobile510100 = new CarrierNode("tmobile510100", "510100.*");
        CarrierNode tmobile510200 = new CarrierNode("tmobile510200", "510200.*");
        CarrierNode tmobile520    = new CarrierNode("tmobile520"   , "520.*");
        CarrierNode tmobile400    = new CarrierNode("tmobile400"   , "400.*");

        CarrierNode telia2000000000 = new CarrierNode("telia2000000000", "2000000000");


        gateway.addConnectedCarrier("302.*"   , telefonica302)
               .addConnectedCarrier("415123.*", verizon415123)
               .addConnectedCarrier("5.*|4.*"  , telia2000000000)
               .addConnectedCarrier("200.*"   , tmobile5);

        tmobile5.addConnectedCarrier("500.*"   , tmobile500)
                .addConnectedCarrier("510100.*", tmobile510100)
                .addConnectedCarrier("510200.*", tmobile510200)
                .addConnectedCarrier("520.*"   , tmobile520)
                .addConnectedCarrier("400.*"   , tmobile400)
                .addConnectedCarrier("200.*"   , telia2000000000);

        telia2000000000.addConnectedCarrier("415769.*"               , att415769)
                       .addConnectedCarrier("55.*"                   , tmobile500)
                       .addConnectedCarrier("51.*|52.*|55000.*|40.*" , tmobile5);

        tmobile500.addConnectedCarrier("555.*", tmobile5);

        return gateway;
    }

}
