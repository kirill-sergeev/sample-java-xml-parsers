package com.sergeev.itroi.parser.stax;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketStaxUnmarshallerTest {

    private static TicketArray ticketArrayObject = MockTicketData.ticketArrayInstance();
    private static String xmlString = MockTicketData.xmlStringJaxb();
    private static String stringInvalidXml = MockTicketData.invalidXmlString();
    private static File validationSchema = MockTicketData.validationSchema();

    @Test
    void shouldUnmarshalValidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketStaxUnmarshaller(validationSchema);
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), true);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalInvalidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketStaxUnmarshaller(validationSchema);

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(stringInvalidXml), true));
    }

    @Test
    void shouldUnmarshalValidXmlWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketStaxUnmarshaller();
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), false);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalWhenXmlIsNull() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketStaxUnmarshaller(validationSchema);

        assertThrows(NullPointerException.class, () -> unmarshaller.unmarshal(null, true));
    }

    @Test
    void shouldNotValidateWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new TicketStaxUnmarshaller();

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(xmlString), true));
    }
}
