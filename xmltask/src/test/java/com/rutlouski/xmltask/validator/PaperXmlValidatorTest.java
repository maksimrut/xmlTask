package com.rutlouski.xmltask.validator;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.rutlouski.xmltask.exception.PaperException;

public class PaperXmlValidatorTest {

  @Test
  public void validateXmlFileTest() throws PaperException {
	  
	 assertTrue(PaperXmlValidator.validateXmlFile("src/main/resources/papers.xml"));

  }
}
