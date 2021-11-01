package com.rutlouski.xmltask.builder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.rutlouski.xmltask.entity.Paper;
import com.rutlouski.xmltask.exception.PaperException;

public abstract class AbstractPaperBuilder {
	
	protected Set<Paper> papers;
	
	protected AbstractPaperBuilder() {
		papers = new HashSet<>();
	}
	
	protected AbstractPaperBuilder(Set<Paper> papers) {
		this.papers = papers;
	}
	
	public Set<Paper> getPapers() {
		return papers.stream().collect(Collectors.toSet());
	}
	
	public void setPapers(Set<Paper> papers) {
		this.papers = papers;	
	}
	
	public abstract void buildSetPapers(String fileName) throws PaperException;
}
