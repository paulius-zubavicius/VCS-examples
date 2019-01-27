package com.vcs.resources.examples;

import java.io.IOException;
import java.util.ResourceBundle;

public class AppPropertiesFromResources {

	private ResourceBundle rBundleLt = ResourceBundle.getBundle("settings_LT");

	public static void main(String[] args) throws IOException {

		AppPropertiesFromResources app = new AppPropertiesFromResources();

		System.out.println(app.rBundleLt.getString("serviceIP"));

	}

}
