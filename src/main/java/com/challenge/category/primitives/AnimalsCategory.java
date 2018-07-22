package com.challenge.category.primitives;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.challenge.category.Category;

public class AnimalsCategory implements Category {

	private Set<String> animals = new HashSet<>();

	@Override
	public void processLine(String line) {
		animals.add(line);
	}

	public String getResult() {
		return  animals.stream()
					   .sorted()
					   .collect(Collectors.joining(System.lineSeparator()));
	}
}
