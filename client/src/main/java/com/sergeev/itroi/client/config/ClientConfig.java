package com.sergeev.itroi.client.config;

import com.sergeev.itroi.client.service.TicketService;
import com.sergeev.itroi.client.service.TicketServiceBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.management.JMException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.URL;

import static com.sergeev.itroi.config.WsConstants.TicketService.WSDL_LOCATION;

@ApplicationScoped
public class ClientConfig {

    private static String getServerProperty(String obj, String key) throws JMException {
        return ManagementFactory.getPlatformMBeanServer().getAttribute(new ObjectName(obj), key).toString();
    }

    @Produces
    public TicketService getTicketService() {
        try {
            return new TicketServiceBean(new URL("http://" +
                getServerProperty("jboss.as:interface=public", "inet-address") + ":" +
                getServerProperty("jboss.as:socket-binding-group=standard-sockets,socket-binding=http", "port") +
                WSDL_LOCATION)).getTicketPort();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
