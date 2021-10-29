package com.rutlouski.xmltask.entity;

public enum PaperLanguage {
	RUSSIAN,
	BELARUSIAN,
	ENGLISH,
	POLISH,
	HEBREW;

	public String getValue() {
		String str = this.toString().toLowerCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
