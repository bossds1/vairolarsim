package com.challenge.command;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.challenge.Processor;
import com.challenge.category.Category;
import com.challenge.category.CategoryProvider;
import com.challenge.service.Reader;
import com.challenge.service.ReaderImpl;

/**
 * Check confitions for parse file.
 *
 */
public class ParserServiceTest {

	private static final String REQUEST_FILE_1 = "input.txt";

	private static final String REQUEST_FILE_2 = "input2.txt";
	
	private CategoryProvider categoryProvider = new CategoryProvider();
	
	@Test
	public void readFile1() throws IOException, URISyntaxException {

		Processor processor = new Processor();
		Collection<Category> categories = categoryProvider.getCategories();
		
		try(Reader reader = new ReaderImpl(REQUEST_FILE_1)) {
			reader.getLines().reduce(
					(Category) null, 
					(cat, line) -> processor.processLine(categories,cat, line), 
					(x, y) -> {throw new IllegalStateException("Not used");});
		}

		Assert.assertEquals(getExpectedFile1(), processor.getSummary(categories));
		
	}

	@Test
	public void readFile2() throws IOException, URISyntaxException {

		Processor processor = new Processor();
		Collection<Category> categories = categoryProvider.getCategories();

		try(Reader reader = new ReaderImpl(REQUEST_FILE_2)) {
			reader.getLines().reduce(
					(Category) null, 
					(cat, line) -> processor.processLine(categories,cat, line), 
					(x, y) -> {throw new IllegalStateException("Not used");});
		}

		Assert.assertEquals(getExpectedFile2(), processor.getSummary(categories));
	}

	private String getExpectedFile1() {
		List<String> response = Arrays.asList("ANIMALS", "cow", "horse", "moose", "sheep", "NUMBERS", "six: 2",
				"one: 2", "seven: 1", "two: 1", "three: 2");
		return response.stream().collect(Collectors.joining(System.lineSeparator()));
	}

	private String getExpectedFile2() {
		List<String> response = Arrays.asList("ANIMALS", "cow", "horse", "moose", "sheep", "NUMBERS", "six: 2",
				"one: 2", "seven: 1", "two: 1", "three: 2", "CARS", "vw (7336a2c49b0045fa1340bf899f785e70)",
				"opel (f65b7d39472c52142ea2f4ea5e115d59)", "bmw (71913f59e458e026d6609cdb5a7cc53d)",
				"audi (4d9fa555e7c23996e99f1fb0e286aea8)");
		return response.stream().collect(Collectors.joining(System.lineSeparator()));
	}
}
