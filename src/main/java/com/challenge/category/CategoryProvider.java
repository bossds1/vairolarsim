package com.challenge.category;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;

import com.challenge.category.exception.ApplicationException;
import com.challenge.category.primitives.AnimalsCategory;
import com.challenge.category.primitives.CarsCategory;
import com.challenge.category.primitives.NumbersCategory;

public class CategoryProvider {

	private List<Category> categories;

	private static Reflections reflections = new Reflections("com.challenge.category.extension");

	public CategoryProvider() {
		
		Set<Class<? extends Category>> classes = reflections.getSubTypesOf(Category.class);
		
		Stream<Category> primitives = Stream.of(new AnimalsCategory(),new NumbersCategory(),new CarsCategory());
		
		Stream<Category> extensions = classes.stream().map(c -> {
			try {
				return c.newInstance();
				
			} catch (ReflectiveOperationException exception) {
				throw new ApplicationException("Not possible to instantiate class "+ c,exception);
			}
			
		});
		
		categories = Stream.concat(primitives, extensions).collect(Collectors.toList());
	}

	public Collection<Category> getCategories() {
		return categories;
	}

}
