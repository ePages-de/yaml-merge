package com.epages.yaml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.UncheckedIOException;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.Test;

public class YamlCommandLineParserTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void test_parse_short() throws Exception {
        CommandLineArguments args = new YamlCommandLineParser().parseCommandLine(new String[] {"-s", "file1", "-o", "file2"});
        assertThat(args.getSource().getFilename()).isEqualTo("file1");
        assertThat(args.getOverride().getFilename()).isEqualTo("file2");
    }

    @Test
    public void test_parse_long() throws Exception {
        CommandLineArguments args = new YamlCommandLineParser().parseCommandLine(new String[] {"--source", "file1", "--override", "file2"});
        assertThat(args.getSource().getFilename()).isEqualTo("file1");
        assertThat(args.getOverride().getFilename()).isEqualTo("file2");
    }

    @Test(expected=UncheckedIOException.class)
    public void test_validate_invalid_files() throws Exception {
        CommandLineArguments args = new YamlCommandLineParser().parseCommandLine(new String[] {"-s", "file1", "-o", "file2"});
        args.validate();
    }

    @Test
    public void test_validate_valid_files() throws Exception {
        File createdFile1 = folder.newFile("myfile.yaml");
        // just need a valid File reference.
        CommandLineArguments args = new YamlCommandLineParser().parseCommandLine(new String[] { "-o", createdFile1.toString() });
        args.validate();
        assertThat(args.getSource().asReader()).isNotNull();
        assertThat(args.getOverride().asReader()).isNotNull();
    }

}
