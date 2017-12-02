package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Passenger.TYPE_NAME, namespace = XML.Passenger.SCHEMA_URI, propOrder = {
    XML.Passenger.NAME,
    XML.Passenger.GENDER,
    XML.Passenger.BIRTHDATE,
    XML.Passenger.EMAIL
})
public class Passenger implements Identifiable<Integer> {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer id;

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String name;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private Gender gender;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private LocalDate birthdate;

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String email;
}
