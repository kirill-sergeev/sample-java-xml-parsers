package com.sergeev.itroi.transformer;

import com.sergeev.itroi.MockTicketData;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XmlTransformerImplTest {

    private static final String LINE_SEPARATOR_PROPERTY = "line.separator";

    private static String xmlString = MockTicketData.xmlStringDom();
    private static String htmlString = MockTicketData.htmlString();
    private static File transformationSchema = MockTicketData.transformationSchema();
    private static File invalidTransformationSchema = MockTicketData.transformationSchemaInvalid();

    @Test
    void shouldTransformValidXmlWithValidXslSchema() throws IOException {
        String originalSeparator = System.getProperty(LINE_SEPARATOR_PROPERTY);
        System.setProperty(LINE_SEPARATOR_PROPERTY, "\n");

        Writer mockWriter = new StringWriter();
        Reader mockReader = new StringReader(xmlString);

        XmlTransformer transformer = new XmlTransformerImpl(transformationSchema);
        transformer.transform(mockReader, mockWriter);

        assertEquals(htmlString, mockWriter.toString());

        System.setProperty(LINE_SEPARATOR_PROPERTY, originalSeparator);
    }

    @Test
    void shouldNotTransformWhenXmlIsNullXml() throws IOException {
        XmlTransformer transformer = new XmlTransformerImpl(transformationSchema);

        assertThrows(NullPointerException.class, () -> transformer.transform(null, new StringWriter()));
    }

    @Test
    void shouldNotTransformXmlWithInvalidXslSchema() throws IOException {
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new NullOutputStream()));

        assertThrows(XmlTransformerException.class, () -> {
            XmlTransformer transformer = new XmlTransformerImpl(invalidTransformationSchema);
            transformer.transform(new StringReader(xmlString), new StringWriter());
        });

        System.setErr(originalErr);
    }
}
