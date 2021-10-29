package com.rutlouski.xmltask.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class PaperErrorHandler implements ErrorHandler {
	
	static Logger logger = LogManager.getLogger();
	
	public void warning(SAXParseException e) {
		logger.warn(getLineColumnNumber(e) + "-" + e.getMessage());
	}
	
	public void error(SAXParseException e) {
		logger.error(getLineColumnNumber(e) + "-" + e.getMessage());
	}
	
	public void fatalError(SAXParseException e) {
		logger.warn(getLineColumnNumber(e) + "-" + e.getMessage());
	}
	
	private String getLineColumnNumber(SAXParseException e) {
		return e.getLineNumber() + " : " + e.getColumnNumber();
	}
}