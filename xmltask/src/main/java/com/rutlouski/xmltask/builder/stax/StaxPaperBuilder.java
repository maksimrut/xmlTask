package com.rutlouski.xmltask.builder.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.rutlouski.xmltask.builder.AbstractPaperBuilder;
import com.rutlouski.xmltask.builder.PaperEnum;
import com.rutlouski.xmltask.entity.CatalogueType;
import com.rutlouski.xmltask.entity.JournalType;
import com.rutlouski.xmltask.entity.Paper;
import com.rutlouski.xmltask.entity.PaperLanguage;
import com.rutlouski.xmltask.exception.PaperException;

public class StaxPaperBuilder extends AbstractPaperBuilder {
	
	private XMLInputFactory inputFactory;
	private static final String WEBSITE = "website";
	private static final String ID = "id";
	
	public StaxPaperBuilder() {
		
		inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildSetPapers(String fileName) throws PaperException {
		
		XMLStreamReader reader = null;
		String name;
		
		try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
			
			reader = inputFactory.createXMLStreamReader(inputStream);
			
			while (reader.hasNext()) {
				int type = reader.next();
				
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName().toUpperCase();
					
					if (name.equals(PaperEnum.JOURNAL.name())) {
						papers.add(buildJournal(reader));
					}
					
					if (name.equals(PaperEnum.CATALOGUE.name())) {
						papers.add(buildCatalogue(reader));
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new PaperException("File " + fileName + " is not found", e);
		} catch (XMLStreamException e) {
			throw new PaperException("StAX parser error", e);
		} catch (IOException e) {
			throw new PaperException("I/O error", e);
		}
	}

	private Paper buildJournal(XMLStreamReader reader) throws XMLStreamException {
		
		JournalType journal = new JournalType();
		String name;

		journal.setId(reader.getAttributeValue(null, ID));
		
		if (reader.getAttributeValue(null, WEBSITE) != null) {
			journal.setWebsite(reader.getAttributeValue(null, WEBSITE));
		}
		
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
				case XMLStreamConstants.START_ELEMENT: {
					name = reader.getLocalName().toUpperCase();
						
					switch (PaperEnum.valueOf(name)) {
						case TITLE -> journal.setTitle(getXMLText(reader));
						
						case LANGUAGE -> journal.setLanguage(PaperLanguage.valueOf(getXMLText(reader).toUpperCase()));
						
						case DATE -> journal.setDate(YearMonth.parse(getXMLText(reader)));
						
						case COLORED -> journal.setColored(Boolean.parseBoolean(getXMLText(reader)));
						
						case PRICE -> journal.setPrice(Integer.parseInt(getXMLText(reader)));
						
						case POINTS -> journal.setPoints(Integer.parseInt(getXMLText(reader)));
						
						case SCOPUS -> journal.setScopus(Boolean.parseBoolean(getXMLText(reader)));
					}
				}
				break;
				
				case XMLStreamConstants.END_ELEMENT: {
					name = reader.getLocalName().toUpperCase();
					
					if (PaperEnum.valueOf(name) == PaperEnum.JOURNAL) {
						return journal;
					}
				}
			}
		}
		throw new XMLStreamException("Unknown tag element");
	}
	
	private Paper buildCatalogue(XMLStreamReader reader) throws XMLStreamException {
		
		CatalogueType catalogue= new CatalogueType();
		String name;
		
		catalogue.setId(reader.getAttributeValue(null, ID));
		
		if (reader.getAttributeValue(null, WEBSITE) != null) {
			catalogue.setWebsite(reader.getAttributeValue(null, WEBSITE));
		}
		
		while (reader.hasNext()) {
			int type = reader.next();
			
			switch (type) {
				case XMLStreamConstants.START_ELEMENT: {
					name = reader.getLocalName().toUpperCase();
					
					switch (PaperEnum.valueOf(name)) {
						case TITLE -> catalogue.setTitle(getXMLText(reader));
						
						case LANGUAGE -> catalogue.setLanguage(PaperLanguage.valueOf(getXMLText(reader).toUpperCase()));
						
						case DATE -> catalogue.setDate(YearMonth.parse(getXMLText(reader)));
						
						case COLORED -> catalogue.setColored(Boolean.parseBoolean(getXMLText(reader)));
						
						case PRICE -> catalogue.setPrice(Integer.parseInt(getXMLText(reader)));
						
						case VOLUME -> catalogue.setVolume(Integer.parseInt(getXMLText(reader)));
					}
				}
				break;
				
				case XMLStreamConstants.END_ELEMENT: {
					name = reader.getLocalName().toUpperCase();
					
					if (PaperEnum.valueOf(name) == PaperEnum.CATALOGUE) {
						return catalogue;
					}
				}
			}
		}
		throw new XMLStreamException("Unknown tag element");
	}
	
	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
