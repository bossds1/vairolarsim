package com.challenge.category.primitives;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.category.Category;

public class NumbersCategory implements Category {

	private List<String> numbers = new ArrayList<>();

	@Override
	public void processLine(String line) {
		numbers.add(line);
	}

	public String getResult() {
		return numbers.stream()
					  .collect(groupingBy(identity(), counting()))
			 		  .entrySet()
			 		  .stream()
					  .map(entry -> String.format("%s: %d", entry.getKey(), entry.getValue()))
					  .collect(Collectors.joining(System.lineSeparator()));
	}
}
