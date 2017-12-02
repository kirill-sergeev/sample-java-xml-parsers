package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Price.TYPE_NAME, namespace = XML.Payment.SCHEMA_URI)
public class Price {

    @XmlValue
    protected Double value;

    @XmlAttribute
    protected Currency currency = Currency.USD;
}
