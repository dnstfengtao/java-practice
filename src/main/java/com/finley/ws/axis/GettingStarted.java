package com.finley.ws.axis;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * @author Finley
 */
public class GettingStarted {

    public static void main(String[] args) throws Exception {
        String endPoint = "http://www.ebi.ac.uk/ws/services";
        Service service = new Service();

        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(endPoint);
        call.setOperationName("WSDbfetch");

        String ret = (String) call.invoke(new Object[]{"Information"});
        System.out.println(ret);
    }

}
