package com.vcs.resources.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AppGsonReader {

	public static void main(String[] args) throws FileNotFoundException {

		File f = new File("java_obj.json");

		Reader reader = new FileReader(f);

		Gson gson = new Gson();

		/**
		 * Read as single object
		 */
		ModelObject mo = gson.fromJson(reader, ModelObject.class);

		System.out.println(mo.isSomethingMissing());
		System.out.println(mo.getPersons().get("dfgdg").getName());
		System.out.println(mo.getPersons().get("dfgdg").getAge());

		/**
		 * Read list
		 */
		f = new File("java_obj_list.json");
		reader = new FileReader(f);

		Type listType = new TypeToken<ArrayList<ModelObject>>() {
		}.getType();
		List<ModelObject> mos = new Gson().fromJson(reader, listType);

		System.out.println(mos.get(0).isSomethingMissing());
		System.out.println(mos.get(1).getPersons().get("dfgdg").getName());
		System.out.println(mos.get(0).getPersons().get("324324").getName());
		
	}

}
