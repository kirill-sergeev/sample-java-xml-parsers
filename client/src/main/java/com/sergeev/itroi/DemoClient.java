package com.sergeev.itroi;

import com.sergeev.itroi.client.service.TicketService;
import com.sergeev.itroi.client.service.TicketServiceBean;
import lombok.extern.slf4j.Slf4j;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.sergeev.itroi.config.WsConstants.TicketService.WSDL_LOCATION;

@Slf4j
public class DemoClient {

    public static void main(String[] args) throws Exception {

        TicketServiceBean service = new TicketServiceBean(getWsdlLocation());
        TicketService port = service.getTicketPort();

        log.info(port.getOne(1).toString());
        log.info(port.getAll().toString());
    }

    private static synchronized URL getWsdlLocation() {
        try {
            return new URL("http://localhost:8080" + WSDL_LOCATION);
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
    }
}
