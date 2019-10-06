package com.vcs.examples.web.news.tagger.word;

public class Word {

	private String str;
	private String root;

	public Word(String str) {
		if (null == str) {
			throw new NullPointerException();
		}
		this.str = str.toLowerCase();
		this.root = getRoot(this.str);
	}

	public String getStr() {
		return str;
	}

	public String getRoot() {
		return root;
	}

	private String getRoot(String str) {
		int len = str.length();
		if (len > 5) {
			int rootLen = len / 2;

			if (len % 2 != 0) {
				rootLen++;
			}

			return str.substring(0, rootLen);
		}
		return str;
	}

	@Override
	public String toString() {
		return root + " [" + str + "]";
	}


}
