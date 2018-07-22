package com.challenge;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.category.Category;
import com.challenge.category.CategoryProvider;
import com.challenge.service.Reader;
import com.challenge.service.ReaderImpl;

public class Application {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	void execute(final String fileName) throws IOException, URISyntaxException {
		LOGGER.info("> Starting process: %s.",fileName);

		Processor processor = new Processor();
		CategoryProvider categoryProvider = new CategoryProvider();
		Collection<Category> categories = categoryProvider.getCategories();
		
		try {
			
			try(Reader reader = new ReaderImpl(fileName)) {
				reader.getLines().reduce(
						(Category) null, 
						(cat, line) -> processor.processLine(categories,cat, line), 
						(x, y) -> {throw new IllegalStateException("Not used");});
			}


			System.out.println(processor.getSummary(categories));
			
		} catch (Exception exception) {
			LOGGER.error("Internal Error",exception);
		}
		

		LOGGER.info("< Finished process.");
	}

	public static void main(String[] args) throws URISyntaxException, IOException {
		Application application = new Application();
		application.execute(args[0]);
	}

}
