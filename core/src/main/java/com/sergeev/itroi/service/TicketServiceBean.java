package com.sergeev.itroi.service;

import com.sergeev.itroi.config.WsConstants;
import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.repository.TicketRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(
    portName = WsConstants.TicketService.PORT_NAME,
    serviceName = WsConstants.TicketService.NAME,
    targetNamespace = WsConstants.TicketService.NAMESPACE,
    endpointInterface = "com.sergeev.itroi.service.TicketService")
public class TicketServiceBean implements TicketService {

    @Inject
    private TicketRepository repository;

    @Override
    public void save(Ticket ticket) {
        repository.save(ticket);
    }

    @Override
    public void saveAll(List<Ticket> tickets) {
        repository.saveAll(tickets);
    }

    @Override
    public void remove(int id) {
        repository.remove(id);
    }

    @Override
    public Ticket getOne(int id) {
        return repository
            .getOne(id)
            .orElseThrow(() -> new SOAPServiceException("Not Found", "Ticket with ID " + id + " not found."));
    }

    @Override
    public List<Ticket> getAll() {
        return repository.getAll();
    }
}
