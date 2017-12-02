package com.sergeev.itroi.parser.jaxb;

import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.XmlParserException;
import lombok.NonNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Writer;

public class JaxbMarshaller<T> implements XmlMarshaller<T> {

    @Override
    public void marshal(@NonNull T object, @NonNull Writer writer) {
        try {
            setupGenerator(object).marshal(object, writer);
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    private Marshaller setupGenerator(T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }
}
