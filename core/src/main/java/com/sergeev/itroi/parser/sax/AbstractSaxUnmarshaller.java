package com.sergeev.itroi.parser.sax;

import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import lombok.NonNull;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.Reader;

public abstract class AbstractSaxUnmarshaller<T> implements XmlUnmarshaller<T> {

    private static final String SYNTAX_VALIDATION_PROPERTY = "http://xml.org/sax/features/validation";
    private static final String SCHEMA_VALIDATION_PROPERTY = "http://apache.org/xml/features/validation/schema";

    private final Schema validationSchema;

    public AbstractSaxUnmarshaller(File schema) {
        validationSchema = schema == null ? null : loadValidationSchema(schema);
    }

    @Override
    public T unmarshal(@NonNull Reader xml, boolean validation) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            SAXParser parser = parserFactory.newSAXParser();
            SaxInnerUnmarshaller<T> innerParser = parser();
            parser.parse(new InputSource(xml), innerParser);

            if (validation) {
                validateXml(parserFactory);
            }
            return innerParser.getResult();
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private void validateXml(SAXParserFactory parserFactory) throws SAXException, ParserConfigurationException {
        if (validationSchema == null) {
            throw new IllegalStateException("Cannot validate XML. Validation schema is not present.");
        }
        parserFactory.setFeature(SYNTAX_VALIDATION_PROPERTY, true);
        parserFactory.setFeature(SCHEMA_VALIDATION_PROPERTY, true);
        parserFactory.setSchema(validationSchema);
    }

    protected abstract SaxInnerUnmarshaller<T> parser();

    protected abstract static class SaxInnerUnmarshaller<T> extends DefaultHandler {

        abstract T getResult();
    }
}
