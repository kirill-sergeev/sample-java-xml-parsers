package com.sergeev.itroi.config;

import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.parser.jaxb.JaxbMarshaller;
import com.sergeev.itroi.parser.jaxb.JaxbUnmarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import com.sergeev.itroi.util.wrapper.Wrapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RepositoryConfig {

    @Produces
    public XmlMarshaller<Wrapper<Ticket>> getBookingMarshaller() {
        return new JaxbMarshaller<>();
    }

    @Produces
    @SuppressWarnings("unchecked")
    public XmlUnmarshaller<Wrapper<Ticket>> getBookingUnmarshaller() {
        return (JaxbUnmarshaller) new JaxbUnmarshaller<>(TicketArray.class);
    }

    @Produces
    @Property("path")
    public String getPathToStorage() {
        return "out";
    }
}
