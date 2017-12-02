package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Coordinates.TYPE_NAME, namespace = XML.Airport.SCHEMA_URI, propOrder = {
    XML.Coordinates.LATITUDE,
    XML.Coordinates.LONGITUDE
})
public class Coordinates {

    @XmlElement(required = true)
    private Double latitude;

    @XmlElement(required = true)
    private Double longitude;
}
