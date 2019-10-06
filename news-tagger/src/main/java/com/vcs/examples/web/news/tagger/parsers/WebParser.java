package com.vcs.examples.web.news.tagger.parsers;

import java.util.List;

public interface WebParser {

	String getMainUrl();

	List<String> getArticlesURL();

	StringBuilder getArticleText(String url);
	
	StringBuilder getArticleText(List<String> urls);
}
