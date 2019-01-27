package com.vcs.resources.examples;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class AppGsonWriter {

	public static void main(String[] args) throws IOException {

		ModelObject mo = new ModelObject();

		Gson gson = new Gson();

		/**
		 * Write as single
		 */
		File f = new File("java_obj.json");
		FileUtils.writeStringToFile(f, gson.toJson(mo), StandardCharsets.UTF_8, false);

		/**
		 * Write as list
		 */
		List<ModelObject> mos = new ArrayList<>();
		mos.add(mo);
		mos.add(mo);

		f = new File("java_obj_list.json");
		FileUtils.writeStringToFile(f, gson.toJson(mos), StandardCharsets.UTF_8, false);

		System.out.println("Done");

	}

}
