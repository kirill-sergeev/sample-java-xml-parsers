package com.sergeev.itroi.client.service;

import com.sergeev.itroi.client.entity.TicketArray;
import com.sergeev.itroi.entity.Ticket;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM;
import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM_ID;
import static com.sergeev.itroi.config.WsConstants.TicketService.ITEM_LIST;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TicketService {

    @WebMethod
    @Oneway
    void saveAll(@WebParam(name = ITEM_LIST) TicketArray tickets);

    @WebMethod
    @Oneway
    void remove(@WebParam(name = ITEM_ID) int id);

    @WebMethod
    @WebResult(name = ITEM)
    Ticket getOne(@WebParam(name = ITEM_ID) int id);

    @WebMethod
    @Oneway
    void save(@WebParam(name = ITEM) Ticket ticket);

    @WebMethod
    @WebResult(name = ITEM_LIST)
    TicketArray getAll();
}
