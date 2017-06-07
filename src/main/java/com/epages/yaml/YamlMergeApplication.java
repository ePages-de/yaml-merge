package com.epages.yaml;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.cli.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlMergeApplication {

    public static void main(String[] args) throws IOException {
        try {
            CommandLineArguments arguments = new YamlCommandLineParser().parseCommandLine(args);
            arguments.validate();
            YamlMapper mapper = new YamlMapper();
            try(
                Reader source = arguments.getSource().asReader();
                Reader override = arguments.getOverride().asReader()) {
                JsonNode merged = new YamlMerger().merge(
                        mapper.read(source),
                        mapper.read(override)
                );
                mapper.write(new YAMLFactory().createGenerator(System.out), merged);
            }
        } catch (ParseException e) { // printing help is handled inside YamlCommandLineParser
            System.exit(1);
        }
    }
}
