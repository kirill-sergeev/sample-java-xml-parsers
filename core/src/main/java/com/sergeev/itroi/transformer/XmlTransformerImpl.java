package com.sergeev.itroi.transformer;

import lombok.NonNull;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.Reader;
import java.io.Writer;

public class XmlTransformerImpl implements XmlTransformer {

    private static final String XML_INDENT_PROPERTY = "{http://xml.apache.org/xslt}indent-amount";

    private final File xslSchema;

    public XmlTransformerImpl(@NonNull File xslSchema) {
        this.xslSchema = xslSchema;
    }

    public void transform(@NonNull Reader xml, @NonNull Writer html) {
        Source xmlSource = new StreamSource(xml);
        Source xslSource = new StreamSource(xslSchema);

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xslSource);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(XML_INDENT_PROPERTY, "2");
            transformer.transform(xmlSource, new StreamResult(html));
        } catch (Exception e) {
            throw new XmlTransformerException(e);
        }
    }
}
