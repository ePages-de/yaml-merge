package com.epages.yaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class YamlSource {

    private final String filename;

    public YamlSource(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public Reader asReader() throws IOException {
        if (filename == null) {
            return new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        return new BufferedReader(new FileReader(filename));
    }

    public boolean exists() {
        if (filename == null) {
            return true;
        }
        return new File(filename).exists();
    }
}
