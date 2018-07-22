package com.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ApplicationTest {
	
	@Test
	public void withoutArgs() throws URISyntaxException, IOException {
		Application.main(null);
	}
	
	@Test
	public void testFile1() {
		
		//ReaderServiceDummyImpl readerService = new ReaderServiceDummyImpl();
		
	}
	
	private String getExpectedFile1() {
		List<String> response = Arrays.asList("ANIMALS", "cow", "horse", "moose", "sheep", "NUMBERS", "six: 2",
				"one: 2", "seven: 1", "two: 1", "three: 2");
		return response.stream().collect(Collectors.joining(System.lineSeparator()));
	}
	
	private String getDummyData(String file) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
		try {
			return IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			return "";
		}
	}

}
