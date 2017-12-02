package com.sergeev.itroi.parser;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.Reader;

public interface XmlUnmarshaller<T> {

    T unmarshal(Reader xml, boolean validation);

    default Schema loadValidationSchema(File schema) {
        try {
            return SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(schema);
        } catch (SAXException e) {
            throw new XmlParserException(e);
        }
    }
}
