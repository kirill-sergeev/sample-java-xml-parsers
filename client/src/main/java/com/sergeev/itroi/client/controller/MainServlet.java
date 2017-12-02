package com.sergeev.itroi.client.controller;

import com.sergeev.itroi.client.config.WebConstants;
import com.sergeev.itroi.client.service.TicketService;
import com.sergeev.itroi.entity.Ticket;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebConstants.ROOT_MAPPING)
public class MainServlet extends HttpServlet {

    private static final String TICKET_PARAM = "ticket";

    @Inject
    private TicketService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticketIdParam = req.getParameter(TICKET_PARAM);
        int ticketId = Integer.parseInt(ticketIdParam);

        Ticket ticket = service.getOne(ticketId);

        req.setAttribute(TICKET_PARAM, ticket);
        req.getRequestDispatcher(WebConstants.ROOT_VIEW).forward(req, resp);
    }

}
