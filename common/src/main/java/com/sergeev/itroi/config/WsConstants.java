package com.sergeev.itroi.config;

public final class WsConstants {

    private WsConstants() {
    }

    public final class TicketService {
        public static final String ITEM = "ticket";
        public static final String ITEM_ID = "id";
        public static final String ITEM_LIST = "tickets";

        public static final String NAME = "TicketService";
        public static final String PORT_NAME = "TicketPort";
        public static final String NAMESPACE = "http://itroi.sergeev.com/ticket/wsdl";
        public static final String WSDL_LOCATION = "/core/service/tickets?wsdl";

        private TicketService() {
        }
    }
}
