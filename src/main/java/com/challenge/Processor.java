package com.challenge;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.challenge.category.Category;

public class Processor {
	
	public Category processLine(Collection<Category> categories, Category category, String line) {
		return categories.stream()
			.filter(e -> e.getName().equals(line))
			.findFirst()
			.map(newCategory -> (Supplier<Category>) () -> newCategory)
			.orElse(() -> {
				category.processLine(line.toLowerCase());
				return category;
			})
			.get();
	}
	
	public String getSummary(Collection<Category> categories) {
		return categories.stream()
				.filter(cat -> !cat.isEmpty())
				.map(cat -> cat.getName() + System.lineSeparator() + cat.getResult())
				.collect(Collectors.joining(System.lineSeparator()));
	}
}
