package com.sergeev.itroi.service;

import com.sergeev.itroi.entity.Ticket;

import javax.jws.Oneway;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM;
import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM_ID;
import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM_LIST;
import static com.sergeev.itroi.config.WsConstants.TicketService.NAMESPACE;
import static javax.jws.soap.SOAPBinding.Style.RPC;
import static javax.jws.soap.SOAPBinding.Use.LITERAL;

@SOAPBinding(style = RPC, use = LITERAL)
@WebService(targetNamespace = NAMESPACE)
public interface TicketService {

    @Oneway
    void save(@WebParam(name = ITEM) Ticket ticket);

    @Oneway
    void remove(@WebParam(name = ITEM_ID) int id);

    @Oneway
    void saveAll(@WebParam(name = ITEM_LIST) List<Ticket> tickets);

    @WebResult(name = ITEM)
    Ticket getOne(@WebParam(name = ITEM_ID) int id);

    @WebResult(name = ITEM_LIST)
    List<Ticket> getAll();
}
