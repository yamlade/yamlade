package org.yamlade;

import org.junit.jupiter.api.Test;
import org.yamlade.model.Stream;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class YamlTest {

	@Test
	void load() {
		Yaml yaml = new Yaml();
		Stream stream = yaml.load("Test");

		assertThat(stream).isNotNull();
		assertThat(stream).isEqualTo(new Stream());
	}
}