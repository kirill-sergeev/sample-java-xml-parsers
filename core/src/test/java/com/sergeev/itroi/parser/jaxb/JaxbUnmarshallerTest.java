package com.sergeev.itroi.parser.jaxb;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JaxbUnmarshallerTest {

    private static TicketArray ticketArrayObject = MockTicketData.ticketArrayInstance();
    private static String xmlString = MockTicketData.xmlStringJaxb();
    private static String invalidXmlString = MockTicketData.invalidXmlString();
    private static File validationSchema = MockTicketData.validationSchema();

    @Test
    void shouldUnmarshalValidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new JaxbUnmarshaller<>(TicketArray.class, validationSchema);
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), true);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalInvalidXml() {
        XmlUnmarshaller<TicketArray> unmarshaller = new JaxbUnmarshaller<>(TicketArray.class, validationSchema);

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(invalidXmlString), true));
    }

    @Test
    void shouldUnmarshalValidXmlWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new JaxbUnmarshaller<>(TicketArray.class);
        TicketArray resultTicketArray = unmarshaller.unmarshal(new StringReader(xmlString), false);

        assertEquals(ticketArrayObject, resultTicketArray);
    }

    @Test
    void shouldNotUnmarshalWhenXmlIsNull() {
        XmlUnmarshaller<TicketArray> unmarshaller = new JaxbUnmarshaller<>(TicketArray.class);

        assertThrows(NullPointerException.class, () -> unmarshaller.unmarshal(null, true));
    }

    @Test
    void shouldNotValidateWithoutValidationSchema() {
        XmlUnmarshaller<TicketArray> unmarshaller = new JaxbUnmarshaller<>(TicketArray.class);

        assertThrows(XmlParserException.class, () -> unmarshaller.unmarshal(new StringReader(xmlString), true));
    }
}
