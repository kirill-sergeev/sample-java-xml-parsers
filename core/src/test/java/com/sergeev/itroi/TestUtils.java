package com.sergeev.itroi;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public final class TestUtils {

    public static String readFileToString(String path) {
        try {
            return FileUtils.readFileToString(new File(path), "UTF-8");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
