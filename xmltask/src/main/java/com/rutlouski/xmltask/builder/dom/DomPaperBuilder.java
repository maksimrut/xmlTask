package com.rutlouski.xmltask.builder.dom;

import java.io.IOException;
import java.time.YearMonth;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rutlouski.xmltask.builder.AbstractPaperBuilder;
import com.rutlouski.xmltask.builder.PaperEnum;
import com.rutlouski.xmltask.entity.CatalogueType;
import com.rutlouski.xmltask.entity.JournalType;
import com.rutlouski.xmltask.entity.Paper;
import com.rutlouski.xmltask.entity.PaperLanguage;
import com.rutlouski.xmltask.exception.PaperException;

public class DomPaperBuilder extends AbstractPaperBuilder {
	
	private DocumentBuilder docBuilder;
	private static final String DEFAULT_WEBSITE = "http://www.example.org";
	private static final String WEBSITE = "website";
	private static final String ID = "id";
	private static final String EMPTY = "";

	public DomPaperBuilder() throws PaperException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new PaperException("Parser configuration error", e);
		}
	}
	
	@Override
	public void buildSetPapers(String fileName) throws PaperException {
		
		Document doc = null;
		
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList journalList = root.getElementsByTagName("journal");
			NodeList catalogueList = root.getElementsByTagName("catalogue");
			
			for (int i = 0; i < journalList.getLength(); i++) {
				Element paperElement = (Element) journalList.item(i);
				Paper paper = buildPaper(paperElement);
				papers.add(paper);
			}
			
			for (int i = 0; i < catalogueList.getLength(); i++) {
				Element paperElement = (Element) catalogueList.item(i);
				Paper paper = buildPaper(paperElement);
				papers.add(paper);
			}
			
		} catch (SAXException e) {
			throw new PaperException("Error in SAX parser", e);
		} catch (IOException e) {
			throw new PaperException("Cannot open file " + fileName, e);
		}
	}

	private Paper buildPaper(Element paperElement) {
		Paper paper = new Paper();
		
		if (paperElement.getTagName().equalsIgnoreCase(PaperEnum.JOURNAL.name())) {
			JournalType journal = new JournalType();
			journal.setPoints(Integer.parseInt(getElementTextContent(paperElement, PaperEnum.POINTS.name().toLowerCase())));
			journal.setScopus(Boolean.parseBoolean(getElementTextContent(paperElement, PaperEnum.SCOPUS.name().toLowerCase())));
			paper = journal;
		}
		
		if (paperElement.getTagName().equalsIgnoreCase(PaperEnum.CATALOGUE.name())) {
			CatalogueType catalogue = new CatalogueType();
			catalogue.setVolume(Integer.parseInt(getElementTextContent(paperElement, PaperEnum.VOLUME.name().toLowerCase())));
			paper = catalogue;
		}
		
		paper.setTitle(getElementTextContent(paperElement, PaperEnum.TITLE.name().toLowerCase()));
		paper.setLanguage(PaperLanguage.valueOf(getElementTextContent(paperElement, "language").toUpperCase()));
		paper.setDate(YearMonth.parse(getElementTextContent(paperElement, PaperEnum.DATE.name().toLowerCase())));
		paper.setColored(Boolean.parseBoolean(getElementTextContent(paperElement, PaperEnum.COLORED.name().toLowerCase())));
		paper.setPrice(Integer.parseInt(getElementTextContent(paperElement, PaperEnum.PRICE.name().toLowerCase())));
		paper.setId(paperElement.getAttribute(ID));
		
		if (paperElement.getAttribute(WEBSITE) != EMPTY) {
			paper.setWebsite(paperElement.getAttribute(WEBSITE));
		} else paper.setWebsite(DEFAULT_WEBSITE);
		return paper;
	}

	private String getElementTextContent(Element paperElement, String elementName) {

		NodeList nList = paperElement.getElementsByTagName(elementName);
		Node node = nList.item(0);
		return node.getTextContent();
	}
}
