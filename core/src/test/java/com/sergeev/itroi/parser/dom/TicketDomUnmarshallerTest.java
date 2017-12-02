package com.sergeev.itroi.parser.dom;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketDomUnmarshallerTest {

    private static TicketArray ticketArrayObject = MockTicketData.ticketArrayInstance();
    private static String xmlString = MockTicketData.xmlStringDom();
    private static String invalidXmlString = MockTicketData.invalidXmlString();
    private static File validationSchema = MockTicketData.validationSchema();

    @Test
    void shouldUnmarshalValidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketDomUnmarshaller(validationSchema);
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), true);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalInvalidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketDomUnmarshaller(validationSchema);

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(invalidXmlString), true));
    }

    @Test
    void shouldUnmarshalValidXmlWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketDomUnmarshaller();
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), false);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalWhenXmlIsNull() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketDomUnmarshaller(validationSchema);

        assertThrows(NullPointerException.class, () -> unmarshaller.unmarshal(null, true));
    }

    @Test
    void shouldNotValidateWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketDomUnmarshaller();

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(xmlString), true));
    }
}
