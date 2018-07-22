package com.challenge.service;

import java.io.Closeable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public interface Reader extends Closeable {
	
	public Stream<String> getLines() throws URISyntaxException, IOException;

}
