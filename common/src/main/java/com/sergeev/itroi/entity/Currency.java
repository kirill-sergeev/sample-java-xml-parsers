package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Price.CURRENCY, namespace = XML.Payment.SCHEMA_URI)
@XmlEnum
public enum Currency {

    AUD,
    BRL,
    CAD,
    CNY,
    EUR,
    GBP,
    INR,
    JPY,
    RUR,
    UAH,
    USD;

    public static Currency fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
