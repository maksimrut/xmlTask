package com.rutlouski.xmltask.entity;

import java.time.YearMonth;

public class Paper {
	
	public static final String DEFAULT_WEBSITE = "http://www.example.org";
	
	private String id;
	private String website;
	private String title;
	private PaperLanguage language;
	private YearMonth date;
	private boolean colored;
	private int price;
	
	public Paper() {
		website = DEFAULT_WEBSITE;
		language = PaperLanguage.RUSSIAN;
	}

	public Paper(String id, String website, String title, PaperLanguage language, YearMonth date, boolean colored, int price) {
		this.id = id;
		this.website = website;
		this.title = title;
		this.language = language;
		this.date = date;
		this.colored = colored;
		this.price = price;
	}
	
	public Paper(String id, String website) {
		this.id = id;
		this.website = website;
		language = PaperLanguage.RUSSIAN;
		website = DEFAULT_WEBSITE;
	}
	
	public Paper(String id) {
		this.id = id;
		language = PaperLanguage.RUSSIAN;
		website = DEFAULT_WEBSITE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + price;
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Paper other = (Paper) obj;
		return colored == other.colored && id.equals(other.id) && (language == other.language) 
				&& (price == other.price) && date.equals(other.date)
				&& website.equals(other.website) && title.equals(other.title);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paper [id=").append(id)
		.append(", website=").append(website)
		.append(", title=").append(title)
		.append(", language=").append(language)
		.append(", date=").append(date)
		.append(", colored=").append(colored)
		.append(", price=").append(price).append("]");
		return builder.toString();
	}
}
