package com.rutlouski.xmltask.builder.factory;

import com.rutlouski.xmltask.builder.AbstractPaperBuilder;
import com.rutlouski.xmltask.builder.dom.DomPaperBuilder;
import com.rutlouski.xmltask.builder.sax.SaxPaperBuilder;
import com.rutlouski.xmltask.builder.stax.StaxPaperBuilder;
import com.rutlouski.xmltask.exception.PaperException;

public class PaperBuilderFactory {
	
	private enum TypeParser {
		SAX, DOM, STAX
	}
	
	public AbstractPaperBuilder createPaperBuilder(String typeParser) throws PaperException {
		
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		
		switch (type) {
			case SAX: return new SaxPaperBuilder();
			
			case DOM: return new DomPaperBuilder();
			
			case STAX: return new StaxPaperBuilder();
			
			default: throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}
	}
}
