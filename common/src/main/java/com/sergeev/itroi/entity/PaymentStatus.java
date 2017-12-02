package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Payment.STATUS, namespace = XML.Payment.SCHEMA_URI)
@XmlEnum
public enum PaymentStatus {

    PAID,
    UNPAID,
    RETURNED,
    CANCELED;

    public static PaymentStatus fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
