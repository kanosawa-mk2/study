/*
 * Copyright (c) 1997, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package fromjava.client;

import java.net.MalformedURLException;

import com.example.myservice.AddNumbersException_Exception;
import com.example.myservice.AddNumbersImpl;
import com.example.myservice.AddNumbersImplService;

import jakarta.xml.ws.BindingProvider;

public class AddNumbersClient {
    public static void main (String[] args) throws MalformedURLException {
        try {
            //AddNumbersImpl port = new AddNumbersImplService(new URL("http://43.207.104.42:8080/samplesoap/addnumbers?wsdl")).getAddNumbersImplPort();
        	AddNumbersImpl port = new AddNumbersImplService().getAddNumbersImplPort();
            log((BindingProvider)port);

            int number1 = 10;
            int number2 = 20;

            System.out.printf ("Invoking addNumbers(%d, %d)\n", number1, number2);
            int result = port.addNumbers (number1, number2);
            System.out.printf ("The result of adding %d and %d is %d.\n\n", number1, number2, result);

            number1 = -10;
            System.out.printf ("Invoking addNumbers(%d, %d)\n", number1, number2);
            result = port.addNumbers (number1, number2);
            System.out.printf ("The result of adding %d and %d is %d.\n", number1, number2, result);
        } catch (AddNumbersException_Exception ex) {
            System.out.printf ("Caught AddNumbersException_Exception: %s\n", ex.getFaultInfo ().getDetail ());
        }
    }

    private static final void log(BindingProvider port) {
        if (Boolean.getBoolean("wsmonitor")) {
            String address = (String)port.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
            address = address.replaceFirst("8080", "4040");
            port.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        }
    }
}
