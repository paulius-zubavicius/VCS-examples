package com.vcs.lects.examples.files.resursuose;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class AppResources {

	public static void main(String[] args) throws IOException {

		/**
		 * Skaito duomenis is projekto vidaus resursu
		 */

		String text = IOUtils.toString(AppResources.class.getClassLoader().getResourceAsStream("data.dat"),
				Charset.forName("UTF-8"));

		System.out.println(text);

	}

}
