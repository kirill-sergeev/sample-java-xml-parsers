package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Payment.TYPE_NAME, namespace = XML.Payment.SCHEMA_URI, propOrder = {
    XML.Payment.PRICE,
    XML.Payment.TYPE,
    XML.Payment.STATUS
})
public class Payment implements Identifiable<Integer> {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer id;

    @XmlElement(required = true)
    private Price price;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private PaymentType paymentType;

    @XmlElement(required = true, defaultValue = "UNPAID")
    @XmlSchemaType(name = "string")
    private PaymentStatus paymentStatus;
}
