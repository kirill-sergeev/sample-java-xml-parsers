package com.sergeev.itroi.service;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

public class SOAPServiceException extends SOAPFaultException {

    public SOAPServiceException(String code, String message) {
        super(generateSOAPFault(code, message));
    }

    private static SOAPFault generateSOAPFault(String code, String message) {
        try {
            return SOAPFactory.newInstance(SOAPConstants.DEFAULT_SOAP_PROTOCOL).createFault(message, new QName(code));
        } catch (SOAPException e) {
            throw new ServiceException(e);
        }
    }
}
