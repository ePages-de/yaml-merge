package com.epages.yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

class CommandLineArguments {

    private final YamlSource source;
    private final YamlSource override;

    public CommandLineArguments(String source, String override) {
        this.source = new YamlSource(source);
        this.override = new YamlSource(override);
    }

    public YamlSource getSource() throws IOException {
        return source;
    }

    public YamlSource getOverride() throws IOException {
        return override;
    }

    public void validate() {
        Stream.of(source, override)
        .filter(source -> !source.exists())
        .forEach(source -> {
            throw new UncheckedIOException(new FileNotFoundException(source.getFilename()));
        });
    }
}