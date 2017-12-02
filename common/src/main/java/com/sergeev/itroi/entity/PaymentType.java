package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Payment.TYPE, namespace = XML.Payment.SCHEMA_URI)
@XmlEnum
public enum PaymentType {

    CASH,
    CREDIT_CARD;

    public static PaymentType fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
