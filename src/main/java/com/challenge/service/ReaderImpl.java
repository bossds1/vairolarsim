package com.challenge.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReaderImpl implements Reader {
	
	private Stream<String> stream;
	
	public ReaderImpl(final String fileName) throws URISyntaxException, IOException {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		Path path = Paths.get(url.toURI());
		stream = Files.lines(path);
	}
	
	public Stream<String> getLines() {
		return stream;
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}

}
