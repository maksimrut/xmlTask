package com.rutlouski.xmltask.entity;

import java.time.YearMonth;

public abstract class PaperType {
	
	public static final String DEFAULT_WEBSITE = "http://www.example.org";
	
	private int id;
	private String website;
	private PaperLanguage language;
	private YearMonth date;
	private boolean colored;
	private int price;
	
	protected PaperType() {
		website = DEFAULT_WEBSITE;
		language = language.RUSSIAN;
	}

	protected PaperType(int id, String website, PaperLanguage language, YearMonth date, boolean colored, int price) {
		super();
		this.id = id;
		this.website = website;
		this.language = language;
		this.date = date;
		this.colored = colored;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public PaperLanguage getLanguage() {
		return language;
	}

	public void setLanguage(PaperLanguage language) {
		this.language = language;
	}

	public YearMonth getDate() {
		return date;
	}

	public void setDate(YearMonth date) {
		this.date = date;
	}

	public boolean isColored() {
		return colored;
	}

	public void setColored(boolean colored) {
		this.colored = colored;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (colored ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + price;
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		PaperType other = (PaperType) obj;
		return colored == other.colored && (id == other.id) 
				&& (language == other.language) && (price == other.price)
				&& date.equals(other.date) && website.equals(other.website);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paper [id=").append(id)
		.append(", website=").append(website)
		.append(", language=").append(language)
		.append(", date=").append(date)
		.append(", colored=").append(colored)
		.append(", price=").append(price).append("]");
		return builder.toString();
	}
}
