package com.sergeev.itroi.parser.dom;

import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.util.metadata.SchemaMetadata;
import lombok.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.Writer;

public abstract class AbstractDomMarshaller<T> implements XmlMarshaller<T> {

    private static final String XML_INDENT_PROPERTY = "{http://xml.apache.org/xslt}indent-amount";

    @Override
    public void marshal(@NonNull T object, @NonNull Writer writer) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(XML_INDENT_PROPERTY, "4");
            transformer.transform(new DOMSource(createDocument(object)), new StreamResult(writer));
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private Document createDocument(T object) throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);

        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.setXmlStandalone(true);

        appendRootElement(document, object);
        return document;
    }

    protected Element appendElement(Node parent, SchemaMetadata prefix, String tagName) {
        Document document = parent instanceof Document ? (Document) parent : parent.getOwnerDocument();

        Element element = document.createElementNS(prefix.getURI(), tagName);
        element.setPrefix(prefix.getPrefix());

        parent.appendChild(element);
        return element;
    }

    protected Element appendElementWithContent(Node parent, SchemaMetadata prefix, String tagName, Object value) {
        Element element = appendElement(parent, prefix, tagName);
        element.setTextContent(String.valueOf(value));
        return element;
    }

    protected abstract void appendRootElement(Document document, T object);
}
