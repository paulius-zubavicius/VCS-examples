package com.vcs.examples.web.news.tagger.parsers;

import com.vcs.examples.web.news.tagger.util.HtmlGetReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DelfiParser implements WebParser {

	private static final String ART_DIV = "<divclass=\"article\">";

	private static final String LINK_START = "<ahref=\"";
	private static final String LINK_END = "\"target=\"_blank\"";

	private static final String POST_START = "<div itemprop=\"articleBody\">";
	private static final String POST_END = "<div class=\"clearfix\"></div>";

	@Override
	public String getMainUrl() {
		return "https://www.delfi.lt/apie/?page=statistika#main-content";
	}

	@Override
	public List<String> getArticlesURL() {

		// ismest dublikatus
		List<String> urls = parseUrls(HtmlGetReader.readContent(getMainUrl()));

		// imest: https://www.delfi.lt/video
		// ismest ne: https://www.delfi.lt/

		return urls.stream().filter((i) -> i.startsWith("https://www.delfi.lt"))
				.filter((i) -> !i.startsWith("https://www.delfi.lt/video")).collect(Collectors.toList());
	}

	private List<String> parseUrls(StringBuilder sb) {
		Set<String> result = new HashSet<String>();
		String html = sb.toString();

		html = html.replaceAll("\\s", "");
		html = html.substring(html.indexOf(ART_DIV));

		int startOfLink = html.indexOf(LINK_START);

		while (startOfLink > -1) {

			html = html.substring(startOfLink);
			int endOfLink = html.indexOf(LINK_END);

			String url = html.substring(LINK_START.length(), endOfLink);
			html = html.substring(endOfLink);

			result.add(url);

			startOfLink = html.indexOf(LINK_START);
		}
		return new ArrayList<>(result);
	}

	@Override
	public StringBuilder getArticleText(List<String> urls) {
		return urls.stream().map((s) -> getArticleText(s)).reduce(new StringBuilder(), (b1, b2) -> b1.append(b2));
	}

	@Override
	public StringBuilder getArticleText(String url) {
		StringBuilder page = HtmlGetReader.readContent(url);

		String html = page.toString();
		int startOfLink = html.indexOf(POST_START);
		html = html.substring(startOfLink);

		int endOfLink = html.indexOf(POST_END);

		html = html.substring(0, endOfLink);
		
		html = cutOutRange(html, "<script", "</script>");
		html = cutOutRange(html, "<div id=", "</div>");
		html = cutOutRange(html, "<div class=", "</div>");
		html = cutOutRange(html, "<a href=", "</a>");
		html = cutOutRange(html, "<iframe", "</iframe>");
		html = cutOutRange(html, "<style", "</style>");
		html = cutOutRange(html, "<span", "</span>");
		html = cutOutRange(html, "<blockquote", "</blockquote>");
		

		html = html.replaceAll("<div itemprop=\"articleBody\">", " ");
		html = html.replaceAll("<!-- cxenseparse_end -->", " ");
		html = html.replaceAll("<strong>", " ");
		html = html.replaceAll("</strong>", " ");
		html = html.replaceAll("<em>", " ");
		html = html.replaceAll("</em>", " ");
		html = html.replaceAll("<p>", " ");
		html = html.replaceAll("</p>", " ");
		html = html.replaceAll("</div>", " ");
		html = html.replaceAll("<br>", " ");

		// Clear special simbols
		html = html.replaceAll("[„“,\\.\\?!@#$%\\^\\&\\*\\(\\)–\\-\\=\\+;:]", " ");
		
		html = html.replaceAll(" .{1} ", " "); // padrikas simbolis
		html = html.replaceAll(" \\d ", " "); //skaicius
		html = html.replaceAll(" \\d{2,} ", " "); //skaicius
		
		html = html.replaceAll("[\\s]{2,}+", " "); // ivairus tararpai
		html = html.toLowerCase();
		html += " ";

		return new StringBuilder(html);
	}

	public String cutOutRange(String html, String from, String to) {

		StringBuilder sb = new StringBuilder();
		int startOfLink = html.indexOf(from);

		while (startOfLink > -1) {

			sb.append(html.substring(0, startOfLink));
			html = html.substring(startOfLink);

			html = html.substring(html.indexOf(to) + to.length());
			startOfLink = html.indexOf(from);
		}
		sb.append(html);
		return sb.toString();
	}

}
