package com.vcs.lects.examples.files.resursuose;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vcs.lects.examples.files.model.Langs;

public class AppPropertiesFromFileTranslates {

	private Map<Langs, Properties> props = new HashMap<>();

	public static void main(String[] args) throws IOException {

		/**
		 * Skaito duomenis is projekto vidaus resursu
		 */
		
		AppPropertiesFromFileTranslates app = new AppPropertiesFromFileTranslates();

		Langs lang = Langs.LT; //Langs.LT;

		for (int i = 1; i <= 12; i++) {
			System.out.println(app.props.get(lang).getProperty("menuo_" + i));
		}

	}

	public AppPropertiesFromFileTranslates() throws IOException {

		for (Langs lang : Langs.values()) {
			props.put(lang, loadProp(lang.toString()));
		}

	}

	private Properties loadProp(String lang) throws IOException {
		File f = new File("resources/settings_" + lang.toUpperCase() + ".properties");

		InputStream is = new FileInputStream(f);

		Properties prop = new Properties();
		prop.load(is);
		return prop;
	}

}
