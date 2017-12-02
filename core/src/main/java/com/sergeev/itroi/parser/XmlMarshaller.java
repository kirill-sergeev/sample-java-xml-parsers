package com.sergeev.itroi.parser;

import java.io.Writer;

public interface XmlMarshaller<T> {

    void marshal(T object, Writer writer);
}
