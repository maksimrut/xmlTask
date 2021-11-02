package test.rutlouski.xmltask.validator;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.rutlouski.xmltask.exception.PaperException;
import com.rutlouski.xmltask.validator.PaperXmlValidator;

public class PaperXmlValidatorTest {
	
	private static final String FILE_NAME = "src/test/resources/papers.xml";

  @Test
  public void validateXmlFileTest() throws PaperException {
	  
	 assertTrue(PaperXmlValidator.validateXmlFile(FILE_NAME));
  }
}
