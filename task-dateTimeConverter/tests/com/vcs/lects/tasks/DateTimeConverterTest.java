package com.vcs.lects.tasks;

import com.vcs.lects.tasks.datetime.DateTimeConverter;

public class DateTimeConverterTest {

	public static void main(String[] args) {

		DateTimeConverter converter = new DateTimeConverter();

		System.out.println("2001, 9, 17, 9, 42, 57, false =>");
		System.out.println(converter.getDateTimeHumanReadable(2001, 9, 17, 9, 42, 57, false));

		System.out.println();

		System.out.println("2001, 9, 17, 9, 42, 57, true =>");
		System.out.println(converter.getDateTimeHumanReadable(2001, 9, 17, 9, 42, 57, true));

		System.out.println();

		System.out.println("2018, 2, 3, 23, 1, 17, false =>");
		System.out.println(converter.getDateTimeHumanReadable(2018, 2, 3, 23, 1, 17, false));

		System.out.println();

		System.out.println("2018, 2, 3, 23, 1, 17, true =>");
		System.out.println(converter.getDateTimeHumanReadable(2018, 2, 3, 23, 1, 17, true));

	}
}
