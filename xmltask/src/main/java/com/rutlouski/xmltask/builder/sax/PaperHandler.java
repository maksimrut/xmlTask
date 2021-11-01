package com.rutlouski.xmltask.builder.sax;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.rutlouski.xmltask.entity.Paper;
import com.rutlouski.xmltask.entity.PaperLanguage;
import com.rutlouski.xmltask.builder.PaperEnum;
import com.rutlouski.xmltask.entity.CatalogueType;
import com.rutlouski.xmltask.entity.JournalType;

public class PaperHandler extends DefaultHandler {
	
	private Set<Paper> papers;
	private Paper currentPaper;
	private PaperEnum currentEnum;
	private EnumSet<PaperEnum> paperEnumRange;
	
	public PaperHandler() {
		papers = new HashSet<>();
		paperEnumRange = EnumSet.range(PaperEnum.TITLE, PaperEnum.VOLUME);
	}
	
	public Set<Paper> getPapers() {
		return papers;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		
		PaperEnum tempEnum = PaperEnum.valueOf(localName.toUpperCase());
		switch (tempEnum) {
			
			case JOURNAL, CATALOGUE -> paperCreate(tempEnum, attributes);
		}
		if (paperEnumRange.contains(tempEnum)) {
			currentEnum = tempEnum;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		
		String info = new String(ch, start, length);
		if (currentEnum != null) {
			
			switch(currentEnum) {
			
				case TITLE -> currentPaper.setTitle(info);
			
				case DATE -> currentPaper.setDate(YearMonth.parse(info));
			
				case COLORED -> currentPaper.setColored(Boolean.getBoolean(info));
			
				case PRICE -> currentPaper.setPrice(Integer.parseInt(info));
				
				case LANGUAGE -> {
					String constantName = info.toUpperCase();
					currentPaper.setLanguage(PaperLanguage.valueOf(constantName));
				}
			
				case POINTS -> {
					JournalType journal = (JournalType) currentPaper;
					journal.setPoints(Integer.parseInt(info));
				}
			
				case SCOPUS -> {
					JournalType journal = (JournalType) currentPaper;
					journal.setScopus(Boolean.getBoolean(info));
				}
				
				case VOLUME -> {
					CatalogueType catalogue = (CatalogueType) currentPaper;
					catalogue.setVolume(Integer.parseInt(info));
				}
			
				default -> throw new EnumConstantNotPresentException(
					currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if ("journal".equals(localName) || "catalogue".equals(localName)) {
			papers.add(currentPaper);
			currentPaper = null;
		}
	}
	
	private void paperCreate(PaperEnum tempEnum, Attributes attributes) {
		
		String[] attrArray = new String[attributes.getLength()];
		
		for (int i = 0; i < attributes.getLength(); i++) {
			attrArray[i] = attributes.getValue(i);
		}
	
		if (attributes.getLength() == 1) {
			
			switch (tempEnum) {
				case JOURNAL -> currentPaper = new JournalType(attributes.getValue(0));
				case CATALOGUE -> currentPaper = new CatalogueType(attributes.getValue(0));
			}
		} else if (attributes.getValue(0).contains(".")) {
			
			switch (tempEnum) {
				case JOURNAL -> currentPaper = new JournalType(attributes.getValue(1), attributes.getValue(0));
				case CATALOGUE -> currentPaper = new CatalogueType(attributes.getValue(1), attributes.getValue(0));
			}
		} else {
			
			switch (tempEnum) {
				case JOURNAL -> currentPaper = new JournalType(attributes.getValue(0), attributes.getValue(1));
				case CATALOGUE -> currentPaper = new CatalogueType(attributes.getValue(0), attributes.getValue(1));
			}
		}
	}
}
