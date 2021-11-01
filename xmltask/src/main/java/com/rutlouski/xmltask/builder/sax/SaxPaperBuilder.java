package com.rutlouski.xmltask.builder.sax;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.rutlouski.xmltask.builder.AbstractPaperBuilder;
import com.rutlouski.xmltask.entity.Paper;
import com.rutlouski.xmltask.exception.PaperException;

public class SaxPaperBuilder extends AbstractPaperBuilder {
	
	private PaperHandler paperHandler;
	private XMLReader reader;
	
	public SaxPaperBuilder() throws PaperException {
		paperHandler = new PaperHandler();
		
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(paperHandler);
		} catch (SAXException e) {
			throw new PaperException("Error in SAX parser", e);
		}
	}
	
	public SaxPaperBuilder(Set<Paper> papers) {
		super(papers);
	}
	
	@Override
	public void buildSetPapers(String fileName) throws PaperException {
		
		try {
			reader.parse(fileName);
		} catch (IOException e) {
			throw new PaperException("Cannot open file " + fileName, e);
		} catch (SAXException e) {
			throw new PaperException("File " + fileName + " is not valid", e);
		}
		papers = paperHandler.getPapers();
	}
}
