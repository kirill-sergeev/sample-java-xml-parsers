package com.sergeev.itroi.client.entity;

import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = XML.TicketArray.TYPE_NAME, namespace = XML.Ticket.SCHEMA_URI)
public class TicketArray {

    @XmlElement(namespace = "", name = "item")
    private List<Ticket> items = new ArrayList<>();
}
