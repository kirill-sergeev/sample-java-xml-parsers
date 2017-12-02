package com.sergeev.itroi.parser.stax;

import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import lombok.NonNull;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public abstract class AbstractStaxUnmarshaller<T> implements XmlUnmarshaller<T> {

    private final Schema validationSchema;

    public AbstractStaxUnmarshaller(File schema) {
        validationSchema = schema == null ? null : loadValidationSchema(schema);
    }

    protected static String getAttributeValue(StartElement startElement, String attributeName) {
        return startElement.getAttributeByName(new QName(attributeName)).getValue();
    }

    @Override
    public T unmarshal(@NonNull Reader xml, boolean validation) {
        try {
            if (validation) {
                String xmlString = IOUtils.toString(xml);
                xml.close();

                validateXml(new StringReader(xmlString));
                xml = new StringReader(xmlString);
            }
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
            XMLEventReader reader = factory.createXMLEventReader(xml);
            return parser().parse(reader);
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private void validateXml(Reader xml) throws IOException, SAXException {
        if (validationSchema == null) {
            throw new IllegalStateException("Cannot validate XML. Validation schema is not present.");
        }
        Validator validator = validationSchema.newValidator();
        validator.validate(new StreamSource(xml));
    }

    protected abstract StaxInnerUnmarshaller<T> parser();

    protected interface StaxInnerUnmarshaller<T> {
        T parse(XMLEventReader reader);
    }
}
