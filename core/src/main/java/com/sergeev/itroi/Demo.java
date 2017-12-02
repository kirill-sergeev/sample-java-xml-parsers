package com.sergeev.itroi;

import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.dom.TicketDomMarshaller;
import com.sergeev.itroi.parser.dom.TicketDomUnmarshaller;
import com.sergeev.itroi.parser.jaxb.JaxbMarshaller;
import com.sergeev.itroi.parser.jaxb.JaxbUnmarshaller;
import com.sergeev.itroi.parser.sax.TicketSaxUnmarshaller;
import com.sergeev.itroi.parser.stax.TicketStaxUnmarshaller;
import com.sergeev.itroi.transformer.XmlTransformer;
import com.sergeev.itroi.transformer.XmlTransformerImpl;
import com.sergeev.itroi.util.wrapper.TicketArray;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

@SuppressWarnings("all")
public class Demo {

    public static void main(String[] args) {
        File schemaFile = new File("core/src/main/resources/xsd/tickets.xsd");
        File xmlFile = new File("core/src/main/resources/xml/tickets.xml");
        File xslFile = new File("core/src/main/resources/xsl/tickets.xsl");

        new File("out").mkdir();

        try (Writer domWriter = new PrintWriter("out/dom.xml", "UTF-8");
             Writer jaxbWriter = new PrintWriter("out/jaxb.xml", "UTF-8");
             Writer transformWriter = new PrintWriter("out/transform.html", "UTF-8")) {

            String xmlString = FileUtils.readFileToString(xmlFile, Charset.forName("UTF-8"));

            TicketArray tickets1 = new TicketSaxUnmarshaller(schemaFile)
                .unmarshal(new StringReader(xmlString), true);

            TicketArray tickets2 = new TicketDomUnmarshaller(schemaFile)
                .unmarshal(new StringReader(xmlString), true);

            TicketArray tickets3 = new TicketStaxUnmarshaller(schemaFile)
                .unmarshal(new StringReader(xmlString), true);

            TicketArray tickets4 = new JaxbUnmarshaller<>(TicketArray.class, schemaFile)
                .unmarshal(new StringReader(xmlString), true);

            System.out.println(tickets1);
            System.out.println(tickets2);
            System.out.println(tickets3);
            System.out.println(tickets4);

            XmlMarshaller<TicketArray> domMarshaller = new TicketDomMarshaller();
            XmlMarshaller<TicketArray> jaxbMarshaller = new JaxbMarshaller<>();

            domMarshaller.marshal(tickets1, domWriter);
            jaxbMarshaller.marshal(tickets1, jaxbWriter);

            XmlTransformer transformer = new XmlTransformerImpl(xslFile);
            transformer.transform(new InputStreamReader(new FileInputStream(xmlFile), "UTF-8"), transformWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

