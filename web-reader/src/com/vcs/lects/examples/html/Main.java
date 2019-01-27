package com.vcs.lects.examples.html;

public class Main {

	public static void main(String[] args) {

		// Nusiskaitom puslapi
		StringBuilder html = new UrlContentReader().readContent("https://www.delfi.lt/");

		// Darom ka reik su juo...
		System.out.println(html);

	}

}
