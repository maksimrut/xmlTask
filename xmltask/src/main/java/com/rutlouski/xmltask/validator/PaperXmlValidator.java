package com.rutlouski.xmltask.validator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.rutlouski.xmltask.exception.PaperException;

public class PaperXmlValidator {
	
	static Logger logger = LogManager.getLogger();
	private static final String SCHEMA_NAME = "papers.xsd";
	private static final String SCHEMA_PATH;
	
	static {
		ClassLoader loader = PaperXmlValidator.class.getClassLoader();
		URL resourse = loader.getResource(SCHEMA_NAME);
		SCHEMA_PATH = new File(resourse.getFile()).getAbsolutePath();
	}
	
	public static boolean validateXmlFile(String xmlPath) throws PaperException {
		
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaFile = new File(SCHEMA_PATH);
		
		try {
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(xmlPath);
			validator.setErrorHandler(new PaperErrorHandler());
			validator.validate(source);
		} catch (SAXException e) {
			logger.warn("File " + xmlPath + " is not valid", e);
			return false;
		} catch (IOException e) {
			throw new PaperException("Cannot open file " + xmlPath, e);
		}
		return true;
	}
}
