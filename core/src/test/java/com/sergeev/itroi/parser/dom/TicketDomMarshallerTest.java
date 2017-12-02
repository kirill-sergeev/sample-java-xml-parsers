package com.sergeev.itroi.parser.dom;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketDomMarshallerTest {

    private static final String LINE_SEPARATOR_PROPERTY = "line.separator";

    private static TicketArray ticketArrayObject = MockTicketData.ticketArrayInstance();
    private static String expectedXml = MockTicketData.xmlStringDom();

    @Test
    void shouldMarshalValidObject() {
        String originalSeparator = System.getProperty(LINE_SEPARATOR_PROPERTY);
        System.setProperty(LINE_SEPARATOR_PROPERTY, "\n");

        XmlMarshaller<TicketArray> marshaller = new TicketDomMarshaller();
        StringWriter mockWriter = new StringWriter();
        marshaller.marshal(ticketArrayObject, mockWriter);

        assertEquals(expectedXml, mockWriter.toString());

        System.setProperty(LINE_SEPARATOR_PROPERTY, originalSeparator);
    }

    @Test
    void shouldNotMarshalNullObject() {
        XmlMarshaller<TicketArray> marshaller = new TicketDomMarshaller();

        assertThrows(NullPointerException.class, () -> marshaller.marshal(null, new StringWriter()));
    }

    @Test
    void shouldNotMarshalWhenNullWriter() {
        XmlMarshaller<TicketArray> marshaller = new TicketDomMarshaller();

        assertThrows(NullPointerException.class, () -> marshaller.marshal(ticketArrayObject, null));
    }
}
