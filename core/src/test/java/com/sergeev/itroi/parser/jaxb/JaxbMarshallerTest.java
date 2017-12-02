package com.sergeev.itroi.parser.jaxb;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JaxbMarshallerTest {

    private static TicketArray ticketArrayObject = MockTicketData.ticketArrayInstance();
    private static String expectedXml = MockTicketData.xmlStringJaxb();

    @Test
    void shouldMarshalValidObject() {
        XmlMarshaller<TicketArray> marshaller = new JaxbMarshaller<>();
        StringWriter mockWriter = new StringWriter();
        marshaller.marshal(ticketArrayObject, mockWriter);

        assertEquals(expectedXml, mockWriter.toString());
    }

    @Test
    void shouldNotMarshalNullObject() {
        XmlMarshaller<TicketArray> marshaller = new JaxbMarshaller<>();

        assertThrows(NullPointerException.class, () -> marshaller.marshal(null, new StringWriter()));
    }

    @Test
    void shouldNotMarshalWhenNullWriter() {
        XmlMarshaller<TicketArray> marshaller = new JaxbMarshaller<>();

        assertThrows(NullPointerException.class, () -> marshaller.marshal(ticketArrayObject, null));
    }
}
