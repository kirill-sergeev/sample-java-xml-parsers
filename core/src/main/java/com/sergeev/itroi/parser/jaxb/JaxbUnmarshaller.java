package com.sergeev.itroi.parser.jaxb;

import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import lombok.NonNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.Reader;

public class JaxbUnmarshaller<T> implements XmlUnmarshaller<T> {

    private final Class<T> clazz;
    private final Schema validationSchema;

    public JaxbUnmarshaller(Class<T> clazz) {
        this.clazz = clazz;
        this.validationSchema = null;
    }

    public JaxbUnmarshaller(Class<T> clazz, File schema) {
        this.clazz = clazz;
        this.validationSchema = loadValidationSchema(schema);
    }

    @Override
    public T unmarshal(@NonNull Reader xml, boolean validation) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (validation) {
                validateXml(unmarshaller);
            }
            return clazz.cast(unmarshaller.unmarshal(xml));
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private void validateXml(Unmarshaller unmarshaller) {
        if (validationSchema == null) {
            throw new IllegalStateException("Cannot validate XML. Validation schema is not present.");
        }
        unmarshaller.setSchema(validationSchema);
    }
}
