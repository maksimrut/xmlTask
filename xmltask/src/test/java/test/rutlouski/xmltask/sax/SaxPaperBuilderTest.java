package test.rutlouski.xmltask.sax;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.rutlouski.xmltask.builder.AbstractPaperBuilder;
import com.rutlouski.xmltask.builder.factory.PaperBuilderFactory;
import com.rutlouski.xmltask.exception.PaperException;

public class SaxPaperBuilderTest {
	
	private static final String FILE_NAME = "src/test/resources/papers.xml";

 
	@Test

	public void buildSetPapersTest() throws PaperException {
  
		PaperBuilderFactory paperFactory = new PaperBuilderFactory(); 
		AbstractPaperBuilder builder = paperFactory.createPaperBuilder("sax");
		builder.buildSetPapers(FILE_NAME);
		
		int actual = builder.getPapers().size();
		int expected = 16;

		assertEquals(actual, expected);
	}
}
