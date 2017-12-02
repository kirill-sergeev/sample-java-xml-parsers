package com.sergeev.itroi.parser.dom;

import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import lombok.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractDomUnmarshaller<T> implements XmlUnmarshaller<T> {

    protected static final String DEFAULT_NAMESPACE_URI = "*";

    private final Schema validationSchema;

    AbstractDomUnmarshaller(File schema) {
        validationSchema = schema == null ? null : loadValidationSchema(schema);
    }

    @Override
    public T unmarshal(@NonNull Reader xml, boolean validation) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(xml));

            if (validation) {
                validateXml(document);
            }
            return parseRootElement(document);
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private void validateXml(Document document) throws SAXException, IOException {
        if (validationSchema == null) {
            throw new IllegalStateException("Cannot validate XML. Validation schema is not present.");
        }
        Validator validator = validationSchema.newValidator();
        validator.validate(new DOMSource(document));
    }

    protected Node getNode(Element element, String name) {
        return element.getElementsByTagNameNS(DEFAULT_NAMESPACE_URI, name).item(0);
    }

    protected abstract T parseRootElement(Document document);
}
