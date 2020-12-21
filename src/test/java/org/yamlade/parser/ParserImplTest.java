package org.yamlade.parser;

import org.junit.jupiter.api.Test;
import org.yamlade.model.Stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ParserImplTest {
	private static final String TEST_YAML_RESOURCES = "src/test/resources/__yaml";

	@Test
	void parseInput() throws IOException {
		ParserImpl parser = new ParserImpl();

		Stream stream = parser.parseInput(Files.newBufferedReader(Paths.get(TEST_YAML_RESOURCES, "basic_scalar.yaml")));
		System.out.println(stream);
	}
}