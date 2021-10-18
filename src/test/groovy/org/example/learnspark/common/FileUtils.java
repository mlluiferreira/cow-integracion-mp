package org.example.learnspark.common;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileUtils {
    public static byte[] loadFileAsByteArray(String path) throws IOException {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
        return org.apache.commons.io.FileUtils.readFileToByteArray(file);
    }

    public static String loadFileAsString(String path) throws IOException {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
        return new String(org.apache.commons.io.FileUtils.readFileToByteArray(file));
    }
}
