package com.sergeev.itroi.transformer;

import java.io.Reader;
import java.io.Writer;

public interface XmlTransformer {

    void transform(Reader xml, Writer html);
}
