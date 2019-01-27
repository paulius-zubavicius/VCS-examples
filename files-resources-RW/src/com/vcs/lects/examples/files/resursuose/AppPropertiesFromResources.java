package com.vcs.lects.examples.files.resursuose;

import java.io.IOException;
import java.util.ResourceBundle;

public class AppPropertiesFromResources {

	private ResourceBundle rBundleLt = ResourceBundle.getBundle("settings_LT");

	public static void main(String[] args) throws IOException {

		/**
		 * Skaito duomenis is projekto vidaus resursu
		 */
		
		AppPropertiesFromResources app = new AppPropertiesFromResources();
		System.out.println(app.rBundleLt.getString("serviceIP"));

	}

}
