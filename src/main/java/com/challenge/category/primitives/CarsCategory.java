package com.challenge.category.primitives;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

import com.challenge.category.Category;

public class CarsCategory implements Category {

	private Set<String> cars = new HashSet<>();

	@Override
	public void processLine(String line) {
		cars.add(line);
	}

	public String getResult() {
		return  cars.stream()
					.map(entry -> String.format("%s (%s)", entry, DigestUtils.md5Hex(entry)))
					.sorted(Comparator.reverseOrder())
					.collect(Collectors.joining(System.lineSeparator()));
	}
}
