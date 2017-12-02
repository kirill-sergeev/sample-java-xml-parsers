package com.sergeev.itroi.client.service;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

import static com.sergeev.itroi.config.WsConstants.TicketService.NAME;
import static com.sergeev.itroi.config.WsConstants.TicketService.NAMESPACE;
import static com.sergeev.itroi.config.WsConstants.TicketService.PORT_NAME;

@WebServiceClient
public class TicketServiceBean extends Service {

    public TicketServiceBean(URL wsdlLocation) {
        super(wsdlLocation, new QName(NAMESPACE, NAME));
    }

    @WebEndpoint(name = PORT_NAME)
    public TicketService getTicketPort() {
        return super.getPort(new QName(NAMESPACE, PORT_NAME), TicketService.class);
    }

    @WebEndpoint(name = PORT_NAME)
    public TicketService getTicketPort(WebServiceFeature... features) {
        return super.getPort(new QName(NAMESPACE, PORT_NAME), TicketService.class, features);
    }
}
