package com.rutlouski.xmltask.entity;

import java.time.YearMonth;

public class CatalogueType extends PaperType {
	
	private int volume;

	public CatalogueType() {
		super();
	}

	public CatalogueType(int id, String website, PaperLanguage language, YearMonth date, 
			boolean colored, int price, int volume) {
		
		super(id, website, language, date, colored, price);
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + volume;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogueType other = (CatalogueType) obj;
		return (volume == other.volume);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalogueType [volume=");
		builder.append(volume);
		builder.append("]");
		return builder.toString();
	}
}
