package com.rutlouski.xmltask.entity;

import java.time.YearMonth;

public class JournalType extends Paper {
	
	private int points;
	private boolean scopus;
	
	public JournalType() {
		super();
	}
	
	public JournalType(String id, String website, String title, PaperLanguage language, YearMonth date, 
			boolean colored, int price, int points, boolean scopus) {
		
		super(id, website, title, language, date, colored, price);
		this.points = points;
		this.scopus = scopus;
	}
	
	public JournalType(String id, String website) {
		super(id, website);
	}
	
	public JournalType(String id) {
		super(id);
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isScopus() {
		return scopus;
	}

	public void setScopus(boolean scopus) {
		this.scopus = scopus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + points;
		result = prime * result + (scopus ? 1231 : 1237);
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
		JournalType other = (JournalType) obj;
		return (points == other.points) && (scopus == other.scopus);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JournalType [id=").append(this.getId())
		.append(", website=").append(this.getWebsite())
		.append(", title=").append(this.getTitle())
		.append(", language=").append(this.getLanguage())
		.append(", date=").append(this.getDate())
		.append(", colored=").append(this.isColored())
		.append(", price=").append(this.getPrice())
		.append(", points=").append(points)
		.append(", scopus=").append(scopus).append("]");
		return builder.toString();
	}
}
