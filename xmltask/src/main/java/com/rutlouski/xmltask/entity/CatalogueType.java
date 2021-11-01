package com.rutlouski.xmltask.entity;

import java.time.YearMonth;

public class CatalogueType extends Paper {
	
	private int volume;

	public CatalogueType() {
		super();
	}

	public CatalogueType(String id, String website, String title, PaperLanguage language, YearMonth date, 
			boolean colored, int price, int volume) {
		
		super(id, website, title, language, date, colored, price);
		this.volume = volume;
	}
	
	public CatalogueType(String id, String website) {
		super(id, website);
	}
	
	public CatalogueType(String id) {
		super(id);
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
		builder.append("CatalogueType [id=").append(this.getId())
		.append(", website=").append(this.getWebsite())
		.append(", title=").append(this.getTitle())
		.append(", language=").append(this.getLanguage())
		.append(", date=").append(this.getDate())
		.append(", colored=").append(this.isColored())
		.append(", price=").append(this.getPrice())
		.append(", volume=").append(volume).append("]");
		return builder.toString();
	}
}
